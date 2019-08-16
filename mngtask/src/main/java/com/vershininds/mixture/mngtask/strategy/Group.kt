package com.vershininds.mixture.mngtask.strategy

typealias GroupKey = String
const val DEFAULT_GROUP = "default_group"

interface GroupStrategyHolder {
    val groupStrategy: GroupStrategy
    val groupKey: GroupKey
}

/**
 * Group strategies for processing tasks
 */
sealed class GroupStrategy

/**
 * By default, all tasks fall into this group
 */
object Default : GroupStrategy()

/**
 * Kill all tasks in group(with same groupKey) after run task with KillGroup strategy
 */
object KillGroup : GroupStrategy()