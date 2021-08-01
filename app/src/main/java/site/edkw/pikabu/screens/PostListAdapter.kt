package site.edkw.pikabu.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.edkw.pikabu.R
import site.edkw.pikabu.models.Post


class PostListAdapter(
    private val posts: List<Post>
) : RecyclerView.Adapter<PostViewHolder>() {

    var onPostClickListener: ((Post) -> Unit)? = null


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.post_list_item, viewGroup, false)
        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]

        holder.titleTextView.text = post.title
        holder.itemView.setOnClickListener {
            onPostClickListener?.invoke(post)
            true
        }
    }


    override fun getItemCount() = posts.size
}