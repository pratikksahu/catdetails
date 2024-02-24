package com.sample.catdetails.view.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomProgressBar(modifier:Modifier = Modifier){
    CircularProgressIndicator(modifier = modifier)
}