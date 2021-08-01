package site.edkw.pikabu.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import site.edkw.pikabu.repositories.ImageRepository
import javax.inject.Inject

@HiltViewModel
class PostInfoViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private var _images = MutableLiveData<List<Bitmap>>()
    val images: LiveData<List<Bitmap>>
        get() = _images

    private var _loaded = MutableLiveData<Boolean>()
    val loaded: LiveData<Boolean>
        get() = _loaded

    fun loadImages(imgs: List<String>){
        viewModelScope.launch(Dispatchers.IO) {
            val bitmaps = imageRepository.getImages(imgs)
            if(!bitmaps.isNullOrEmpty()){
                _images.postValue(bitmaps)
            }
            _loaded.postValue(true)
        }
    }
}