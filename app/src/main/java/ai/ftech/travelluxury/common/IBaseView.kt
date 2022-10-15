package ai.ftech.travelluxury.common

interface IBaseView {
    fun showLoading(
        bigMessage: String = "Loading...",
        smallMessage: String = "Please wait a moment"
    )

    fun hideLoading()
}
