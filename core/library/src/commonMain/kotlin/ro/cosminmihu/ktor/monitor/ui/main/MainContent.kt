package ro.cosminmihu.ktor.monitor.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import ro.cosminmihu.ktor.monitor.ui.detail.DetailRoute
import ro.cosminmihu.ktor.monitor.ui.list.ListRoute

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun MainContent(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val navigator = rememberListDetailPaneScaffoldNavigator<String?>()

    Surface(modifier = modifier) {
        ListDetailPaneScaffold(
            modifier = Modifier.fillMaxSize(),
            directive = navigator.scaffoldDirective,
            value = navigator.scaffoldValue,
            listPane = {
                AnimatedPane {
                    ListRoute(
                        onClick = { id ->
                            coroutineScope.launch {
                                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, id)
                            }
                        },
                        onClear = {
                            coroutineScope.launch {
                                navigator.navigateBack()
                            }
                        }
                    )
                }
            },
            detailPane = {
                val id = navigator.currentDestination?.contentKey
                AnimatedPane {
                    DetailRoute(
                        id = id,
                        onBack = {
                            coroutineScope.launch {
                                navigator.navigateBack()
                            }
                        }
                    )
                }
            },
        )
    }
}