package com.example.kaamelottcitations.core

import com.example.kaamelottcitations.R

fun Int.getBookDrawableId(): Int {
    return when (this) {
        1 -> R.drawable.livre_1
        2 -> R.drawable.livre_2
        3 -> R.drawable.livre_3
        4 -> R.drawable.livre_4
        5 -> R.drawable.livre_5
        else -> R.drawable.livre_6
    }
}