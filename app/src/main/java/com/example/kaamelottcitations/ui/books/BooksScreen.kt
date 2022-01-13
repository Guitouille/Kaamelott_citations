package com.example.kaamelottcitations.ui.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kaamelottcitations.R
import com.example.kaamelottcitations.core.getBookDrawableId
import com.example.kaamelottcitations.core.numberOfBooks
import com.example.kaamelottcitations.ui.Toolbar
import com.example.kaamelottcitations.ui.theme.QuickSand
import com.example.kaamelottcitations.ui.theme.RomanCoffee

@Composable
fun BooksGrid(onBookClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .background(color = RomanCoffee)
            .fillMaxHeight()
    ) {
        Toolbar(title = stringResource(R.string.screen_title_book))
        LazyColumn(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            items(numberOfBooks) {
                val bookNumber = it + 1

                BookCell(
                    bookNumber,
                    stringResource(id = R.string.book, "$bookNumber"),
                    onBookClick
                )
            }
        }
    }
}

@Composable
fun BookCell(bookNumber: Int, bookName: String, onBookClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onBookClick(bookNumber) }
            .padding(all = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(color = QuickSand)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card {
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .padding(6.dp),
                    painter = painterResource(id = bookNumber.getBookDrawableId()),
                    contentDescription = bookName
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.h6,
                    text = bookName,
                    color = Color.White
                )
                Text(
                    style = MaterialTheme.typography.caption,
                    text = stringResource(R.string.screen_book_see_characters),
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun BookCellPreview() {
    BookCell(bookNumber = 1, bookName = stringResource(id = R.string.book, "1"), {})
}