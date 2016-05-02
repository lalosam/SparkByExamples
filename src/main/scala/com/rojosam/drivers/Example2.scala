package com.rojosam.drivers

import org.apache.spark.{SparkConf, SparkContext}
import com.datastax.spark.connector._

object Example2 {
  def execute(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Simple Application")
      .set("spark.cassandra.connection.host", "127.0.0.1")
    val sc = new SparkContext(conf)
    sc.cassandraTable("test", "user")
      .map(n => s"******       ${n.getString("name")}")
      .collect().foreach(println)
  }

}