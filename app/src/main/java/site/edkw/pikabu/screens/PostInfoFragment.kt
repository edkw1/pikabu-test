package site.edkw.pikabu.screens

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import site.edkw.pikabu.R
import site.edkw.pikabu.models.Post
import site.edkw.pikabu.viewmodels.PostInfoViewModel
import javax.inject.Inject

@AndroidEntryPoint
class PostInfoFragment @Inject constructor() : Fragment() {

    private val postInfoViewModel: PostInfoViewModel by viewModels()
    private val args by navArgs<PostInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData(view)
        setPostValuesToView(args.post, view)
    }

    private fun setPostValuesToView(post: Post, view: View) {
        val postInfoTitle = view.findViewById<TextView>(R.id.postInfoTitle)
        val postInfoText = view.findViewById<TextView>(R.id.postInfoText)
        val progressBar = view.findViewById<ProgressBar>(R.id.imageProgressBar)

        with(post) {
            postInfoTitle.text = title
            postInfoText.text = body
            if (!images.isNullOrEmpty()) {
                postInfoViewModel.loadImages(images)
            }else{
                progressBar.visibility = ProgressBar.GONE
            }
        }
    }

    private fun subscribeToLiveData(view: View) {
        val imageContainer = view.findViewById<LinearLayout>(R.id.imageContainer)
        val progressBar = view.rootView.findViewById<ProgressBar>(R.id.imageProgressBar)

        postInfoViewModel.images.observe(viewLifecycleOwner) { bitmapImages ->
            imageContainer.removeAllViews()

            if (bitmapImages.isNotEmpty()) {
                bitmapImages.forEach { bitmapImage ->
                    val imageView = createImageView(bitmapImage)
                    imageContainer.addView(imageView)
                }
            }
        }

        postInfoViewModel.loaded.observe(viewLifecycleOwner){loaded ->
            Log.d("loaded", "loaded")
            if(loaded){
                progressBar.visibility = ProgressBar.GONE
            }
        }
    }


    private fun createImageView(bitmap: Bitmap): ImageView {
        val imageView = ImageView(context)
        imageView.setImageBitmap(bitmap)
        imageView.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        imageView.adjustViewBounds= true
        return imageView
    }
}