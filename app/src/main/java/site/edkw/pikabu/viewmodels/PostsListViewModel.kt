package site.edkw.pikabu.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import site.edkw.pikabu.models.Post
import site.edkw.pikabu.repositories.PostRepository
import javax.inject.Inject

@HiltViewModel
class PostsListViewModel @Inject constructor(
    private var postRepository: PostRepository,
) : ViewModel(), LifecycleObserver {

    private var _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private var _loaded = MutableLiveData<Boolean>()
    val loaded: LiveData<Boolean>
        get() = _loaded

    private var _errors = MutableLiveData<Boolean>()
    val errors: LiveData<Boolean>
        get() = _errors

    fun getPosts() {
        viewModelScope.launch {
            try {
                val result = postRepository.getPosts()
                _posts.postValue(result)
            } catch (e: Exception) {
                _errors.postValue(true)
                e.printStackTrace()
            } finally {
                _loaded.postValue(true)
            }
        }
    }

}