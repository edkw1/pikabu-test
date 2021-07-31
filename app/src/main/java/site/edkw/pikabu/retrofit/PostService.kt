package site.edkw.pikabu.retrofit

import retrofit2.Call
import retrofit2.http.GET
import site.edkw.pikabu.models.Post


interface PostService {

    @GET("feed.php")
    fun fetchPosts(): Call<List<Post>>
}