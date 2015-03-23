package info.hargrave.refext

/**
 * Provides an implicit conversion from [[Option]] to [[Boolean]]
 */
trait OptionAsBoolean {

    /**
     * Implicit converter that will convert an expression of `Option[_]` to a boolean value corresponding to [[Option.isDefined]]
     *
     * @param opt   option expression
     * @return      result of [[Option.isDefined]]
     */
    implicit def option2boolean(opt: Option[Any]): Boolean = opt.isDefined

}
object OptionAsBoolean extends AnyRef with OptionAsBoolean
