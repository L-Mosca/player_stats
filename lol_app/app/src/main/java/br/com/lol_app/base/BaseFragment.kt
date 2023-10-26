package br.com.lol_app.base

import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.StringRes
import androidx.appcompat.R
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import br.com.lol_app.databinding.IncludeLoadingBinding
import br.com.lol_app.utils.LoadingType
import com.google.android.material.snackbar.Snackbar

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    abstract val bindingInflater: (LayoutInflater) -> VB
    private var viewBinding: ViewBinding? = null

    val binding: VB
        get() = viewBinding as VB
    private var currentToast: Toast? = null
    private var currentSnackBar: Snackbar? = null

    abstract val viewModel: BaseViewModel
    abstract fun initViews()
    abstract fun initObservers()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewBinding = bindingInflater.invoke(inflater)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            onErrorMessage(it)
        }


        initObservers()
        initViews()
    }

    open fun onErrorMessage(it: Any?) {
        when (it) {
            is Int -> showShortToast(it)
            is String -> showShortToast(it)
        }
    }

    private fun showShortToast(@StringRes stringResId: Int) {
        showShortToast(getString(stringResId))
    }

    fun showLongToast(@StringRes stringResId: Int) {
        showLongToast(getString(stringResId))
    }

    fun showShortToast(message: String) {
        showToast(message, Toast.LENGTH_SHORT)
    }

    fun showLongSnackBar(message: String) {
        showSnackBar(message, Snackbar.LENGTH_LONG)
    }

    fun showShortSnackBar(message: String) {
        showSnackBar(message, Snackbar.LENGTH_SHORT)
    }

    fun showLongToast(message: String) {
        showToast(message, Toast.LENGTH_LONG)
    }

    fun setBackNavigation(onBackPressed: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback {
            onBackPressed.invoke()
        }
    }

    /**
     * Shows a PopupMenu
     *
     * @param anchorView View that the PopupMenu will be anchored to
     * @param customLogic Use this to add the desired options to your PopupMenu as well
     * as the action to be performed after those options are clicked
     *
     *//*fun showPopupMenu(anchorView: View, customLogic: (popupMenu: PopupMenu) -> Unit) {
        val popupMenu = PopupMenu(
            requireContext(),
            anchorView,
            Gravity.END,
            R.attr.popupMenuStyle,
            R.style.Base_PopupMenu
        )

        customLogic.invoke(popupMenu)

        try {
            val fieldPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldPopup.isAccessible = true
            popupMenu.show()
        } catch (exception: Exception) {
            exception.printStackTrace()
        } finally {
            popupMenu.show()
        }
    }*/

    fun showLoading(
        loadingView: IncludeLoadingBinding,
        isLoading: Boolean = true,
        loadingType: LoadingType = LoadingType.MAIN_LOADING
    ) {
        with(loadingView) {
            if (isLoading) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    clLoading.isVisible = true
                    val imageRes =
                        if (loadingType == LoadingType.SECONDARY_LOADING)
                            br.com.lol_app.R.drawable.gif_tea_loading
                        else br.com.lol_app.R.drawable.gif_loading

                    lavLoading.isVisible = false
                    ivLoading.isVisible = true
                    val source = ImageDecoder.createSource(resources, imageRes)
                    val drawable = ImageDecoder.decodeDrawable(source)
                    ivLoading.setImageDrawable(drawable)
                    (drawable as? AnimatedImageDrawable)?.start()
                } else {
                    clLoading.isVisible = true
                    lavLoading.isVisible = true
                    ivLoading.isVisible = false
                }
            } else {
                clLoading.isVisible = false
                lavLoading.isVisible = false
                ivLoading.isVisible = false
            }
        }
    }

    private fun showToast(message: String, duration: Int) {
        currentToast?.cancel()
        currentToast = Toast.makeText(context, message, duration)
        currentToast?.show()
    }

    private fun showSnackBar(message: String, duration: Int) {
        currentSnackBar?.dismiss()
        currentSnackBar = Snackbar.make(binding.root, message, duration).setBackgroundTint(
            ContextCompat.getColor(
                requireContext(), R.color.primary_dark_material_dark
            )
        ).setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.primary_dark_material_light
            )
        )
        currentSnackBar?.show()
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }

}