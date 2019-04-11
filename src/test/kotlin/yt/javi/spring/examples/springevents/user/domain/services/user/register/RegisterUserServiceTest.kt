package yt.javi.spring.examples.springevents.user.domain.services.user.register

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import yt.javi.spring.examples.springevents.user.domain.ActionEvent
import yt.javi.spring.examples.springevents.user.domain.model.user.Email
import yt.javi.spring.examples.springevents.user.domain.model.user.User
import yt.javi.spring.examples.springevents.user.domain.model.user.UserEvent
import yt.javi.spring.examples.springevents.user.domain.model.user.UserEventPublisher
import yt.javi.spring.examples.springevents.user.domain.model.user.UserRepository
import yt.javi.spring.examples.springevents.user.domain.model.user.Username

internal class RegisterUserServiceTest {
    private val repository: UserRepository = mockk()

    private val eventPublisher: UserEventPublisher = mockk()

    private lateinit var registerUserService: RegisterUserService

    @BeforeEach
    fun setUp() {
        registerUserService = RegisterUserService(repository, eventPublisher)
    }

    @Test
    internal fun `it should save a newly registered user into storage`() {
        val username = "test"
        val email = "test@mail.com"
        val request = RegisterUserRequest(username = username, email = email)
        val user = slot<User>()
        val event = slot<UserEvent>()
        every { repository.save(capture(user)) } just Runs
        every { eventPublisher.publishEvent(capture(event)) } just Runs

        registerUserService(request)

        assertThat(user.captured)
            .hasFieldOrPropertyWithValue("username", Username(username))
            .hasFieldOrPropertyWithValue("email", Email(email))

        assertThat(event.captured)
            .hasFieldOrPropertyWithValue("username", username)
            .hasFieldOrPropertyWithValue("email", email)
            .hasFieldOrPropertyWithValue("action", ActionEvent.CREATE)
    }
}