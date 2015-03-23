package info.hargrave.refext

/**
 * Provides an implicit conversion from [[AnyRef]] to [[Boolean]] depending on whether or not said reference is null
 */
trait ReferenceAsBoolean {

    /**
     * Implicit converter from AnyRef to Boolean, which will convert AnyRef to true if it is non-null, otherwise to false
     *
     * @param ref   reference to convert to Boolean
     * @return      whether or not `ref` is null
     */
    implicit def IsValid(ref: AnyRef): Boolean = ref != null

}
object ReferenceAsBoolean extends AnyRef with ReferenceAsBoolean