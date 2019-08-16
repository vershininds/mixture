package com.vershininds.mixture.mngtask.strategy

interface StrategyHolder {
    val strategy: Strategy
}

/**
 * Strategies for processing tasks
 */
sealed class Strategy

/**
 * Task with same Id kill previous running task
 */
object KillMe : Strategy()

/**
 * Task with same Id will not be started while the previous task is running
 */
object SaveMe : Strategy()