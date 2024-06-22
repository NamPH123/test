package com.namseox.app_tcare.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.namseox.app_tcare.MainActivity
import com.namseox.app_tcare.databinding.FragmentUserBinding
import com.namseox.app_tcare.view.base.AbsBaseFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class UserFragment : Fragment() {
    lateinit var binding: FragmentUserBinding
    lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    var avt = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }
    val buffer = ByteArray(8 * 1024)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Glide.with(this).load(avt).into(binding.imvAvt)
        binding.imvAvt.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
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
                    Glide.with(this).load(avt).into(binding.imvAvt)
                }catch (e: Exception) {
                    e.printStackTrace()
                    outputDir = null
                }finally {
                    try {
                        inputStream!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
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