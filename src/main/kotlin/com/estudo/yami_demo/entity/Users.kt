package com.estudo.yami_demo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
data class Users(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "nick_name", length = 32)
    var nickName: String? = null,

    @Column(name = "name", length = 255, nullable = false, unique = true)
    var name: String = "",

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    var birthDate: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    var stacks: List<UserStack> = mutableListOf(),

    @Transient
    var stack: List<String> = mutableListOf()
) {
    fun addStack(stack: String) {
        this.stacks.plus(stack)
    }
}