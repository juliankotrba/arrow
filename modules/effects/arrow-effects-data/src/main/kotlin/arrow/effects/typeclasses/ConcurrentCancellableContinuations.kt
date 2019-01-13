package arrow.effects.typeclasses

import arrow.Kind
import arrow.effects.typeclasses.suspended.ConcurrentSyntax
import arrow.typeclasses.MonadContinuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.RestrictsSuspension

typealias ConcurrentEffects<F> = MonadDeferCancellableContinuation<F, *>

@RestrictsSuspension
@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
open class ConcurrentCancellableContinuation<F, A>(val CF: Concurrent<F>, override val context: CoroutineContext = EmptyCoroutineContext) :
  MonadDeferCancellableContinuation<F, A>(CF), Concurrent<F> by CF, ConcurrentSyntax<F> {
  override fun <B> binding(c: suspend MonadContinuation<F, *>.() -> B): Kind<F, B> =
    bindingCancellable { c() }.a
}