package ai.ftech.travelluxury.data.model.selectroom

class Room(
    val id: Int,
    val name: String,
    val guessNumber: Int,
    val bedType: String,
    val breakfast: String,
    val refund: String,
    val price: Int,
    val imageList: List<String>
)
