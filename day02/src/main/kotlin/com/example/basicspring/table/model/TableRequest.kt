package com.example.basicspring.table.model

import javax.validation.constraints.NotBlank

data class TableRequest(
    @field:NotBlank(message = "INVALID_VALUE!@#writer")
    val writer: String = "",

    @field:NotBlank(message = "INVALID_VALUE!@#title")
    val title: String = "",

    @field:NotBlank(message = "INVALID_VALUE!@#content")
    val content: String = "",

    @field:NotBlank(message = "INVALID_VALUE!@#password")
    val password: String = ""
)