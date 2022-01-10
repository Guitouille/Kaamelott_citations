package com.example.kaamelottcitations.ui.books

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kaamelottcitations.R
import com.example.kaamelottcitations.core.getBookDrawableId
import com.example.kaamelottcitations.core.numberOfBooks

@ExperimentalFoundationApi
@Composable
fun BooksGrid(onBookClick: (Int) -> Unit) {
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
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

@Composable
fun BookCell(bookNumber: Int, bookName: String, onBookClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onBookClick(bookNumber) }
            .padding(all = 4.dp)
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = bookNumber.getBookDrawableId()),
                contentDescription = bookName
            )
            Text(bookName)
        }
    }
}

@Preview
@Composable
fun BookCellPreview() {
    BookCell(bookNumber = 1, bookName = stringResource(id = R.string.book, "1"), {})
}