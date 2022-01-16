package com.example.basicspring.utils

data class ApiUtils(
    val success: Boolean = true,
    val data: Any? = null,
    val error: ErrorUtils? = null
) {
    data class ErrorUtils(
        val code: String = "NONE",
        val message: String = ""
    )
}