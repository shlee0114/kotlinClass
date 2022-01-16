package com.example.basicspring.table.model

data class TableDto(
    val writer: String,
    val title: String,
    val content: String = "",
) {
    constructor(tableDomain: TableDomain) : this(
        writer = tableDomain.writer,
        title = tableDomain.title,
        content = tableDomain.content
    )
}

data class TableResultDto(
    val success : Boolean = true,
    val reason: String = ""
)
