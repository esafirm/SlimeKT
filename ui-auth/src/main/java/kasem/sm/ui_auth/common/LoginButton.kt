/*
 * Copyright (C) 2021, Kasem S.M
 * All rights reserved.
 */
package kasem.sm.ui_auth.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kasem.sm.common_ui.SlimePrimaryButton

@Composable
internal fun LoginButton(
    enabled: Boolean,
    isLoading: Boolean,
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    SlimePrimaryButton(
        isLoading = isLoading,
        onClick = onContinueClicked,
        enabled = enabled,
        text = "Continue",
        modifier = modifier
    )
}