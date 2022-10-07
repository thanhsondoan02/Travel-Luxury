package ai.ftech.travelluxury.common

interface IBaseView {
    fun showLoading(message: String = "Loading...")
    fun hideLoading()
}
