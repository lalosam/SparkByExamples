package com.rojosam.examples

import org.apache.spark.rdd.RDD

object Filter {
  def execute(dictionary:RDD[String]): Unit = {
    val numAs = dictionary.filter(line => line.contains("a")).count()
    val numBs = dictionary.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
