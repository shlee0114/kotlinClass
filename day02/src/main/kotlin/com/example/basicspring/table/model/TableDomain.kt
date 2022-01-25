package com.example.basicspring.table.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "notice")
data class TableDomain(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long? = null,
    val writer: String,
    val title: String,
    val content: String,
    val password: String
) {
    constructor(tableRequest: TableRequest) : this(
        writer = tableRequest.writer ?: "none",
        title = tableRequest.title,
        content = tableRequest.content,
        password = tableRequest.password ?: "none"
    )

    constructor(tableRequest: TableRequest, seq: Long) : this(
        seq = seq,
        writer = tableRequest.writer ?: "none",
        title = tableRequest.title,
        content = tableRequest.content,
        password = tableRequest.password ?: "none"
    )

    override fun equals(other: Any?) = super.equals(other)
    override fun hashCode(): Int = javaClass.hashCode()
    override fun toString() = super.toString()
}
