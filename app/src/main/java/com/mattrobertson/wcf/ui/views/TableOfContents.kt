package com.mattrobertson.wcf.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mattrobertson.wcf.model.Confession

@Composable
fun TableOfContents(
    confession: Confession,
    onClick: (position: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        confession.chapters.forEachIndexed { index, chapter ->
            item {
                TocItem(
                    text = "${index + 1}. ${chapter.title}",
                    onClick = { onClick(index) }
                )
                Divider()
            }
        }
    }
}

@Composable
fun TocItem(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    )
}