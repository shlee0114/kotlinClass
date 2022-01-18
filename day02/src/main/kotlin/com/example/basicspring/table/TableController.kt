package com.example.basicspring.table

import com.example.basicspring.table.model.TableRequest
import com.example.basicspring.table.model.TableResultDto
import com.example.basicspring.utils.ApiUtils
import com.example.basicspring.utils.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/table")
class TableController(
    private val _service: TableService
) {
    @GetMapping
    fun tableList(
        @RequestParam
        @Valid
        page: PageRequest
    ) =
        ResponseEntity(
            ApiUtils(
                data = page.run {
                    _service.getPagingList(pageNum, size)
                }
            ),
            HttpStatus.OK
        )


    @PostMapping
    fun addTable(
        @RequestBody
        @Valid
        tableRequest: TableRequest
    ) =
        _service.addTable(tableRequest)
            .run {
                ResponseEntity(
                    ApiUtils(
                        data = TableResultDto()
                    ),
                    HttpStatus.OK
                )
            }

    @PutMapping("/{id}")
    fun updateTable(
        @PathVariable("id")
        id: String,
        @RequestBody
        @Valid
        tableRequest: TableRequest
    ) =
        _service.updateTable(tableRequest, id.toLong())
            .run {
                ResponseEntity(
                    ApiUtils(
                        data = TableResultDto()
                    ),
                    HttpStatus.OK
                )
            }

    @DeleteMapping("/{id}/{password}")
    fun deleteTable(
        @PathVariable("id")
        id: String,
        @PathVariable("password")
        password: String
    ) =
        _service.deleteTable(id.toLong())
            .run {
                ResponseEntity(
                    ApiUtils(
                        data = TableResultDto()
                    ),
                    HttpStatus.OK
                )
            }
}