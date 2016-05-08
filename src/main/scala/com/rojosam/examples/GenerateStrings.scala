package com.rojosam.examples

import org.apache.spark.rdd.RDD

object GenerateStrings {

  def execute(letters:RDD[String]): Unit = {
    val sc = letters.context
    val outputDir = sc.getConf.get("com.roosam.outputdir", "generatedstrings/outputDefault")
    generateStrings(letters)
    .saveAsTextFile(s"${outputDir}")
  }

  def generateStrings (letters:RDD[String]): RDD[String] = {
    val chars = letters.cache()
    printSize("CHARS", chars)
    val chars2 = chars.cartesian(chars).map(concat)
    printSize("CHARS2", chars2)
    val chars3 = chars2.cartesian(chars).map(concat)
    printSize("CHARS3", chars3)
    val chars4 = chars3.cartesian(chars).map(concat)
    printSize("CHARS4", chars4)
    val result = chars.union(chars2).union(chars3).union(chars4)
    printSize("RESULT", result)
    result
  }

  def concat(t: (String, String)):String = {
    t._1 + t._2
  }

  def printSize(name:String, rdd:RDD[String]): Unit ={
    println(s"******* **** ****   $name: ${rdd.partitions.size}")
  }
}
