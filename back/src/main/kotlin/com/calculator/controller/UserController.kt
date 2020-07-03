package com.calculator.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import com.calculator.model.UserRepository
import com.calculator.model.User
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/user")
class UserController(@Autowired private val userRepository: UserRepository) {

    @GetMapping("/health")
    fun greet(): String {
        return "Hello There!"
    }

    //gets all users
    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAll()

    //creates a user
    @PostMapping
    fun createUser(@RequestBody user: User): User = userRepository.save(user)

    //gets a single user
    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: String): ResponseEntity<User> =
            userRepository.findById(userId).map {
                ResponseEntity.ok(it)
            }.orElse(ResponseEntity.notFound().build())

    //updates a user
    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: String, @RequestBody updatedUser: User): ResponseEntity<User> =
            userRepository.findById(userId).map {
                val newUser = it.copy(id = updatedUser.id, name = updatedUser.name, email = updatedUser.email)
                ResponseEntity.ok().body(userRepository.save(newUser))
            }.orElse(ResponseEntity.notFound().build())

    // deletes a user
    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: String): ResponseEntity<Void> =
            userRepository.findById(userId).map {
                userRepository.delete(it)
                ResponseEntity<Void>(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())
}
