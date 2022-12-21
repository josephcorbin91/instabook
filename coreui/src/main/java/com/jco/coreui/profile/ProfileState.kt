package com.jco.coreui.profile

data class ProfileState(
    val handle: String,
    val addButtonVisible: Boolean,
    val isViewed: Boolean,
    val imageUrl: String
)