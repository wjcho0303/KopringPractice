package com.example.kopringpractice.blog.controller

import com.example.kopringpractice.blog.dto.BlogDto
import com.example.kopringpractice.blog.entity.Wordcount
import com.example.kopringpractice.blog.service.BlogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/blog")
@RestController
class BlogController(
    private val blogService: BlogService
) {
//    @GetMapping
//    fun search(
//        @RequestParam query: String,
//        @RequestParam sort: String,
//        @RequestParam page: Int,
//        @RequestParam size: Int): String? {
//        val blogDto = BlogDto(query, sort, page, size)
//        return blogService.searchKakao(blogDto)
//    }

    @GetMapping
    fun search(
        @RequestBody blogDto: BlogDto): String? {
        return blogService.searchKakao(blogDto)
    }

    @GetMapping("/rank")
    fun searchWordRank(): List<Wordcount> = blogService.searchWordRank()
}