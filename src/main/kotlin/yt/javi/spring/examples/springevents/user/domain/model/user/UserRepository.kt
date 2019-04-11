package yt.javi.spring.examples.springevents.user.domain.model.user

interface UserRepository {
    fun save(user: User)

    fun getById(userId: UserId)

    fun remove(user: User)
}