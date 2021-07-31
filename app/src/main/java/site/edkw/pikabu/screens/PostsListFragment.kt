package site.edkw.pikabu.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import site.edkw.pikabu.R
import site.edkw.pikabu.viewmodels.PostsListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class PostsListFragment @Inject constructor(): Fragment() {

    private val postsListViewModel: PostsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postsListViewModel.getPosts()
        postsListViewModel.posts.observe(viewLifecycleOwner){ posts ->
            posts?.forEach {
                Log.d("Test", "Post ${it.body}")
            }
        }
    }

}