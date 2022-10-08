package ai.ftech.travelluxury.ui.hoteldetail

interface IListener {
    fun onSeeAllPhotos()
    fun onSeeAllReviews()
    fun onSeeAllFacilities()
    fun onSeeAllPolicies()
    fun onSeeAllDescription()
    fun onSelectRoom()
    fun onViewPhoto(index: Int)
}
