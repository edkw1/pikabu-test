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
import site.edkw.pikabu.databinding.FragmentPostInfoBinding
import site.edkw.pikabu.models.Post
import site.edkw.pikabu.viewmodels.PostInfoViewModel
import java.lang.RuntimeException
import javax.inject.Inject

@AndroidEntryPoint
class PostInfoFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentPostInfoBinding? = null
    val binding: FragmentPostInfoBinding
        get() = _binding ?: throw RuntimeException("fragment binding is null!")


    private val postInfoViewModel: PostInfoViewModel by viewModels()
    private val args by navArgs<PostInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData(view)
        setPostValuesToView(args.post, view)
    }

    private fun setPostValuesToView(post: Post, view: View) {

        with(post) {
            binding.postInfoTitle.text = title
            binding.postInfoText.text = body
            if (!images.isNullOrEmpty()) {
                postInfoViewModel.loadImages(images)
            } else {
                hideProgressBar()
            }
        }
    }

    private fun subscribeToLiveData(view: View) {

        postInfoViewModel.images.observe(viewLifecycleOwner) { bitmapImages ->
            binding.imageContainer.removeAllViews()

            if (bitmapImages.isNotEmpty()) {
                bitmapImages.forEach { bitmapImage ->
                    val imageView = createImageView(bitmapImage)
                    binding.imageContainer.addView(imageView)
                }
            }
        }

        postInfoViewModel.loaded.observe(viewLifecycleOwner) { loaded ->
            Log.d("loaded", "loaded")
            if (loaded) {
               hideProgressBar()
            }
        }
    }


    private fun hideProgressBar(){
        binding.imageProgressBar.visibility = ProgressBar.GONE
    }


    private fun createImageView(bitmap: Bitmap): ImageView {
        val imageView = ImageView(context)
        imageView.setImageBitmap(bitmap)
        imageView.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        imageView.adjustViewBounds = true
        return imageView
    }
}