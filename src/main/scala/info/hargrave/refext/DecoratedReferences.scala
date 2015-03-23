package info.hargrave.refext

/**
 * Provides an implicit constructor for [[DecoratedReferences.Reference]]
 */
trait DecoratedReferences {
    import DecoratedReferences.Reference

    /**
     * Construct a [[DecoratedReferences.Reference reference wrapper]] for a given [[AnyRef reference]]
     *
     * @see [[DecoratedReferences.Reference]]
     * @param reference reference to wrap
     * @tparam T        reference type
     * @return          reference wrapper
     */
    implicit def DecoratedReference[T <: AnyRef](reference: T): Reference[T] = new Reference(reference)
}
object DecoratedReferences extends AnyRef with DecoratedReferences {

    /**
     * Provides decorations for dealing with null references
     *
     * @param data  AnyRef to wrap
     * @tparam Kind type of reference
     */
    final class Reference[Kind](data: Kind) {

        /**
         * Returns an option containing the wrapped data
         *
         * @return Option of type [[Kind]] containing [[data]]
         */
        def option  = Option(data)

        /**
         * Returns true when `data` is null
         *
         * @return true if `data` is null
         */
        def isNull  = data == null

        /**
         * Bridge to [[isNull]] which allows for `!reference` syntax to assert emptiness
         *
         * @return true if `data` is null
         */
        def unary_! = isNull

        /**
         * Returns true when `data` is not null
         *
         * @see [[?]] is a bridge to this method
         * @return true if `data` is not null
         */
        def notNull = data != null

        /**
         * Bridge to [[notNull]]
         * @see [[notNull]]
         * @return true if `data` is not null
         */
        def `?`     = notNull

    }

}
