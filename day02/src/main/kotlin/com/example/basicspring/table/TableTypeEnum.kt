package com.example.basicspring.table

import org.springframework.http.HttpStatus

enum class TableTypeEnum {
    NOT_FOUND {
        override fun message() : String = "해당 게시글이 존재하지 않습니다"
        override fun success() : Boolean = false
        override fun status() : HttpStatus = HttpStatus.NOT_FOUND
    },
    WRONG_PASSWORD {
        override fun message() : String = "패스워드가 일치하지 않습니다"
        override fun success() : Boolean = false
        override fun status() : HttpStatus = HttpStatus.FORBIDDEN
    },
    SUCCESS {
        override fun message() : String = ""
        override fun success() : Boolean = true
        override fun status() : HttpStatus = HttpStatus.OK
    };

    abstract fun message() : String
    abstract fun success() : Boolean
    abstract fun status() : HttpStatus
}