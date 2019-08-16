package com.vershininds.mixture.mngtask.store

import io.reactivex.disposables.Disposable
import com.vershininds.mixture.mngtask.action.Id
import com.vershininds.mixture.mngtask.strategy.DEFAULT_GROUP
import com.vershininds.mixture.mngtask.strategy.GroupKey

internal interface TaskStore {
    fun contains(groupKey: GroupKey = DEFAULT_GROUP, id: Id): Boolean
    fun add(groupKey: GroupKey = DEFAULT_GROUP, id: Id, disposable: () -> Disposable)
    fun removeAll()
    fun removeGroup(groupKey: GroupKey)
    fun remove(groupKey: GroupKey, id: Id)
}