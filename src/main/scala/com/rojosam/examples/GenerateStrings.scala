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
    val chars2 = chars.cartesian(chars).map(concat)
    val chars3 = chars2.cartesian(chars).map(concat)
    val chars4 = chars3.cartesian(chars).map(concat)
    chars.union(chars2).union(chars3).union(chars4)
  }

  def concat(t: (String, String)):String = {
    t._1 + t._2
  }
}
