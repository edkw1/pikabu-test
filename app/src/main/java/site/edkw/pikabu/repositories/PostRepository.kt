package site.edkw.pikabu.repositories

import site.edkw.pikabu.models.Post

interface PostRepository {

    suspend fun getPosts(): List<Post>
}