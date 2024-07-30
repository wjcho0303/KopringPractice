package com.example.kopringpractice.blog.service

import com.example.kopringpractice.blog.dto.BlogDto
import com.example.kopringpractice.core.exception.InvalidInputException
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class BlogService {

    fun searchKakao(blogDto: BlogDto): String? {
        val messageList = mutableListOf<ExceptionMessage>()

        if (blogDto.query.trim().isEmpty()) {
            messageList.add(ExceptionMessage.EMPTY_QUERY)
        }

        if (blogDto.sort.trim() !in arrayOf("accuracy", "recency")) {
            messageList.add(ExceptionMessage.INACCURATE_SORT)
        }

        when {
            blogDto.page < 1 -> messageList.add(ExceptionMessage.LESS_THAN_MIN)
            blogDto.size > 50 -> messageList.add(ExceptionMessage.MORE_THAN_MAX)
        }

        if (messageList.isNotEmpty()) {
            val message = messageList.joinToString { it.message }
            throw InvalidInputException(message)
        }

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

private enum class ExceptionMessage(val message: String) {
    EMPTY_QUERY("query parameter required"),
    INACCURATE_SORT("sort must be accuracy or recency"),
    LESS_THAN_MIN("page is less than min"),
    MORE_THAN_MAX("page is more than max")
}