package com.rojosam.drivers

import org.apache.spark.{SparkConf, SparkContext}

object Example1 {
  def execute(args: Array[String]): Unit = {
    val logFile = if(args.length > 1) {
       args(1)
    }else{
      "file:///usr/local/spark/README.md"
    }
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
