package com.estudo.yami_demo.DTO

import com.estudo.yami_demo.entity.Users
import com.estudo.yami_demo.middleware.DateUtils
import jakarta.validation.constraints.NotBlank

data class ResponseUserDTO(
    val id: String,
    val nickName: String,
    @NotBlank(message = "Name field is required!")
    val name: String,
    val age: Int,
    val stacks: String
) {
    constructor(user: Users) : this(
        user.id.toString(),
        user.nickName.toString(),
        user.name,
        DateUtils.calculateAge(user.birthDate),
        user.stacks.map { it.stack }.toString()
    )
}