package rojosam.tests

import com.holdenkarau.spark.testing.SharedSparkContext
import com.rojosam.examples.{Acronyms, Filter, GenerateStrings, StatisticsByLetter}
import org.apache.spark.SparkConf
import org.scalacheck.Gen
import rojosam.UnitSpec

import scala.collection.mutable


class Test1 extends UnitSpec{

  override val conf = new SparkConf().
    setMaster("local[*]").
    setAppName("test").
    set("spark.ui.enabled", "true").
    set("spark.app.id", appID)

  "A Stack" should "pop values in last-in-first-out order" in {
    println(1)
    val stack = new mutable.Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop() === 2)
    assert(stack.pop() === 1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    println(2)
    val emptyStack = new mutable.Stack[String]
    intercept[NoSuchElementException] {
      emptyStack.pop()
    }
  }

  it should "Clue" in {
    println(3)
    withClue("this is a clue") {
      intercept[IndexOutOfBoundsException] {
        "hi".charAt(-1)
      }
    }
  }

  "Filter" should "return one \"a\" and one \"b\"" in {
    val dictionary = sc.parallelize(Array("arco", "bicel", "piso"))
    Filter.execute(dictionary)
  }

  "StatisticsByLetter" should "return 4 for each vowel" in {
    val dictionary = sc.parallelize(Array("aeiouaeiou", "aeiouaeiou"))
    StatisticsByLetter.execute(dictionary)
  }

  "Acronyms" should "return two rows" in {
    //val list = Gen.listOf(Gen.alphaStr).sample
    //val dictionary = sc.parallelize(list.get,2)
    val dictionary = sc.parallelize(Array("dog", "god", "none"),2)
    val output = Acronyms.execute(dictionary).collect()
    output.foreach(t => println(s"${t._1.padTo(10, " ").mkString("")}-> ${t._2} (${t._3})"))
    assert(output.length == 2)
  }

  "GenerateStrings" should "return 10,000 words" in {
    val partitions = 10
    val dictionary = sc.parallelize('a' to 'z', partitions).map(c => c.toString).cache()
    val outputDir = sc.broadcast("/user/eduardo/sparkbyexamples")
    val output = GenerateStrings.execute(dictionary)
    output.saveAsTextFile(s"${outputDir.value}/strings")
  }

}
