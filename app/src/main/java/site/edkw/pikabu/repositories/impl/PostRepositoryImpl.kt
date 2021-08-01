package site.edkw.pikabu.repositories.impl

import site.edkw.pikabu.di.retrofit.PostService
import site.edkw.pikabu.models.Post
import site.edkw.pikabu.repositories.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
            return postService.fetchPosts()
    }
}