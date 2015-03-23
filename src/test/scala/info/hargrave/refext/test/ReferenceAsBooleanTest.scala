package info.hargrave.refext.test

import info.hargrave.refext.ReferenceAsBoolean._
import org.scalatest.{Matchers, WordSpec}

class ReferenceAsBooleanTest extends WordSpec with Matchers {

    "ReferenceAsBoolean" should {
        "allow references to be used as boolean expressions" which {
            "evaluate to false when null" in {
                val reference: Object = null
                (reference: Boolean) should be(false)
            }

            "evaluate to true when non-null" in {
                val reference: Object = new Object
                (reference: Boolean) should be(true)
            }
        }
    }

}
