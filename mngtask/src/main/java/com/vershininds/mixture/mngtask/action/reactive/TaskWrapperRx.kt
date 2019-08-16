package com.vershininds.mixture.mngtask.action.reactive

import com.vershininds.mixture.mngtask.action.Id
import com.vershininds.mixture.mngtask.action.TaskFuture
import com.vershininds.mixture.mngtask.strategy.*

/**
 * Class helper for create [TaskFuture] from various rx sources(Observable, Flowable, Single, etc.)
 */
abstract class TaskWrapperRx<S, TResult : Any>(
        override val id: Id,
        override val strategy: Strategy = SaveMe,
        override val groupStrategy: GroupStrategy = Default,
        override val groupKey: String = DEFAULT_GROUP,
        val rxSource: S
) : TaskFuture<TResult>