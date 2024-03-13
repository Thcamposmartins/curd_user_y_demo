package com.estudo.yami_demo.controller

import com.estudo.yami_demo.AbstractIntegrationTest
import com.estudo.yami_demo.entity.Users
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForObject
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.jdbc.JdbcTestUtils
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class UserControllerTest : AbstractIntegrationTest() {

//    @LocalServerPort
//    private var port: Int = 0
//    @Autowired
//    lateinit var testRestTemplate:TestRestTemplate
//
//    private var baseUrl: String = "http://localhost"
//
//    @BeforeEach
//    fun setUp() {
//        baseUrl = "$baseUrl:$port/users"
//    }
//
//    @AfterEach
//    fun setDown(@Autowired jdbcTemplate: JdbcTemplate) {
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users")
//    }
//
//    @Test
//    fun `Should create a user and get user by id`() {
//        val userRequest = Users(
//            name = "Thaís",
//            nickName = "Thaty",
//            birthDate = LocalDateTime.of(1993, 4, 19, 1, 1),
//            stack = listOf("Java")
//        )
//
//
//        val createUserResponse = testRestTemplate.postForObject<Users>(baseUrl, userRequest)
//        val userResponse = testRestTemplate.getForEntity<Users>("$baseUrl/${createUserResponse?.id}")
//        assertNotNull(createUserResponse)
//        assertEquals(HttpStatus.OK, userResponse.statusCode)
//        val user = userResponse.body
//        assertNotNull(user?.id)
//        assertEquals(userRequest.nickName, user?.nickName)
//        assertEquals(userRequest.name, user?.name)
//        assertEquals(userRequest.birthDate, user?.birthDate)
//        assertEquals(userRequest.stack, user?.stack)
//    }

}
