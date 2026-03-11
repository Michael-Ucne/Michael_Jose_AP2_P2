package com.phantomshard.michael_jose_ap2_p2.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.phantomshard.michael_jose_ap2_p2.ui.theme.Michael_Jose_AP2_P2Theme

@Composable
fun DetailBorrameScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onBack() }
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailBorramePreview() {
    Michael_Jose_AP2_P2Theme {
        DetailBorrameScreen(onBack = {})
    }
}
