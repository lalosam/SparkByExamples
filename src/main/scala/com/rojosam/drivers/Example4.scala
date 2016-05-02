package com.rojosam.drivers

import org.apache.spark.{SparkConf, SparkContext}
import com.datastax.spark.connector._

object Example4 {
  def initial(): (String, Int) = ("", 0)

  def merge(group1: (String, Int), group2: (String, Int)): (String, Int) = {
    var delimiter = ""
    if(group1._1.length > 1){
      delimiter = ", "
    }
    (s"${group1._1}$delimiter${group2._1}", group1._2 + group2._2)
  }

  def mergeSeq(group: (String, Int), s: String): (String, Int) = {
    var delimiter = ""
    if(group._1.length > 1){
      delimiter = ", "
    }
    (s"${group._1}$delimiter$s", group._2 + 1)
  }

  def execute(args: Array[String]): Unit = {
    val logFile = if (args.length > 1) {
      args(1)
    } else {
      "file:///usr/local/spark/README.md"
    }
    val conf = new SparkConf().setAppName("Simple Application-E4")
      .set("spark.cassandra.connection.host", "127.0.0.1")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 10).cache()
    logData.map(word => (word.toLowerCase.sorted, word))
      .aggregateByKey(initial)(mergeSeq, merge)
      .map(v => (v._1, v._2._1, v._2._2))
      .saveToCassandra("test", "acronyms", SomeColumns("sorted", "words", "wordsnum"))
  }
}
