package site.edkw.pikabu.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import site.edkw.pikabu.R
import site.edkw.pikabu.models.Post
import site.edkw.pikabu.viewmodels.PostsListViewModel
import javax.inject.Inject


@AndroidEntryPoint
class PostsListFragment @Inject constructor() : Fragment() {
    private val postsListViewModel: PostsListViewModel by viewModels()
    private lateinit var postListAdapter: PostListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv: RecyclerView = view.findViewById(R.id.recycler_view)

        setDivider(rv)
        subscribeToLiveData(view, rv)
        postsListViewModel.getPosts()
    }

    private fun setDivider(rv: RecyclerView) {
        val mDividerItemDecoration = DividerItemDecoration(
            rv.context,
            DividerItemDecoration.VERTICAL
        );
        rv.addItemDecoration(mDividerItemDecoration);
    }

    private fun subscribeToLiveData(view: View, rv: RecyclerView) {
        val progressBar = view.findViewById<ProgressBar>(R.id.listProgressBar)


        with(postsListViewModel) {
            posts.observe(viewLifecycleOwner) {
                updateItems(rv, it)
            }
            loaded.observe(viewLifecycleOwner) {
                if (it) {
                    progressBar.visibility = ProgressBar.INVISIBLE
                }
            }
            errors.observe(viewLifecycleOwner) {
                if(it){
                    findNavController().navigate(
                        PostsListFragmentDirections.actionPostsListFragmentToErrorFragment()
                    )
                }
            }
        }
    }

    private fun updateItems(rv: RecyclerView, posts: List<Post>) {
        postListAdapter = PostListAdapter(posts)
        postListAdapter.onPostClickListener = { post ->
            findNavController().navigate(
                PostsListFragmentDirections.actionPostsListFragmentToPostInfoFragment(post)
            )
        }
        rv.adapter = postListAdapter
    }

}