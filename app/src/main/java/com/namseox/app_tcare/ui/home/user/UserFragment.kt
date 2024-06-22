package com.namseox.app_tcare.ui.home.user

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.namseox.app_tcare.R
import com.namseox.app_tcare.databinding.FragmentUserBinding
import com.namseox.app_tcare.ui.home.MainActivity
import com.namseox.app_tcare.ui.infomanager.InfoManagerActivity
import com.namseox.app_tcare.ui.login.LoginActivity
import com.namseox.app_tcare.ui.resetpass.ResetPassActivity
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.utils.showToast
import com.namseox.app_tcare.view.base.AbsBaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : AbsBaseFragment<FragmentUserBinding, MainActivity>(R.layout.fragment_user) {
    @Inject
    lateinit var sharedPreferenceUtils: SharedPreferenceUtils

    lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    val buffer = ByteArray(8 * 1024)
    var avt = ""
    override fun initView() {

    }

    override fun setView() {
        avt = sharedPreferenceUtils.getStringValue(Const.AVT)!!
        Glide.with(this).load(avt).error(R.drawable.ic_my_account).into(binding.imvAvt)
    }

    override fun setClick() {
        binding.apply {
            tvCaNhan.setOnClickListener {

            }
            tvPass.setOnClickListener {
                startActivity(Intent(requireContext(), ResetPassActivity::class.java))
            }
            binding.cv4.setOnClickListener {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                mActivity.finish()
            }
            binding.cv1.setOnClickListener {
                startActivity(Intent(requireContext(), InfoManagerActivity::class.java))
            }
        }


        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                avt = it.toString()

                val outputDirectory = File(activity?.filesDir, "Avata")
                if (!outputDirectory.exists()) {
                    outputDirectory.mkdirs()
                }
                var inputStream: InputStream? = null
                var outputDir: File? = null
                try {
                    outputDir = File(
                        outputDirectory,
                        getFileNameFromUri(requireContext(), Uri.parse(avt))
                    )
                    inputStream = activity?.contentResolver?.openInputStream(Uri.parse(avt))
                    inputStream.use { input ->
                        outputDir!!.parentFile?.mkdirs()
                        FileOutputStream(outputDir).use { output ->
                            var bytesRead: Int
                            while (inputStream!!.read(buffer)
                                    .also { bytesRead = it } != -1
                            ) {
                                output.write(buffer, 0, bytesRead)
                            }
                            output.flush()
                        }
                    }
                    avt = outputDir.path
                    sharedPreferenceUtils.putStringValue(Const.AVT, avt)
                    Glide.with(this).load(avt).into(binding.imvAvt)
                    showToast(requireContext(), R.string.change_avt_success)
                } catch (e: Exception) {
                    e.printStackTrace()
                    outputDir = null
                } finally {
                    try {
                        inputStream!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        binding.imvAvt.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    fun getFileNameFromUri(context: Context, uri: Uri): String? {
        var fileName: String? = null

        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    val displayNameIndex =
                        it.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME)
                    if (displayNameIndex != -1) {
                        fileName = it.getString(displayNameIndex)
                    }
                }
            }
        } else if (uri.scheme == "file") {
            fileName = File(uri.path).name
        } else if (uri.scheme == "android.resource") {

        }
        return fileName
    }
}