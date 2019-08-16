package com.vershininds.mixture.mngtask.action.reactive

import io.reactivex.Single
import com.vershininds.mixture.mngtask.action.Id
import com.vershininds.mixture.mngtask.action.TaskFuture
import com.vershininds.mixture.mngtask.strategy.*

/**
 * Extension [TaskFuture] for rx [Single]
 */
class SingleTaskFuture<TResult : Any>(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP,
        rxSource: Single<TResult>
) : TaskWrapperRx<Single<TResult>, TResult>(id, strategy, groupStrategy, groupKey, rxSource)

/**
 * Convert [Single] to [TaskFuture]
 */
fun <TResult : Any> Single<TResult>.toTaskFuture(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP
): TaskFuture<TResult> = SingleTaskFuture(id, strategy, groupStrategy, groupKey, this)