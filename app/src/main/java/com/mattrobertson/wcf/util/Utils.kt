package com.mattrobertson.wcf.util

import com.mattrobertson.wcf.model.Section

fun formatSectionText(
    section: Section,
    sectionNum: Int
): String {
    return "${numToRomanNumeral(sectionNum)} ${section.text}"
}

private fun numToRomanNumeral(num: Int): String {
    return when (num) {
        1 -> "i."
        2 -> "ii."
        3 -> "iii."
        4 -> "iv."
        5 -> "v."
        6 -> "vi."
        7 -> "vii."
        8 -> "viii."
        9 -> "ix."
        10 -> "x."
        11 -> "xi."
        12 -> "xii."
        13 -> "xiii."
        14 -> "xiv."
        15 -> "xv."
        else -> ""
    }
}