package shadowteam.ua.cryptominerwatcher.data.network

import android.util.Log
import retrofit2.http.*
import shadowteam.ua.cryptominerwatcher.data.maper.RaveOsMapper
import shadowteam.ua.cryptominerwatcher.data.network.model.coinmodel.CoinInfoJsonObjectDto
import shadowteam.ua.cryptominerwatcher.data.network.model.coinmodel.CoinListNameDto
import shadowteam.ua.cryptominerwatcher.data.network.model.raveosmodel.RaveOsDto
import shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel.TwoMinerRootDto
import java.security.Signature

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

    @GET("https://eth.2miners.com/api/accounts/{walletid}")
    suspend fun getTwoMinersAccount(@Path(WALLET_PATH) wallet:String): TwoMinerRootDto

    @GET("https://oapi.raveos.com/v2/worker/list")
    suspend fun getRaveOsWorkersList(
        @HeaderMap headers: Map<String, String>): RaveOsDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"


        private const val CURRENCY = "USD"
        private const val WALLET_PATH = "walletid"

        fun createMapRaveOsListWorkerRequest(apiKey: String, secretKey: String): Map<String,String>{
            return mutableMapOf<String, String>().apply {
                val apiSignature = RaveOsMapper().generateSignature(
                    apiPatch = API_PATCH_WORKER_LIST,
                    secretKey = secretKey,
                    publicKey = apiKey
                )
                put(API_KEY, apiKey)
                put(API_SIGNATURE, apiSignature)
            }
        }
        private const val API_KEY = "api-key"
        private const val API_SIGNATURE = "api-signature"
        private const val API_PATCH_WORKER_LIST = "/v2/worker/list"
    }
}