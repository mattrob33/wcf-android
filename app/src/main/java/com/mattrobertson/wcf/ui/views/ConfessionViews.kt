package com.mattrobertson.wcf.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mattrobertson.wcf.R
import com.mattrobertson.wcf.model.Chapter
import com.mattrobertson.wcf.model.Confession
import com.mattrobertson.wcf.model.Section
import com.mattrobertson.wcf.ui.theme.OffWhite
import com.mattrobertson.wcf.ui.theme.VeryDarkGray
import com.mattrobertson.wcf.util.formatSectionText
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ConfessionScreen(
    confession: Confession
) {
    val coroutineScope = rememberCoroutineScope()

    val listState = rememberLazyListState()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                TableOfContents(
                    confession = confession,
                    onClick = { position ->
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                            listState.scrollToItem(position)
                        }
                    }
                )
            }
        },
        sheetPeekHeight = 0.dp
    ) {
        Confession(
            confession = confession,
            listState = listState,
            showBottomSheet = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            }
        )
    }
}

@Composable
fun Confession(
    confession: Confession,
    listState: LazyListState,
    showBottomSheet: () -> Unit
) {
    SelectionContainer {
        Box {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                state = listState
            ) {

                confession.chapters.forEachIndexed { index, chapter ->
                    item {
                        Chapter(chapter, index + 1)
                        Spacer(modifier = Modifier.height(32.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }

            ShowTocButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = {
                    showBottomSheet()
                }
            )
        }
    }
}

@Composable
fun ShowTocButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            if (isSystemInDarkTheme()) VeryDarkGray else OffWhite
        ),
        shape = RoundedCornerShape(
            topStart = 8.dp
        ),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            colorFilter = ColorFilter.tint(
                color = if (isSystemInDarkTheme()) OffWhite else VeryDarkGray
            ),
            contentDescription = "Menu"
        )
    }
}

@Composable
fun Chapter(
    chapter: Chapter,
    chapterNum: Int
) {
    Column {
        ChapterNumber(chapterNum)
        ChapterTitle(chapter.title)
        Spacer(modifier = Modifier.height(8.dp))

        chapter.sections.forEachIndexed { index, section ->
            Section(
                section = section,
                sectionNum = index + 1
            )
        }
    }
}

@Composable
fun ChapterNumber(num: Int) {
    val topPadding = when (num) {
        1 -> 28.dp
        else -> 0.dp
    }

    Text(
        text = "Chapter $num",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h1
    )
}

@Composable
fun ChapterTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h2
    )
}

@Composable
fun Section(
    section: Section,
    sectionNum: Int
) {
    Text(
        text = formatSectionText(section, sectionNum),
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
    )
}