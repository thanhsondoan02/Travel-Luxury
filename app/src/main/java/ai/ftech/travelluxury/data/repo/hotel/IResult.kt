package ai.ftech.travelluxury.data.repo.hotel

interface IResult {
    fun onRepoSuccess(data: Any)
    fun onRepoFail(message: String)
}
