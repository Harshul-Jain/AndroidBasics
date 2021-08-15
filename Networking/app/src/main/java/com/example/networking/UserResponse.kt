package com.example.networking

data class UserResponse(
	val totalCount: Int? = null,
	val incompleteResults: Boolean? = null,
	val items: List<User>? = null
)


