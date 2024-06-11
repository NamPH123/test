package com.namseox.app_tcare.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.TypefaceSpan
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.io.File
import android.net.Uri
import android.os.Parcelable
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import java.io.IOException


fun Activity.showSystemUI(white: Boolean) {
        if (white) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        } else {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        } else {
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }
    }

fun showToast(context: Context, id: Int) {
    SystemUtils.setLocale(context)
    Toast.makeText(context, context.resources.getText(id), Toast.LENGTH_SHORT).show()
}

fun changeColor(
    context: Context,
    text: String,
    color: Int,
    fontfamily: Int,
    textSize: Float
): SpannableString {
    val spannableString = SpannableString(text)
    spannableString.setSpan(
        ForegroundColorSpan(context.getColor(color)),
        0,
        text.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    val font = ResourcesCompat.getFont(context, fontfamily)
    val typefaceSpan = CustomTypefaceSpan("", font)
    spannableString.setSpan(
        typefaceSpan,
        0,
        text.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    spannableString.setSpan(
        RelativeSizeSpan(textSize),
        0,
        text.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableString
}

class CustomTypefaceSpan(private val family: String, private val typeface: Typeface?) :
    TypefaceSpan(family) {

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, typeface)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, typeface)
    }

    private fun applyCustomTypeFace(paint: Paint, tf: Typeface?) {
        if (tf != null) {
            paint.typeface = tf
        } else {
            paint.typeface = Typeface.DEFAULT
        }
    }
}

fun isServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val services = activityManager.getRunningServices(Integer.MAX_VALUE)
    for (service in services) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}

fun dpToPx(dp: Float, context: Context): Float {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
}

fun dpToSp(sp: Float, context: Context): Float {
    val floatSize =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics)
    return floatSize
}

fun Activity.setupWindow() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    getWindow().setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
//    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
}

fun AppCompatActivity.changeFragment(fragment: Fragment, tag: String?, id: Int) {
    var fragmentManager: FragmentManager? = null
    var transaction: FragmentTransaction? = null
    fragmentManager = supportFragmentManager
    transaction = fragmentManager!!.beginTransaction()
    val existingFragment: Fragment? = fragmentManager!!.findFragmentByTag(tag)
    if (existingFragment != null) {
        transaction!!.replace(id, existingFragment)
    } else {
        transaction!!.replace(id, fragment, tag)
        transaction!!.addToBackStack(tag)
    }
    transaction!!.commit()
}

fun shareFile(context: Context, file: File) {
    val fileUri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    Log.d(TAG, "shareFile: $fileUri")
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "audio/*"
    intent.putExtra(Intent.EXTRA_STREAM, fileUri)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    context.startActivity(Intent.createChooser(intent, "Share Audio File"))
}

fun getAllUriInFileAsset(context: Context, filePaths: ArrayList<String>): ArrayList<Uri> {
    val assetUris = ArrayList<Uri>()
    for (filePath in filePaths) {

        var uri = Uri.parse(
            "content://com.keyboard.fonts.emojikeyboard.theme.Provider/" + filePath.replace(
                "file:///android_asset/",
                ""
            )
        )
        assetUris.add(uri)
    }
    return assetUris
}


fun shareImages(context: Context, imageUris: ArrayList<Uri>) {
    val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
        type = "image/*"
        putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(intent, "Share Images"))
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager!!.toggleSoftInputFromWindow(
        view.getApplicationWindowToken(),
        InputMethodManager.SHOW_FORCED, 0
    )
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun getAllUriInFIleAsset(context: Context, path: String): ArrayList<String> {
    val pathList = arrayListOf<String>()
    try {
        val files = context.assets.list(path) ?: arrayOf()
        for (file in files) {
            val fullPath = "file:///android_asset/$path/$file"
            pathList.add(fullPath)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    Log.d(TAG, "getAllPathInFileAsset: ${pathList.size}")
    return pathList
}

fun pickImage(fragment: Fragment?, activity: Activity?) {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    intent.setType("image/*")
    intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*"))
    if (fragment == null) {
        activity!!.startActivityForResult(intent, 1102)
    } else {
        fragment!!.startActivityForResult(intent, 1103)
    }
}

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
fun fileToDrawable(context: Context, filePath: String?): Drawable? {
    val file = File(filePath)
    if (!file.exists()) {
        return null
    }
    val bitmap = BitmapFactory.decodeFile(filePath)
    return BitmapDrawable(context.resources, bitmap)
}

fun Intent.putParcelableExtra(key: String, value: Parcelable) {
    putExtra(key, value)
}

fun setWidthHeight(view: View, width: Int, height: Int) {
    val params = view.layoutParams
    if(width!=0){
        params.width = width
    }
    if(height!=0){
        params.height = height
    }
    view.layoutParams = params
}

fun setLayoutParamParent(view: View, top: Float, right: Float, bottom: Float, left: Float) {
    val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, // width
        LinearLayout.LayoutParams.WRAP_CONTENT // height
    )
    val marginTopInPixels = dpToPx(top, view.context).toInt()
    params.setMargins(0, marginTopInPixels, 0, 0)
    view.layoutParams = params
}

fun setLayoutParam(view: View, top: Float, right: Float, bottom: Float, left: Float) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.setMargins(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
    view.layoutParams = layoutParams
}
fun changeText(context: Context, text: String, color: Int, fontfamily: Int): SpannableString {
    val spannableString = SpannableString(text)
    spannableString.setSpan(
        ForegroundColorSpan(context.getColor(color)),
        0,
        text.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    val font = ResourcesCompat.getFont(context, fontfamily)
    val typefaceSpan = CustomTypefaceSpan("", font)
    spannableString.setSpan(
        typefaceSpan,
        0,
        text.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableString
}

fun onClick(action: () -> Unit) {
    if (System.currentTimeMillis() - Const.lastClickTime >= 500) {
        action()
        Const.lastClickTime = System.currentTimeMillis()
    }
}