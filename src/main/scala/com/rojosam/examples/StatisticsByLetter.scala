package com.rojosam.examples

import org.apache.spark.rdd.RDD

object StatisticsByLetter {
  def execute(dictionary: RDD[String]): Unit = {
    val sc = dictionary.context
    dictionary.flatMap(word => {
      word.toCharArray
    })
      .map(letter => {
        (letter.toLower, 1)
      })
      .reduceByKey((a, b) => a + b)
      .coalesce(1, shuffle = true)
      .sortBy(_._2)
      .collect()
      .foreach(a => println(s"${a._1} => ${a._2}"))
  }
}
