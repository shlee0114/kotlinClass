package com.example.basicspring.table.model

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class TableRequest(
    @field:NotNull(message = "INVALID_VALUE#@!writer")
    @field:Size(min = 2, max = 10, message = "OUT_OF_RANGE#@!writer!@#2 ~ 10")
    @field:Pattern(regexp = "[a-zA-Z1-9ㄱ-ㅎ가-힣]*", message = "INVALID_FORMAT#@!writer!@#한글, 숫자, 영어")
    val writer: String? = null,

    @field:NotBlank(message = "INVALID_VALUE#@!title")
    @field:Size(min = 0, max = 100, message = "OUT_OF_RANGE#@!title!@#0 ~ 100")
    val title: String = "",

    @field:NotBlank(message = "INVALID_VALUE#@!content")
    @field:Size(min = 0, max = 500, message = "OUT_OF_RANGE#@!content!@#0 ~ 500")
    val content: String = "",

    @field:NotNull(message = "INVALID_VALUE#@!password")
    @field:Size(min = 2, max = 10, message = "OUT_OF_RANGE#@!password!@#2 ~ 10")
    val password: String? = null
)