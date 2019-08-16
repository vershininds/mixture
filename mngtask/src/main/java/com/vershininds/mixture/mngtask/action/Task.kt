package com.vershininds.mixture.mngtask.action

import com.vershininds.mixture.mngtask.strategy.GroupStrategyHolder
import com.vershininds.mixture.mngtask.strategy.StrategyHolder


typealias Id = String

/**
 * Represents a Task to be processed
 */
interface Task : StrategyHolder, GroupStrategyHolder {
    val id: Id
}