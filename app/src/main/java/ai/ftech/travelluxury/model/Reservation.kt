package ai.ftech.travelluxury.model

data class Reservation(
    val id: Int,
    val totalPrice: Long,
    val status: String,
    val startDate: String,
    val endDate: String
)
