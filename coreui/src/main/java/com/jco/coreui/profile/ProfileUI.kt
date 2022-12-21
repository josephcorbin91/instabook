package com.jco.coreui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun ProfileUI(profileState: ProfileState) {
    Column {
        Image(
            painter = painterResource(id = androidx.core.R.drawable.notification_bg),
            contentDescription = null
        )
        Text(text = profileState.handle)
        if (profileState.isViewed) { }
        if (profileState.addButtonVisible) { }
    }
}