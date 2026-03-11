package com.phantomshard.michael_jose_ap2_p2.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.phantomshard.michael_jose_ap2_p2.domain.model.Jugador
import com.phantomshard.michael_jose_ap2_p2.ui.theme.Michael_Jose_AP2_P2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JugadorListScreen(
    viewModel: JugadorListViewModel = hiltViewModel(),
    onAddJugador: () -> Unit,
    onEditJugador: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getJugadores()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Jugadores") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddJugador) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Jugador")
            }
        }
    ) { padding ->
        JugadorListBody(
            modifier = Modifier.padding(padding),
            uiState = uiState,
            onEditJugador = onEditJugador
        )
    }
}

@Composable
fun JugadorListBody(
    modifier: Modifier = Modifier,
    uiState: JugadorListUiState,
    onEditJugador: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        uiState.error?.let {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.jugadores) { jugador ->
                JugadorRow(
                    jugador = jugador,
                    onClick = { onEditJugador(jugador.jugadorId) }
                )
            }
        }

        Divider()
        Text(
            text = "Total jugadores: ${uiState.jugadores.size}",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun JugadorRow(
    jugador: Jugador,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "ID: ${jugador.jugadorId}", style = MaterialTheme.typography.labelSmall)
            Text(text = jugador.nombres, style = MaterialTheme.typography.titleMedium)
            Text(text = jugador.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JugadorListPreview() {
    Michael_Jose_AP2_P2Theme {
        val uiState = JugadorListUiState(
            jugadores = listOf(
                Jugador(1, "Michael Jose", "michael@example.com"),
                Jugador(2, "Jose Michael", "jose@example.com")
            )
        )
        Surface {
            JugadorListScreen(
                onAddJugador = {},
                onEditJugador = {}
            )
        }
    }
}
