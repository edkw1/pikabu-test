package site.edkw.pikabu.di.retrofit

import retrofit2.Call
import retrofit2.http.GET
import site.edkw.pikabu.models.Post


interface PostService {

    @GET("feed.php")
    suspend fun fetchPosts(): List<Post>
}