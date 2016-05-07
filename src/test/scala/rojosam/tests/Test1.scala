package rojosam.tests

import com.rojosam.examples.{Acronyms, Filter, StatisticsByLetter}
import rojosam.UnitSpec

import scala.collection.mutable


class Test1 extends UnitSpec{

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
    val dictionary = sc.parallelize(Array("dog", "god", "none"))
    val output = Acronyms.execute(dictionary).collect()
    output.foreach(t => println(s"${t._1.padTo(10, " ").mkString("")}-> ${t._2} (${t._3})"))
    assert(output.length == 2)
  }

}
