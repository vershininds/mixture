package com.vershininds.mixture.mngtask.action.reactive

import io.reactivex.Observable
import com.vershininds.mixture.mngtask.action.Id
import com.vershininds.mixture.mngtask.action.TaskFuture
import com.vershininds.mixture.mngtask.strategy.*

/**
 * Extension [TaskFuture] for rx [Observable]
 */
class ObservableTaskFuture<TResult : Any>(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP,
        rxSource: Observable<TResult>
) : TaskWrapperRx<Observable<TResult>, TResult>(id, strategy, groupStrategy, groupKey, rxSource)

/**
 * Convert [Observable] to [TaskFuture]
 */
fun <TResult : Any> Observable<TResult>.toTaskFuture(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP
): TaskFuture<TResult> = ObservableTaskFuture(id, strategy, groupStrategy, groupKey, this)