package com.estudo.yami_demo.service

import com.estudo.yami_demo.DTO.ResponseUserDTO
import com.estudo.yami_demo.DTO.UsersDTO
import com.estudo.yami_demo.entity.UserStack
import com.estudo.yami_demo.repository.UserRepository
import com.estudo.yami_demo.repository.UserStackRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.data.domain.Pageable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository, private val userStackRepository: UserStackRepository) {
    val logger: Logger = LoggerFactory.getLogger(UserService::class.java);

    fun save(userdto: UsersDTO): Any {
        try {
            val objectMapper = ObjectMapper().registerModule(JavaTimeModule())
            val user = userdto.toUser(objectMapper)
            user.id = UUID.randomUUID()
            user.stacks = mutableListOf()

            userdto.stack.forEach { stack ->
                (user.stacks as MutableList<UserStack>).add(UserStack(user, stack))
            }

            val userSaved = userRepository.save(user)

            user.stacks.forEach { userStack ->
                userStackRepository.save(userStack)
            }
            logger.info("The user were saved successfully: {}");
            return ResponseUserDTO(userSaved)

        } catch (e: Exception) {
            logger.error("The user was not saved successfully: {}", e);
        }
        return "Dados já existentes em nossa base!"
    }

    fun getAll(pageable: Pageable): Page<Map<String, Any>> {
        return userRepository.findAllWithStacks(pageable)
    }

    fun findById(id: UUID): Any? {
        return try {
            userRepository.findByIdWithStacksInTwoTables(id);
        } catch (ex: Exception) {
            "User id not found"
        }
    }

    fun update(id: UUID, user: UsersDTO): Any? {
        val userFound = userRepository.findById(id).orElse(null)
        userFound.name = user.name;
        userFound.nickName = user.nickName;
        userFound.birthDate = user.birthDate;
        userFound.stack = user.stack;
        userRepository.save(userFound)
        val userStack = UserStack(userFound, user.stack.first())
        userStackRepository.save(userStack)

        return ResponseUserDTO(userFound)
    }

    fun deleteById(id: UUID): Any? {
        return try {
            userRepository.deleteById(id);
        } catch (ex: Exception) {
            "User id not found"
        }
    }

}
