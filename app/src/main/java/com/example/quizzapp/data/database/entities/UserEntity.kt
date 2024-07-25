package com.example.quizzapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizzapp.data.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)
fun UserEntity.toUser(): User {
    return User(id = this.id, name = this.name)
}