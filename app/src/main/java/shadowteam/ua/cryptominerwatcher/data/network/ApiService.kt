package shadowteam.ua.cryptominerwatcher.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import shadowteam.ua.cryptominerwatcher.data.network.model.CoinInfoJsonObjectDto
import shadowteam.ua.cryptominerwatcher.data.network.model.CoinListNameDto

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinsList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) toSymbol: String = CURRENCY
    ): CoinListNameDto

    @GET("pricemultifull")
    suspend fun getCoinFullInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fromSymbol: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) toSymbol: String = CURRENCY
    ): CoinInfoJsonObjectDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}