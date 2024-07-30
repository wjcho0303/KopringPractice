package com.example.kopringpractice.blog.repository

import com.example.kopringpractice.blog.entity.Wordcount
import org.springframework.data.jpa.repository.JpaRepository

interface WordRepository : JpaRepository<Wordcount, String> {
    fun findTop100ByOrderByCntDesc(): List<Wordcount>
}
