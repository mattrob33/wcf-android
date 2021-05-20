package com.mattrobertson.wcf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.mattrobertson.wcf.repo.ConfessionRepo
import com.mattrobertson.wcf.ui.theme.WCFTheme
import com.mattrobertson.wcf.ui.views.ConfessionScreen

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private val repo = ConfessionRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WCFTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ConfessionScreen(
                        confession = repo.getConfession()
                    )
                }
            }
        }
    }
}
