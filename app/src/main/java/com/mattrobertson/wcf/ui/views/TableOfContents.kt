package com.mattrobertson.wcf.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mattrobertson.wcf.model.Confession

@Composable
fun TableOfContents(
    confession: Confession,
    onClick: (position: Int) -> Unit
) {
    Column {
       DragHandle()

        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
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

@Composable
fun DragHandle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(24.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
    }
}