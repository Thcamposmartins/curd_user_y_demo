package com.estudo.yami_demo.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "user_stack")
@IdClass(UserStackId::class)
data class UserStack(
    @Id @ManyToOne @JoinColumn(name = "user_id") var user: Users?,

    @Id @Column(name = "stack", length = 32) var stack: String? = null
) {
    constructor() : this(null, null)
}

data class UserStackId(
    var user: UUID? = null, var stack: String? = null
)