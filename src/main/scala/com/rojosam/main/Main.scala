package com.rojosam.main

import com.rojosam.drivers.{Example1, Example2, Example3, Example4}


object Main {
  def main(args: Array[String]): Unit = {
    println(s"Arguments: ${args.mkString(", ")}")
    val example = args(0).toInt
    example match {
      case 1 =>
        Example1.execute(args)
      case 2 =>
        Example2.execute(args)
      case 3 =>
        Example3.execute(args)
      case 4 =>
        Example4.execute(args)
    }
  }

}
