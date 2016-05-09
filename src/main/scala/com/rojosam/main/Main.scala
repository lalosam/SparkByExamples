package com.rojosam.main

import com.rojosam.examples.{Acronyms, Filter, GenerateStrings, StatisticsByLetter}
import org.apache.spark.{SparkConf, SparkContext}


object Main {

  def main(args: Array[String]): Unit = {
    println(s"Arguments: ${args.mkString(", ")}")
    val example = args(0)
    val input = args(1)
    val output = if(args.length > 2) {
      args(2)
    }else{
      ""  //default directory
    }
    val partitions:Int = if(args.length > 3) {
      args(3).toInt
    }else{
      10  //default number of partitions
    }
    val conf = new SparkConf().setAppName("SparkByExample")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .set("com.rojosam.outputdir", output)
      .set("com.rojosam.partitions", partitions.toString)
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
      case "GenerateStrings" =>
        GenerateStrings.execute(dictionary)
    }
    if(!sc.isStopped) sc.stop()
  }

}
