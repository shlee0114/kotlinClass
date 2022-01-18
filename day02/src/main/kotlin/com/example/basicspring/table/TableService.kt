package com.example.basicspring.table

import com.example.basicspring.table.model.TableDomain
import com.example.basicspring.table.model.TableDto
import com.example.basicspring.table.model.TableRequest
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TableService(
    private val _repository: TableRepository
) {
    @Transactional(readOnly = true)
    fun getPagingList(pageNum: Int, size: Int) =
        _repository.findAll(PageRequest.of(pageNum, size))
            .map {
                TableDto(it)
            }

    @Transactional
    fun addTable(tableRequest: TableRequest) =
        _repository.save(TableDomain(tableRequest))

    @Transactional
    fun updateTable(tableRequest: TableRequest, seq: Long) =
        _repository.save(TableDomain(tableRequest, seq))

    @Transactional
    fun deleteTable(seq: Long) =
        _repository.deleteById(seq)
}