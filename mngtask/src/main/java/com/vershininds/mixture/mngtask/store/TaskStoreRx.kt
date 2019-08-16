package com.vershininds.mixture.mngtask.store

import io.reactivex.disposables.Disposable
import com.vershininds.mixture.mngtask.action.Id
import com.vershininds.mixture.mngtask.strategy.GroupKey
import java.util.concurrent.ConcurrentHashMap

typealias TaskKey = String
typealias GroupMap = ConcurrentHashMap<TaskKey, Disposable>

internal class TaskStoreRx : TaskStore {

    val map = ConcurrentHashMap<GroupKey, GroupMap>()

    override fun contains(groupKey: GroupKey, id: Id): Boolean {
        return map[groupKey]?.containsKey(id) ?: false
    }

    @Synchronized
    override fun add(groupKey: GroupKey, id: Id, disposable: () -> Disposable) {
        val taskMap = map[groupKey]
                ?: ConcurrentHashMap<TaskKey, Disposable>().apply { map[groupKey] = this }
        taskMap[id] = disposable()
    }

    override fun removeAll() {
        map.keys.forEach { removeGroup(it) }
    }

    @Synchronized
    override fun removeGroup(groupKey: GroupKey) {
        map[groupKey]?.values?.forEach { it.dispose() }
    }

    override fun remove(groupKey: GroupKey, id: Id) {
        map[groupKey]?.apply {
            remove(id)?.dispose()
        }
    }
}