package info.hargrave.refext

/**
 * Provides an implicit conversion from [[Option]] to [[Boolean]]
 */
trait OptionAsBoolean {
    implicit def option2boolean(opt: Option[Any]): Boolean = opt.isDefined
}
object OptionAsBoolean extends AnyRef with OptionAsBoolean
