package com.estudo.yami_demo.controller

import com.estudo.yami_demo.DTO.UsersDTO
import com.estudo.yami_demo.service.UserService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/users")
class UserController(private val userservice: UserService) {

    @PostMapping("/user")
    fun save(@Valid @RequestBody user: UsersDTO): ResponseEntity<Any> {
        return ResponseEntity.ok(userservice.save(user));
    }

    @GetMapping("/")
    fun findAll(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<Map<String, Any>>> {
        val pageable = PageRequest.of(page, size)
        val usersPage = userservice.getAll(pageable)
        return ResponseEntity.ok(usersPage)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<Any> {
        return ResponseEntity.ok(userservice.findById(id));
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @Valid @RequestBody user: UsersDTO): ResponseEntity<Any> {
        return ResponseEntity.ok(userservice.update(id, user));
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Any> {
        return ResponseEntity.ok(userservice.deleteById(id));
    }
}