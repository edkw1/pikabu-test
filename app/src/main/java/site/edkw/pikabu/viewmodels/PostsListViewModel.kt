package site.edkw.pikabu.viewmodels

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.edkw.pikabu.retrofit.PostService
import site.edkw.pikabu.models.Post
import javax.inject.Inject

@HiltViewModel
class PostsListViewModel @Inject constructor(
    private var postService: PostService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver {

    private var _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    fun getPosts() {
            val call = postService.fetchPosts()

            call.enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    val posts = response.body()
                    _posts.postValue(posts!!)
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("Test", "error")

                }

            })
            //_posts.postValue(Collections.emptyList())
    }

}