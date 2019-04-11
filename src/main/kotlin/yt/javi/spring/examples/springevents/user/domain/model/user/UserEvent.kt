package yt.javi.spring.examples.springevents.user.domain.model.user

import yt.javi.spring.examples.springevents.user.domain.ActionEvent


data class UserEvent(val userId: String, val username: String, val email: String, val action: ActionEvent)
