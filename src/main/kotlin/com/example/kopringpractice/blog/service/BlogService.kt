package com.example.kopringpractice.blog.service

import com.example.kopringpractice.blog.dto.BlogDto
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class BlogService {

    fun searchKakao(blogDto: BlogDto): String? {
        val webClient = WebClient
            .builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        val response = webClient
            .get()
            .uri { it.path("/v2/search/blog")
                .queryParam("query", blogDto.query)
                .queryParam("sort", blogDto.sort)
                .queryParam("page", blogDto.page)
                .queryParam("size", blogDto.size)
                .build()}
            .header("Authorization", "KakaoAK 54ac671e55e83c243e50fcc5b8ed98d7")
            .retrieve()
            .bodyToMono<String>()

        val result = response.block()

        return result
    }
}