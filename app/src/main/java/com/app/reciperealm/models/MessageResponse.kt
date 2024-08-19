package com.app.reciperealm.models

data class MessageResponse(
    val error: Boolean,
    val url: String?,
    val message: String?
)
