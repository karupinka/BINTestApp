package ru.yandex.repinanr.binapp.presentation

sealed interface State

class ErrorState(val message: Int) : State
object InProgress : State
object ResultState : State
object EmptyResultState : State