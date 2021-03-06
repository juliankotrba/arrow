package arrow.effects.typeclasses

import arrow.Kind

interface Environment<F> {
  fun dispatchers(): Dispatchers<F>

  fun handleAsyncError(e: Throwable): Kind<F, Unit>
}
