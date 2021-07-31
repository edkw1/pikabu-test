package site.edkw.pikabu.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideJacksonConverterFactory(): JacksonConverterFactory{
        return JacksonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(jacksonConverterFactory: JacksonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(jacksonConverterFactory)
            .build()
    }

    @Provides
    fun providePostService(retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }


    companion object {
        const val HOST_URL = "https://pikabu.ru/page/interview/mobile-app/test-api/"
    }

}