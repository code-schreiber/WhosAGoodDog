package com.toolslab.gooddog.view.mydog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.facebook.drawee.view.SimpleDraweeView
import com.toolslab.gooddog.R
import com.toolslab.gooddog.view.base.BaseFragment
import javax.inject.Inject

class MyDogFragment : BaseFragment(), MyDogContract.View {

    @BindView(R.id.fragment_my_dog_breed_text_view)
    internal lateinit var dogBreed: TextView

    @BindView(R.id.fragment_my_dog_image)
    internal lateinit var dogImage: SimpleDraweeView

    @Inject
    internal lateinit var presenter: MyDogContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bind(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_my_dog, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onDestroy() {
        presenter.unbind(this)
        super.onDestroy()
    }

    override fun setBreedText(breed: String) {
        dogBreed.text = breed
    }

    override fun setImageUrl(imageUrl: String) {
        dogImage.setImageURI(imageUrl)
    }

    override fun showUnavailableError() {
        showMessage(getString(R.string.error_service_unavailable))
    }

    @OnClick(R.id.fragment_my_dog_thats_not_my_dog)
    internal fun onThatsNotMyDogClicked() {
        presenter.onThatsNotMyDogClicked()
    }

}
