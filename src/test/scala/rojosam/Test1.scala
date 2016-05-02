package rojosam

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

}
