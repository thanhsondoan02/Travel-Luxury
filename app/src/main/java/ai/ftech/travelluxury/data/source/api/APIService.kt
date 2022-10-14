package ai.ftech.travelluxury.data.source.api

class APIService {
    companion object {
        private const val BASE_URL: String = "http://192.168.5.231:8099"

        fun base(): DataService {
            return APIRetrofitClient.getClient(BASE_URL).create(DataService::class.java)
        }
    }
}
