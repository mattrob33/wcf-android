package com.mattrobertson.wcf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.mattrobertson.wcf.repo.ConfessionRepo
import com.mattrobertson.wcf.ui.theme.WCFTheme
import com.mattrobertson.wcf.ui.views.ConfessionScreen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ReadingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WCFTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ConfessionScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
