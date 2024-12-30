package com.example.homework_jc.ui.anim

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally


object CustomScreenAnimations {

    val enterFromRight: EnterTransition
        get() = slideInHorizontally(
            initialOffsetX = { it / 2 },
            animationSpec = tween(durationMillis = 350)
        ) + fadeIn(animationSpec = tween(350))

    val exitToLeft: ExitTransition
        get() = slideOutHorizontally(
            targetOffsetX = { -it / 2 },
            animationSpec = tween(durationMillis = 350)
        ) + fadeOut(animationSpec = tween(350))

    val popEnterFromLeft: EnterTransition
        get() = slideInHorizontally(
            initialOffsetX = { -it / 2 },
            animationSpec = tween(durationMillis = 350)
        ) + fadeIn(animationSpec = tween(350))

    val popExitToRight: ExitTransition
        get() = slideOutHorizontally(
            targetOffsetX = { it / 2 },
            animationSpec = tween(durationMillis = 350)
        ) + fadeOut(animationSpec = tween(350))
}

