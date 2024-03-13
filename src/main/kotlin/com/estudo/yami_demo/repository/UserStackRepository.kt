package com.estudo.yami_demo.repository

import com.estudo.yami_demo.entity.UserStack
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserStackRepository : CrudRepository<UserStack, UUID> {
}