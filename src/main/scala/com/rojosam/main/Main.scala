package com.rojosam.main

import com.rojosam.examples.{Filter, Acronyms, StatisticsByLetter}
import org.apache.spark.{SparkConf, SparkContext}


object Main {

  def main(args: Array[String]): Unit = {
    println(s"Arguments: ${args.mkString(", ")}")
    val example = args(0)
    val input = args(1)
    val output = if(args.length > 2) {
      Some(args(2))
    }else{
      None
    }
    val conf = new SparkConf().setAppName("SparkByExample")
    val sc = new SparkContext(conf)
    val letters = sc.parallelize('a' to 'z')
    val dictionary = sc.textFile(input)
    example match {
      case "filter" =>
        Filter.execute(dictionary)
      case " " =>
        //Example2.execute(sc, args)
      case "StatisticsByLetter" =>
        StatisticsByLetter.execute(dictionary)
      case "Acronyms" =>
        Acronyms.execute(dictionary)
    }
    if(!sc.isStopped) sc.stop()
  }

}
