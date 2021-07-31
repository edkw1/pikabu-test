package site.edkw.pikabu.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import site.edkw.pikabu.R
import site.edkw.pikabu.viewmodels.PostsListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var postsListFragment: PostsListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPostsFragment()
    }

    private fun initPostsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_host, postsListFragment)
            .addToBackStack(null)
            .commit()
    }

}