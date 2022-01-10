package com.example.kaamelottcitations.navigation

enum class NavigationScreens {
    Books,
    Characters,
    CharactersQuotes;

    companion object {
        fun fromRoute(route: String?): NavigationScreens =
            when (route?.substringBefore("/")) {
                Books.name -> Books
                Characters.name -> Characters
                CharactersQuotes.name -> CharactersQuotes
                null -> Books
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}