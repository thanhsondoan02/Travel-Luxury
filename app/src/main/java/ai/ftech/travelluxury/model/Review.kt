package ai.ftech.travelluxury.model

data class Review(
    val id: Int,
    val rating: Int,
    val title: String,
    val content: String,
    val images: String,
    val hotelId: Int
)
