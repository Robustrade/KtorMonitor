package ro.cosminmihu.ktor.monitor.ui.preview

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * Common-source replacement for `androidx.compose.ui.tooling.preview.datasource.LoremIpsum`,
 * which only ships on Android. Supplies a fixed placeholder string to `@PreviewParameter`
 * so the detail previews render in `commonMain` on Compose Multiplatform 1.8.x.
 */
internal class LoremIpsum : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(LOREM_IPSUM)
}

private const val LOREM_IPSUM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
        "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu " +
        "fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
        "culpa qui officia deserunt mollit anim id est laborum."
