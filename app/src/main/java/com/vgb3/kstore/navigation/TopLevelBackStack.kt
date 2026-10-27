package com.vgb3.kstore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.serialization.NavKeySerializer
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer

/**
 * Каждый экран приложения содержит в себе стэк. Он необходим для навигации вглубь конкретного экрана.
 * Для хранения этой информации используется Map.
 *
 * topLevelKey - используется для bottom bar navigation, для определения активного item'а в bottom bar'е.
 * currentKey - экран, который рисует NavDisplay.
 *
 *
 *
 */

class TopLevelBackStack(
    private val startKey: NavKey,
    topLevelKey: MutableState<NavKey>,
    private val stacks: Map<NavKey, NavBackStack<NavKey>>
) {
    var topLevelKey by topLevelKey
        private set

    private val stacksInUse
        get() = if (topLevelKey == startKey) listOf(startKey)
                else listOf(startKey, topLevelKey)

    val currentKey
        get() = stacks.getValue(topLevelKey).last()

    fun addTopLevel(key: NavKey) {
        topLevelKey = key
    }

    fun add(key: NavKey) {
        stacks.getValue(topLevelKey).add(key)
    }

    /**
     * Для снятия верхнего экрана берем в stacks, значение по ключу topLevelKey, значением является стэк для родительского экрана.
     * И у полученного стэка верхний NavKey, если у экрана остался 1 экран, то topLevel становится стартовым (startKey).
     */
    fun removeLast() {
        val stack = stacks.getValue(topLevelKey)
        when {
            stack.size > 1 -> stack.removeAt(stack.lastIndex)
            topLevelKey != startKey -> topLevelKey = startKey
        }
    }

    @Composable
    fun toEntries(entryProvider: (NavKey) -> NavEntry<NavKey>): List<NavEntry<NavKey>> {
        //мапим стэки экранов в NavEntry для NavDisplay
        val decorated = stacks.mapValues { (_, stack) ->
            rememberDecoratedNavEntries(
                backStack = stack,
                entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()),
                entryProvider = entryProvider
            )
        }
        return stacksInUse.flatMap { decorated.getValue(it) }
    }
}

@Composable
fun rememberTopLevelBackStack(
    startKey: NavKey,
    topLevelKeys: List<NavKey>
): TopLevelBackStack {

    val topLevelKey = rememberSerializable(
        startKey, topLevelKeys,
        serializer = MutableStateSerializer(NavKeySerializer())
    ) { mutableStateOf(startKey) }

    val stacks = topLevelKeys.associateWith { key -> rememberNavBackStack(key) }

    return remember(startKey, topLevelKeys) {
        TopLevelBackStack(startKey, topLevelKey, stacks)
    }
}