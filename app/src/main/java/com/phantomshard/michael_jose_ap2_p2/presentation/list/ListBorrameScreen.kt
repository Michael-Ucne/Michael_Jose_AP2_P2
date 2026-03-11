package com.phantomshard.michael_jose_ap2_p2.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.phantomshard.michael_jose_ap2_p2.ui.theme.Michael_Jose_AP2_P2Theme

@Composable
fun ListBorrameScreen(onNavigateToDetail: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToDetail) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
private fun ListBorramePreview() {
    Michael_Jose_AP2_P2Theme {
        ListBorrameScreen(onNavigateToDetail = {})
    }
}
