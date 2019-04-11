package yt.javi.spring.examples.springevents.user.domain.model.user

interface UserEventPublisher {
    fun publishEvent(event: UserEvent)
}