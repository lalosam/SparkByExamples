package rojosam

import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.SparkConf
import org.scalatest._

abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors
                                         with BeforeAndAfter with SharedSparkContext{

  self: SharedSparkContext =>


  before{

    println("\n\n** Starting test\n")
  }

  after{

  }
}