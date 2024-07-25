package com.example.quizzapp.data.model

import com.example.quizzapp.data.database.entities.UserEntity

data class User(
    val id : Int,
    val name : String
)
fun User.toUserEntity(): UserEntity {
    return UserEntity(id = this.id, name = this.name)
}

