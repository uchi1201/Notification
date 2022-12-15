package com.android.example.notification.network.base



import com.android.example.notification.BuildConfig
import com.android.example.notification.constant.ErrorCode
import com.android.example.notification.network.exception.NetworkException
import com.android.example.notification.network.interceptor.CommonRequestInterceptor
import com.android.example.notification.network.interceptor.CommonResponseInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.ParameterizedType
import java.util.concurrent.TimeUnit

/**
 * ネットワーク請求
 */
abstract class BaseNetworkApi<I>(private val baseUrl: String) : IService<I> {

    protected val service: I by lazy {
        getRetrofit().create(getServiceClass())
    }

    protected open fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getServiceClass(): Class<I> {
        val genType = javaClass.genericSuperclass as ParameterizedType
        return genType.actualTypeArguments[0] as Class<I>
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClient = getCustomOkHttpClient()
        if (null != okHttpClient) {
            return okHttpClient
        }
        return defaultOkHttpClient
    }

    protected open fun getCustomOkHttpClient(): OkHttpClient? {
        return null
    }

    protected open fun getCustomInterceptor(): Interceptor? {
        return null
    }

    protected suspend fun <T> getResult(block: suspend () -> BaseResponse<T>): Result<T> {
        for (i in 1..RETRY_COUNT) {
            try {
                val response = block()
                if (response.data == null) {
                    throw NetworkException.of(ErrorCode.VALUE_IS_NULL, "response value is null")
                }
                return Result.success(response.data)
            } catch (throwable: Throwable) {
                if (throwable is NetworkException) {
                    return Result.failure(throwable)
                }
                if ((throwable is HttpException && throwable.code() == ErrorCode.UNAUTHORIZED)) {

                }
            }
        }
        return Result.failure(NetworkException.of(ErrorCode.VALUE_IS_NULL, "unknown"))
    }


    companion object {
        const val TAG = "BaseNetworkApi"
        private const val RETRY_COUNT = 2
        private val defaultOkHttpClient by lazy {
            val builder = OkHttpClient.Builder()
                .callTimeout(10L, TimeUnit.SECONDS)
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

            builder.addInterceptor(CommonRequestInterceptor())
            builder.addInterceptor(CommonResponseInterceptor())
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                builder.addInterceptor(loggingInterceptor)
            }

            builder.build()
        }
    }
}