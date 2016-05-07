package rojosam

import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest._

abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors
                                         with BeforeAndAfter with SharedSparkContext{
  before{
    println("\n\n** Starting test\n")
  }

  after{
    println(
      "")
  }
}