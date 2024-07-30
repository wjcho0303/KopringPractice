package com.example.kopringpractice.blog.service

import com.example.kopringpractice.blog.dto.BlogDto
import org.springframework.stereotype.Service

@Service
class BlogService {
    fun searchKakao(blogDto: BlogDto): String? {
        println(blogDto.toString())
        return "SearchKakao"
    }
}