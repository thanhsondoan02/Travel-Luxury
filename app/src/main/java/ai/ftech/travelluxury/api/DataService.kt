package ai.ftech.travelluxury.api

import ai.ftech.travelluxury.model.home.Model
import retrofit2.Call
import retrofit2.http.GET

interface DataService {

    @GET("b74fe13f8f09259a60b0/")
    fun getData(): Call<Model>
}
