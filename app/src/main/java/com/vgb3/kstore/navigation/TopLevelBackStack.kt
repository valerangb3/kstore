package com.vgb3.kstore.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList

class TopLevelBackStack<T: Any>(private val startKey: T) {
    private val topLevelStacks: LinkedHashMap<T, SnapshotStateList<T>> = linkedMapOf(
        startKey to mutableStateListOf(startKey)
    )

    var topLevelKey by mutableStateOf(startKey)
        private set

    val backStack = mutableStateListOf(startKey)

    private fun updateBackStack() {
        backStack.clear()
        topLevelStacks[startKey]?.let { topLevelItems ->
            backStack.addAll(topLevelItems)
        }
        if (topLevelKey != startKey) {
            topLevelStacks[topLevelKey]?.let { topLevelItems ->
                backStack.addAll(topLevelItems)
            }
        }
    }

    fun addTopLevel(key: T) {
        topLevelStacks.getOrPut(key) { mutableStateListOf(key) }
        topLevelKey = key
        updateBackStack()
    }

    fun add(key: T) {
        topLevelStacks[topLevelKey]?.add(key)
        updateBackStack()
    }

    fun removeLast() {
        topLevelStacks[topLevelKey]?.let { stack ->
            when {
                stack.size > 1 -> stack.removeAt(stack.lastIndex)
                topLevelKey != startKey -> topLevelKey = startKey
                else -> return
            }
        }
        updateBackStack()
    }
}