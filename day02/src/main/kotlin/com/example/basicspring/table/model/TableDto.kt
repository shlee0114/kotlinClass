package com.example.basicspring.table.model

data class TableDto(
    val id: Long,
    val writer: String,
    val title: String,
    val content: String = "",
) {
    constructor(tableDomain: TableDomain) : this(
        id = tableDomain.seq?:0L,
        writer = tableDomain.writer,
        title = tableDomain.title,
        content = tableDomain.content
    )
}

data class TableResultDto(
    val result : Boolean = true,
    val reason: String = ""
)
