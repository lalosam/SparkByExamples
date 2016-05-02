package com.rojosam.drivers

import org.apache.spark.{SparkConf, SparkContext}

object Example3 {
  def execute(args: Array[String]): Unit = {
    val logFile = if(args.length > 1) {
      args(1)
    }else{
      "file:///usr/local/spark/README.md"
    }
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 10).cache()
    val accumWords = sc.accumulator(0, "NumberOfWords")
    val accumLetters = sc.accumulator(0, "NumberOfLetters")
    logData.flatMap(word => {
      accumWords += 1
      word.toCharArray
    })
      .map(letter => {
        accumLetters += 1
        (letter.toLower, 1)
      })
      .reduceByKey((a,b) => a+b)
      .coalesce(1,shuffle = true)
      .sortBy(_._2)
      .collect()
      .foreach(a => println(s"${a._1} => ${a._2}"))
    println(s"Words: ${accumWords.value}")
    println(s"Letters: ${accumLetters.value}")

  }
}
