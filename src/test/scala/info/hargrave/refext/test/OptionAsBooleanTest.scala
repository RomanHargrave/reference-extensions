package info.hargrave.refext.test

import info.hargrave.refext.OptionAsBoolean._
import org.scalatest.{Matchers, WordSpec}

class OptionAsBooleanTest extends WordSpec with Matchers {

    "OptionAsBoolean" should {

        "convert None to false" in {
            val option = Option.empty[Object]
            (option: Boolean) should be(false)
        }

        "convert Some to true" in {
            val option = Option(new Object)
            (option: Boolean) should be(true)
        }

    }

}
