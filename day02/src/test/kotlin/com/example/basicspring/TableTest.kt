package com.example.basicspring

import com.example.basicspring.utils.ErrorMessage
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsNull
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TableTest {

    private lateinit var _mockMvc: MockMvc

    @Autowired
    fun setMockMvc(mockMvc: MockMvc) {
        this._mockMvc = mockMvc
    }

    @Test
    fun tableList_returnTableList_success() {
        doGet("/table/")
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success", `is`(true)))
            .andExpect(jsonPath("$.data").isArray)
            .andExpect(jsonPath("$.error", `is`(IsNull.nullValue())))
    }

    @Test
    fun tableInfo_returnTableInfo_success() {
        doGet("/table/1")
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success", `is`(true)))
            .andExpect(jsonPath("$.data.writer", `is`("test")))
            .andExpect(jsonPath("$.data.title", `is`("test")))
            .andExpect(jsonPath("$.data.content", `is`("test")))
            .andExpect(jsonPath("$.error", `is`(IsNull.nullValue())))
    }

    @Test
    fun tableAdd_returnTrue_success() {
        doPost(
            "/table",
            "{\"writer\" : \"test\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.success", `is`(true)))
            .andExpect(jsonPath("$.data.result", `is`(true)))
            .andExpect(jsonPath("$.error", `is`(IsNull.nullValue())))
    }

    @Test
    fun tableUpdate_returnTrue_success() {
        doPut(
            "/table/1",
            "{\"writer\" : \"test1\", \"title\" : \"test1\", \"content\" : \"test1\", \"password\" : \"test\"}"
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.success", `is`(true)))
            .andExpect(jsonPath("$.data.result", `is`(true)))
            .andExpect(jsonPath("$.error", `is`(IsNull.nullValue())))

        doGet("/table/1")
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success", `is`(true)))
            .andExpect(jsonPath("$.data.writer", `is`("test1")))
            .andExpect(jsonPath("$.data.title", `is`("test1")))
            .andExpect(jsonPath("$.data.content", `is`("test1")))
            .andExpect(jsonPath("$.error", `is`(IsNull.nullValue())))
    }

    @Test
    fun tableUpdate_returnReason_fail() {
        doPut(
            "/table/1",
            "{\"writer\" : \"test1\", \"title\" : \"test1\", \"content\" : \"test1\", \"password\" : \"test2\"}"
        )
            .andDo(print())
            .andExpect(status().isForbidden)
            .andExpect(jsonPath("$.success", `is`(false)))
            .andExpect(jsonPath("$.data.reason", `is`("패스워드가 일치하지 않습니다")))
            .andExpect(jsonPath("$.error", `is`(IsNull.nullValue())))
    }

    @Test
    fun tableDelete_returnTrue_success() {
        doDelete("/table/1/test")
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success", `is`(true)))
            .andExpect(jsonPath("$.data.result", `is`(true)))
            .andExpect(jsonPath("$.error", `is`(IsNull.nullValue())))
    }

    @Test
    fun tableDelete_returnReason_fail() {
        doDelete("/table/1/test1")
            .andDo(print())
            .andExpect(status().isForbidden)
            .andExpect(jsonPath("$.success", `is`(false)))
            .andExpect(jsonPath("$.data.reason", `is`("패스워드가 일치하지 않습니다")))
            .andExpect(jsonPath("$.error", `is`(IsNull.nullValue())))
    }

    @Test
    fun tableInfo_throwException_NotFound() {
        doGet("/table/999")
            .checkIsError(status().isNotFound, ErrorMessage.INVALID_VALUE, "999")
    }

    @Test
    fun tableAdd_throwException_InvalidValue() {
        doPost(
            "/table",
            "{\"title\" : \"test\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_VALUE, "writer")
        doPost(
            "/table",
            "{\"writer\" : \"test\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_VALUE, "title")
        doPost(
            "/table",
            "{\"writer\" : \"test\", \"title\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_VALUE, "content")
        doPost(
            "/table",
            "{\"writer\" : \"test\", \"title\" : \"test\", \"content\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_VALUE, "password")
    }

    @Test
    fun tableUpdate_throwException_invalidValue() {
        doPut(
            "/table/1",
            "{\"title\" : \"test1\", \"content\" : \"test1\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_VALUE, "writer")
        doPut(
            "/table/1",
            "{\"writer\" : \"test1\", \"content\" : \"test1\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_VALUE, "title")
        doPut(
            "/table/1",
            "{\"writer\" : \"test1\", \"title\" : \"test1\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_VALUE, "content")
        doPut(
            "/table/1",
            "{\"writer\" : \"test1\", \"title\" : \"test1\", \"content\" : \"test1\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_VALUE, "password")
    }

    @Test
    fun tableAdd_throwException_invalidFormat() {
        doPost(
            "/table",
            "{\"writer\" : \"test!@#\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_FORMAT, "writer!@#한글, 숫자, 영어")

        doPost(
            "/table",
            "{\"writer\" : \"t\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "writer!@#2 ~ 10")

        doPost(
            "/table",
            "{\"writer\" : \"t11132d12dd2d\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "writer!@#2 ~ 10")

        doPost(
            "/table",
            "{\"writer\" : \"test\", \"title\" : \"${randomAlphanumeric(101)}\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "title!@#0 ~ 100")

        doPost(
            "/table",
            "{\"writer\" : \"test\", \"title\" : \"test\", \"content\" : \"${randomAlphanumeric(501)}\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "content!@#0 ~ 500")

        doPost(
            "/table",
            "{\"writer\" : \"test\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"t\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "password!@#2 ~ 10")

        doPost(
            "/table",
            "{\"writer\" : \"test\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"t12d12f12g1wgw\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "password!@#2 ~ 10")
    }

    @Test
    fun tableUpdate_throwException_invalidFormat() {
        doPut(
            "/table/1",
            "{\"writer\" : \"test!@#\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.INVALID_FORMAT, "writer!@#한글, 숫자, 영어")

        doPut(
            "/table/1",
            "{\"writer\" : \"t\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "writer!@#2 ~ 10")

        doPut(
            "/table/1",
            "{\"writer\" : \"t11132d12dd2d\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "writer!@#2 ~ 10")

        doPut(
            "/table/1",
            "{\"writer\" : \"test\", \"title\" : \"${randomAlphanumeric(101)}\", \"content\" : \"test\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "title!@#0 ~ 100")

        doPut(
            "/table/1",
            "{\"writer\" : \"test\", \"title\" : \"test\", \"content\" : \"${randomAlphanumeric(501)}\", \"password\" : \"test\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "content!@#0 ~ 500")

        doPut(
            "/table/1",
            "{\"writer\" : \"test\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"t\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "password!@#2 ~ 10")

        doPut(
            "/table/1",
            "{\"writer\" : \"test\", \"title\" : \"test\", \"content\" : \"test\", \"password\" : \"t12d12f12g1wgw\"}"
        )
            .checkIsError(status().isBadRequest, ErrorMessage.OUT_OF_RANGE, "password!@#2 ~ 10")
    }

    fun ResultActions.checkIsError(status: ResultMatcher, errorCode: ErrorMessage, additionalMessage: String = "") =
        andDo(print())
            .andExpect(status)
            .andExpect(jsonPath("$.data", `is`(IsNull.nullValue())))
            .andExpect(jsonPath("$.error.code", `is`(errorCode.name)))
            .andExpect(
                jsonPath(
                    "$.error.message",
                    `is`(errorCode.getMessage(additionalMessage))
                )
            )


    fun doGet(uri: String, param: String = "") =
        _mockMvc.perform(
            get("$uri$param")
                .accept(MediaType.APPLICATION_JSON)
        )

    fun doPost(uri: String, body: String = "") =
        _mockMvc.perform(
            post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )

    fun doDelete(uri: String) =
        _mockMvc.perform(
            delete(uri)
                .accept(MediaType.APPLICATION_JSON)
        )

    fun doPut(uri: String, body: String = "") =
        _mockMvc.perform(
            put(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
}