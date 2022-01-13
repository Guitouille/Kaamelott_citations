package com.example.kaamelottcitations.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.kaamelottcitations.ui.books.BooksGrid
import com.example.kaamelottcitations.ui.characters.CharacterList
import com.example.kaamelottcitations.ui.quotes.CharacterQuotesByBookScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.Books.name,
        modifier = modifier
    ) {
        composable(NavigationScreens.Books.name) {
            BooksGrid(
                onBookClick = { bookNumber ->
                    navController.navigate("${NavigationScreens.Characters}/$bookNumber")
                }
            )
        }
        composable(
            route = "${NavigationScreens.Characters.name}/{bookNumber}",
            arguments = listOf(
                navArgument("bookNumber") {
                    // Make argument type safe
                    type = NavType.IntType
                }
            ),
            deepLinks = listOf(navDeepLink {
                uriPattern = "kaamelottcitations://${NavigationScreens.Characters}/{bookNumber}"
            })

        ) { entry ->
            val bookNumber = entry.arguments?.getInt("bookNumber")
            if (bookNumber != null) {
                CharacterList(
                    bookNumber = bookNumber,
                    onCharacterClicked = { book, characterNameIndex ->
                        navController.navigate("${NavigationScreens.CharactersQuotes}/$book/$characterNameIndex")
                    })
            }
        }
        composable(
            route = "${NavigationScreens.CharactersQuotes.name}/{bookNumber}/{characterNameIndex}",
            arguments = listOf(
                navArgument("bookNumber") {
                    // Make argument type safe
                    type = NavType.IntType
                },
                navArgument("characterNameIndex") {
                    // Make argument type safe
                    type = NavType.IntType
                }
            ),
            deepLinks = listOf(navDeepLink {
                uriPattern =
                    "kaamelottcitations://${NavigationScreens.CharactersQuotes}/{bookNumber}/{characterNameIndex}"
            })

        ) { entry ->
            val bookNumber = entry.arguments?.getInt("bookNumber")
            val characterNameIndex = entry.arguments?.getInt("characterNameIndex")
            if (bookNumber != null && characterNameIndex != null) {
                CharacterQuotesByBookScreen(
                    bookNumber = bookNumber,
                    characterNameIndex = characterNameIndex
                )
            }
        }
    }
}
