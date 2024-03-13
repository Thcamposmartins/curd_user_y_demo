package com.estudo.yami_demo.DTO

import com.estudo.yami_demo.entity.UserStack
import com.estudo.yami_demo.entity.Users
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class UsersDTO(
    @JsonProperty("nick_name")
    @Size(max = 32)
    var nickName: String,

    @NotBlank
    @Size(max = 255)
    var name: String,

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("birth_date")
    var birthDate: LocalDateTime = LocalDateTime.now(),

    @Size(max = 32, message = "Each technology name must be at most 32 characters long.")
    var stack: List<String> = mutableListOf()
) {
    constructor() : this("", "", LocalDateTime.now(), listOf())

    fun toUser(objectMapper: ObjectMapper): Users {
        val user = objectMapper.convertValue(this, Users::class.java)
        val userStacks = stack.map { UserStack(user, it) }
        user.stacks = userStacks
        return user
    }
}