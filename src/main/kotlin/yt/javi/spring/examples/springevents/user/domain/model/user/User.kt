package yt.javi.spring.examples.springevents.user.domain.model.user

import java.util.UUID
import java.util.UUID.randomUUID


data class UserId private constructor(val id: UUID) {
    companion object {
        fun fromString(id: String) = UserId(id = UUID.fromString(id))

        fun random() = UserId(randomUUID())
    }
}

data class Email(val value: String) {
    override fun toString() = value
}

data class Username(val value: String) {
    override fun toString() = value
}

data class User private constructor(val userId: UserId, val username: Username, val email: Email) {
    companion object {
        fun create(id: String?, username: String, email: String) = User(
            id?.let { UserId.fromString(id) } ?: UserId.random(),
                Username(username),
                Email(email)
        )
    }
}

