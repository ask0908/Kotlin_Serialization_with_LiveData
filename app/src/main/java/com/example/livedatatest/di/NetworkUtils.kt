package com.example.livedatatest.di

import com.example.livedatatest.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkUtils {
    private const val BASE_URL = "https://my-json-server.typicode.com/engincancan/case/"

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )
            )
            .addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Content-Type", "application/json")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
            .build()
    }

    /** encodeDefaults와 유사한 값으로 coerceInputValues가 있다. 이것은 null이 들어간 경우 초기값으로 대체하는 옵션이다 */
    @Singleton
    @Provides
    // Json {} : 선택적으로 제공된 Json 인스턴스에서 구성되고 builderAction으로 조정된 Json 인스턴스를 생성
    fun provideJson() = Json {
        prettyPrint = true          // JSON을 출력할 때 예쁘게 표시되게 설정함 (추가)
        ignoreUnknownKeys = true    // 필드값이 없는 경우 무시. 역직렬화 중 알 수 없는 키가 있으면 이를 무시함
        isLenient = true            // JSON 큰따옴표를 느슨하게 체크함
        encodeDefaults = true       // 기본값이 있는 nullable 프로퍼티에 null 값이 안 들어가게 함
    }

    /** asConverterFactory() : 문자열 기반 페이로드에 코틀린 직렬화를 사용하는 Converter.Factory를 리턴함
     * 코틀린 직렬화는 지원 유형이 매우 유연하기 때문에 이 Converter는 모든 유형을 처리할 수 있다고 가정한다
     * 이것을 다른 것과 혼합하는 경우, 다른 변환기가 해당 타입을 볼 수 있도록 이 인스턴스를 마지막에 추가해야 한다 */
    @ExperimentalSerializationApi   // 실험 버전 API를 의미하는 어노테이션. 없애면 asConverterFactory()에 경고줄 생김
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}