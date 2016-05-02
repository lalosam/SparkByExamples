package rojosam

import org.scalatest._

abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors
                                         with BeforeAndAfter {
  before{
    println("Before")
  }

  after{
    println(
      "After")
  }
}