package com.rojosam.examples

import org.apache.spark.rdd.RDD

object Acronyms {
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

  def execute(dictionary:RDD[String]): RDD[(String, String, Int)] = {
    dictionary.map(word => (word.toLowerCase.sorted, word))
      .aggregateByKey(initial)(mergeSeq, merge)
      .map(v => (v._1, v._2._1, v._2._2))
  }
}
