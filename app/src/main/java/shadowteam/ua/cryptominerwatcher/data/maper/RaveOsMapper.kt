package shadowteam.ua.cryptominerwatcher.data.maper

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class RaveOsMapper{

     fun generateSignature(secretKey: String, publicKey: String, apiPatch:String): String {
        val algorithm = "HmacSHA512"
        val publicKeyAndPatch = apiPatch+publicKey
        return Mac.getInstance(algorithm)
            .apply { init(SecretKeySpec(secretKey.toByteArray(), algorithm)) }
            .run { doFinal(publicKeyAndPatch.toByteArray()) }
            .let { it.toHexString() } }

    private fun ByteArray.toHexString() = joinToString("") {
        (0xFF and it.toInt()).toString(16).padStart(2, '0')
    }
}