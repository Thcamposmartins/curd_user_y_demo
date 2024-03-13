package com.estudo.yami_demo.repository

import com.estudo.yami_demo.entity.Users
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<Users, UUID> {
    fun findAll(pageable: Pageable): MutableIterable<Users>

    @Query("SELECT NEW map(u.id AS id, u.nickName AS nickName, u.name AS name, TO_CHAR(CAST(u.birthDate AS TIMESTAMP), 'DD/MM/YYYY') AS birthDate, LISTAGG(s.stack, ', ') WITHIN GROUP (ORDER BY s.stack) AS stacks) FROM Users u JOIN UserStack s ON u.id = s.user.id WHERE u.id = :id GROUP BY u.id, u.nickName, u.name, u.birthDate")
    fun findByIdWithStacksInTwoTables(@Param("id") id: UUID): Iterable<Any>


    @Query("SELECT NEW map(u.id AS id, u.nickName AS nickName, u.name AS name, TO_CHAR(CAST(u.birthDate AS TIMESTAMP), 'DD/MM/YYYY') AS birthDate, LISTAGG(s.stack, ', ') WITHIN GROUP (ORDER BY s.stack) AS stacks) FROM Users u JOIN UserStack s ON u.id = s.user.id GROUP BY u.id, u.nickName, u.name, u.birthDate")
    fun findAllWithStacks(pageable: Pageable): Page<Map<String, Any>>
}