package ai.ftech.travelluxury.model

data class User(
    val id: Int,
    val name: String,
    val password: String,
    val fullName: String,
    val gender: String,
    val phoneNumber: Int,
    val email: String
)
