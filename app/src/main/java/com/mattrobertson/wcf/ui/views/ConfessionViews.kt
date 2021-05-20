package com.mattrobertson.wcf.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mattrobertson.wcf.model.Chapter
import com.mattrobertson.wcf.model.Confession
import com.mattrobertson.wcf.model.Section
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
    Box {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
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

        Text(
            text = "ᐱ",
            color = MaterialTheme.colors.primary,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable {
                    showBottomSheet()
                }
                .padding(24.dp),
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
                sectionNum = index + 1,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ChapterNumber(num: Int) {
    Text(
        text = "Chapter $num",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 22.sp
    )
}

@Composable
fun ChapterTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Italic,
        fontSize = 20.sp
    )
}

@Composable
fun Section(
    section: Section,
    sectionNum: Int,
    modifier: Modifier = Modifier
) {
    Text(text = formatSectionText(section, sectionNum), modifier)
}