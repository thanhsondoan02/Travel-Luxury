package ai.ftech.travelluxury.api

class APIService {
    companion object {
        private const val BASE_URL: String = "https://api.npoint.io/"

        fun base(): DataService {
            return APIRetrofitClient.getClient(BASE_URL).create(DataService::class.java)
        }
    }
}
