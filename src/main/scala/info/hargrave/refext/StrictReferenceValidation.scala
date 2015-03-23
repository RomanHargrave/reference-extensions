package info.hargrave.refext

/**
 * Provides an implicit construction mechanism for [[StrictReferenceValidation.Reference]]
 */
trait StrictReferenceValidation {

    import StrictReferenceValidation.Reference

    implicit def ReferenceValidator[T <: AnyRef](ref: T)(implicit manifest: Manifest[T]): Reference[T] = new Reference(ref)(manifest)

}

object StrictReferenceValidation extends AnyRef with StrictReferenceValidation {

    /**
     * Contains the implementation of `!` for doing strict non-null checking of a reference.
     *
     * Example:
     *
     * {{{
     *      import info.hargrave.refext.StrictReferenceValidation._
     *      val potentiallyNull: Object = null
     *
     *      doSomething(potentiallyNull.ensured)    // => NullPointerException(Illegal null reference of type Object)
     *      doSomething(potentiallyNull!)           // => Same as above
     * }}}
     *
     * @param ref       AnyRef to decorate
     * @param manifest  type manifest of wrapped reference
     * @tparam Kind     type of reference
     */
    final class Reference[Kind <: AnyRef](ref: Kind)(implicit manifest: Manifest[Kind]) {

        /**
         * When called, this will return [[ref]] if it is non-null, otherwise it will raise a [[NullPointerException]]
         * This allows for placing contracts on potentially null references (such as from an external API) at the time
         * of passing the reference instead of later on, when unexpected functionality could occur, or an exception
         * might be raised at a point in the call stack that obscures the real error point
         *
         * @throws NullPointerException when [[ref]] is null
         * @return [[ref]] that is ensured to be non-null
         */
        def ensured: Kind = ref match {
                case null => throw new NullPointerException(s"Illegal null reference of type ${ manifest.wrap.runtimeClass.getCanonicalName }")
                case _ => ref
        }

        /**
         * Bridge to [[ensured]]
         *
         * @note this is a bridge method
         * @see ensured
         */
        def `!`: Kind = ensured

    }

}