package com.vershininds.mixture.mngtask.action.reactive

import io.reactivex.Maybe
import com.vershininds.mixture.mngtask.action.Id
import com.vershininds.mixture.mngtask.action.TaskFuture
import com.vershininds.mixture.mngtask.strategy.*

/**
 * Extension [TaskFuture] for rx [Maybe]
 */
class MaybeTaskFuture<TResult : Any>(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP,
        rxSource: Maybe<TResult>
) : TaskWrapperRx<Maybe<TResult>, TResult>(id, strategy, groupStrategy, groupKey, rxSource)

/**
 * Convert [Maybe] to [TaskFuture]
 */
fun <TResult : Any> Maybe<TResult>.toTaskFuture(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP
): TaskFuture<TResult> = MaybeTaskFuture(id, strategy, groupStrategy, groupKey, this)