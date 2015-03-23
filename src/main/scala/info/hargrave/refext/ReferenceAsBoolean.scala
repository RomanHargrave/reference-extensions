package info.hargrave.refext;

/**
 * Date: 3/22/15
 * Time: 6:30 PM
 */
trait ReferenceAsBoolean {

    implicit def IsValid(ref: AnyRef): Boolean = ref != null

}
object ReferenceAsBoolean extends AnyRef with ReferenceAsBoolean