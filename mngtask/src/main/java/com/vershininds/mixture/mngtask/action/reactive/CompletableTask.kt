package com.vershininds.mixture.mngtask.action.reactive

import io.reactivex.Completable
import com.vershininds.mixture.mngtask.action.Id
import com.vershininds.mixture.mngtask.action.TaskFuture
import com.vershininds.mixture.mngtask.strategy.*

/**
 * Extension [TaskFuture] for rx [Completable]
 */
class CompletableTaskFuture(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP,
        rxSource: Completable
) : TaskWrapperRx<Completable, Unit>(id, strategy, groupStrategy, groupKey, rxSource)

/**
 * Convert [Completable] to [TaskFuture]
 */
fun Completable.toTaskFuture(
        id: Id,
        strategy: Strategy = SaveMe,
        groupStrategy: GroupStrategy = Default,
        groupKey: String = DEFAULT_GROUP
): TaskFuture<Unit> = CompletableTaskFuture(id, strategy, groupStrategy, groupKey, this)