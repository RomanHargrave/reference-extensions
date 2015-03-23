package info.hargrave.refext.test

import info.hargrave.refext.StrictReferenceValidation._
import org.scalatest.{Matchers, WordSpec}

class StrictReferenceValidationTest extends WordSpec with Matchers {


    "StrictReferenceValidation" should {
        "provide a means to ensure reference existence" which {
            "should raise an exception when null" in {
                val reference: Object = null

                val intercepted = Set(
                                         intercept[NullPointerException] { reference.ensured },
                                         intercept[NullPointerException] { reference ! }
                                     )

                intercepted.foreach(_.getMessage should startWith("Illegal null reference of type"))
            }

            "should return the reference when non-null" in {
                val reference: Object = new Object

                reference.ensured should be(reference)
                (reference !) should be(reference)
            }
        }
    }

}
