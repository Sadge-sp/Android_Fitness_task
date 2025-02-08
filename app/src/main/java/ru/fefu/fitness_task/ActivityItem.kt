package ru.fefu.fitness_task

sealed class ActivityItem {
    data class Section(val date: String) : ActivityItem()
    data class Activity(val title: String, val description: String) : ActivityItem()
}