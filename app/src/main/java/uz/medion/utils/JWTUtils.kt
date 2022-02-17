package uz.medion.utils

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.nio.charset.Charset

class JWTUtils {

    @Throws(Exception::class)
    fun decoded(JWTEncoded: String): String {
        val token = JWTEncoded.split(".").toTypedArray()
        //token[0] == Header: {"alg":"HS512"}
        //token[1] == Body:   {"sub":"user@user.com",
        //                     "roles":["CLIENT"],
        //                     "userId":2,
        //                     "jti":"bcc8aa1d32f74b05a3a011e6d0f68f6e",
        //                     "iat":1643040194,
        //                     "exp":1652040194
        //                     }
        return getJson(token[1])
    }

    @Throws(UnsupportedEncodingException::class)
    fun getJson(strEncoded: String): String {
        val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, Charset.forName("UTF-8"))
    }
}