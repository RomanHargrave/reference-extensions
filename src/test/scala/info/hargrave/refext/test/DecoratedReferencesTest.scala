package info.hargrave.refext.test

import info.hargrave.refext.DecoratedReferences._
import org.scalatest.{Matchers, WordSpec}

class DecoratedReferencesTest extends WordSpec with Matchers {


    "DecoratedReferences" should {
        "provide option conversion with Any Reference" which {
            "should create an empty option when null" in {
                val reference: Object = null
                reference.option should be(None)
            }

            "should create an option containing the reference when not null" in {
                val reference: Object = new Object
                reference.option should be(Some(reference))
            }
        }

        "provide isNull and prefix `!` syntax for isNull" which {
            "returns true when null" in {
                val reference: Object = null
                reference.isNull should be(true)
                !reference should be(true)
            }

            "returns false when non-null" in {
                val reference: Object = new Object
                reference.isNull should be(false)
                !reference should be(false)
            }
        }

        "provide notNull and suffix `?` syntax for notNull" which {
            "returns true when non-null" in {
                val reference: Object = new Object
                reference.notNull should be(true)
                (reference ?) should be(true)
            }

            "returns false when null" in {
                val reference: Object = null
                reference.notNull should be(false)
                (reference ?) should be(false)
            }
        }
    }

}
