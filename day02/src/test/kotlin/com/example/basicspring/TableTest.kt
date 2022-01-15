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
    fun tableDelete_returnTrue_success() {
        doDelete("/table/1/test")
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success", `is`(true)))
            .andExpect(jsonPath("$.data.result", `is`(true)))
            .andExpect(jsonPath("$.error", `is`(IsNull.nullValue())))
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