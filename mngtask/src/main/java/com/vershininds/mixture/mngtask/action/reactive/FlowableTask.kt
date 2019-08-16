package com.vershininds.mixture.mngtask.action.reactive

import io.reactivex.Flowable
import com.vershininds.mixture.mngtask.action.Id
import com.vershininds.mixture.mngtask.action.TaskFuture
import com.vershininds.mixture.mngtask.strategy.*

/**
 * Extension [TaskFuture] for rx [Flowable]
 */
class FlowableTaskFuture<TResult : Any>(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP,
        rxSource: Flowable<TResult>
) : TaskWrapperRx<Flowable<TResult>, TResult>(id, strategy, groupStrategy, groupKey, rxSource)

/**
 * Convert [Flowable] to [TaskFuture]
 */
fun <TResult : Any> Flowable<TResult>.toTaskFuture(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP
): TaskFuture<TResult> = FlowableTaskFuture(id, strategy, groupStrategy, groupKey, this)