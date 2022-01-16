package com.example.basicspring.table

import com.example.basicspring.table.model.TableDomain
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TableRepository : PagingAndSortingRepository<TableDomain, Long>