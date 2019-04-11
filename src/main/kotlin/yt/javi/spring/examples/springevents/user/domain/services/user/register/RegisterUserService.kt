package yt.javi.spring.examples.springevents.user.domain.services.user.register

import yt.javi.spring.examples.springevents.user.domain.ActionEvent
import yt.javi.spring.examples.springevents.user.domain.model.user.User
import yt.javi.spring.examples.springevents.user.domain.model.user.UserEvent
import yt.javi.spring.examples.springevents.user.domain.model.user.UserEventPublisher
import yt.javi.spring.examples.springevents.user.domain.model.user.UserRepository

data class RegisterUserRequest(val username: String, val email: String)

class RegisterUserService(private val repository: UserRepository, private val eventPublisher: UserEventPublisher) {
    operator fun invoke(request: RegisterUserRequest) {
        val user = User.create(
            id = null,
            username = request.username,
            email = request.email
        )

        repository.save(user)
        eventPublisher.publishEvent(user.toEvent())
    }

    fun User.toEvent() = UserEvent(
        userId = userId.toString(),
        username = username.toString(),
        email = email.toString(),
        action = ActionEvent.CREATE
    )
}