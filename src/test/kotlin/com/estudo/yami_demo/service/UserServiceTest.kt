package com.estudo.yami_demo.service

import com.estudo.yami_demo.DTO.ResponseUserDTO
import com.estudo.yami_demo.DTO.UsersDTO
import com.estudo.yami_demo.entity.UserStack
import com.estudo.yami_demo.entity.Users
import com.estudo.yami_demo.repository.UserRepository
import com.estudo.yami_demo.repository.UserStackRepository
import org.springframework.data.domain.Pageable
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import java.time.LocalDateTime
import java.util.*

internal class UserServiceTest {


    private val userRepository: UserRepository = mock(UserRepository::class.java)
    private val userStackRepository: UserStackRepository = mock(UserStackRepository::class.java)
    private val userService: UserService = UserService(userRepository, userStackRepository)

    @Test
    fun `Should save user`() {
        val userStack = UserStack()
        val userDto = UsersDTO("Doe", "John Doe", LocalDateTime.now(), mutableListOf("Java"))
        val user = Users(UUID.randomUUID(), "Doe", "John Doe", LocalDateTime.now(), mutableListOf(userStack), mutableListOf())

        `when`(userRepository.save(any(Users::class.java))).thenReturn(user)

        val response= userService.save(userDto) as ResponseUserDTO

        verify(userRepository, times(1)).save(any(Users::class.java))
        verify(userStackRepository, times(1)).save(any(UserStack::class.java))
        assertNotNull(response)
        assertEquals("Doe", response.nickName)
        assertEquals("John Doe", response.name)
    }

//    @Test
//    fun `Should get all users`() {
//        val users = listOf(
//            mapOf(
//                "nickName" to "nick1",
//                "stacks" to "Java, Spring",
//                "name" to "User 1",
//                "id" to "1302d676-8f8b-02dc-e063-020011ac1a6d",
//                "birthDate" to "01/01/2000"
//            ),
//            mapOf(
//                "nickName" to "nick2",
//                "stacks" to "Android, Kotlin",
//                "name" to "User 2",
//                "id" to "1302d676-8f8c-02dc-e063-020011ac1a6d",
//                "birthDate" to "02/02/2001"
//            ),
//            mapOf(
//                "nickName" to "Thaty",
//                "stacks" to "Java, PHP",
//                "name" to "dedede",
//                "id" to "b885b0c9-509c-43c1-a04b-cbfddd26eba4",
//                "birthDate" to "19/04/1993"
//            )
//        )
//
//        val pageable: Pageable = mock(Pageable::class.java)
//        val page: Page<Map<String, Any>> = PageImpl(users, pageable, users.size.toLong())
//
//        `when`(userRepository.findAllWithStacks(any(Pageable::class.java))).thenReturn(page)
//
//        val result = userService.getAll(pageable)
//
//        assertNotNull(result)
//        verify(userRepository, times(1)).findAllWithStacks(any(Pageable::class.java))
//    }

    @Test
    fun `Should find user by id`() {
        val userId = UUID.randomUUID()
        val users = listOf(
            mapOf(
                "nickName" to "nick1",
                "stacks" to "Java, Spring",
                "name" to "User 1",
                "id" to "1302d676-8f8b-02dc-e063-020011ac1a6d",
                "birthDate" to "01/01/2000"
            ),
            mapOf(
                "nickName" to "nick2",
                "stacks" to "Android, Kotlin",
                "name" to "User 2",
                "id" to "1302d676-8f8c-02dc-e063-020011ac1a6d",
                "birthDate" to "02/02/2001"
            ),
            mapOf(
                "nickName" to "Thaty",
                "stacks" to "Java, PHP",
                "name" to "dedede",
                "id" to "b885b0c9-509c-43c1-a04b-cbfddd26eba4",
                "birthDate" to "19/04/1993"
            )
        )
        `when`(userRepository.findByIdWithStacksInTwoTables(userId)).thenReturn(users)
        val result = userService.findById(userId)

        assertNotNull(result)
        verify(userRepository, times(1)).findByIdWithStacksInTwoTables(userId)
    }

    @Test
    fun `Should update user`() {
        val userId = UUID.randomUUID()
        val userDto = UsersDTO("Doe", "John Doe", LocalDateTime.now(), listOf("Java"))
        val user = Users(userId, "Doe", "John Doe",LocalDateTime.now(),  mutableListOf(), mutableListOf())
        `when`(userRepository.findById(userId)).thenReturn(Optional.of(user))

        val response = userService.update(userId, userDto)

        verify(userRepository, times(1)).save(any(Users::class.java))
        verify(userStackRepository, times(1)).save(any(UserStack::class.java))
    }

    @Test
    fun `Should delete user by id`() {
        val userId = UUID.randomUUID()
        val response= userService.deleteById(userId)

        val result = userService.deleteById(userId)
        assertNotNull(result)
    }
}