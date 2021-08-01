package site.edkw.pikabu.screens

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import site.edkw.pikabu.R

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleTextView: TextView = itemView.findViewById(R.id.postTitle)

}