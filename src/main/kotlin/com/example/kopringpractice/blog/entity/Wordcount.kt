package com.example.kopringpractice.blog.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Wordcount(
    @Id
    val word: String,
    @Column(name = "count")
    val cnt: Int = 0
)