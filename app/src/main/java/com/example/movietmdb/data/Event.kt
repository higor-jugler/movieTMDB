package com.example.movietmdb.data

/**
 * This class is responsible for fixing the bug where the app does not return to the main screen
 */

class Event<out T>(private val content: T) {

    var hasBeenHandle = false
        private set // Anyone outside can read this value but cannot set it

    fun getContentIfNotHandle(): T? {
        return if (hasBeenHandle) {
            null
        } else {
            hasBeenHandle = true
            content
        }
    }
}