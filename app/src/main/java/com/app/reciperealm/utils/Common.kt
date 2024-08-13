package com.app.reciperealm.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.app.reciperealm.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun ImageView.loadImageFromUrl(
    aImageUrl: String? = "",
    userImage: Boolean = false
) {
    val placeholder = if (userImage) R.drawable.white_background else R.drawable.white_background
    if (!aImageUrl.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(Uri.parse(aImageUrl))
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(placeholder)
            .fallback(R.color.white) // Set the white background color as a fallback
            .into(this)
    } else {
        displayBlankImage(this.context, placeholder)
    }
}

fun ImageView.displayBlankImage(aContext: Context, aPlaceHolderImage: Int) {
    Glide.with(aContext)
        .load(aPlaceHolderImage)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .fallback(R.color.white) // Set the white background color as a fallback
        .into(this)
}


fun formatDate(dateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("UTC") // Set timezone to UTC
    val date = dateFormat.parse(dateString)

    val currentDate = Calendar.getInstance().apply {
        time = Date()
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val givenDate = Calendar.getInstance().apply {
        time = date
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val dateFormatter = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())

    return when (givenDate) {
        currentDate -> {
            timeFormat.format(date)
        }

        currentDate.apply { add(Calendar.DAY_OF_YEAR, -1) } -> {
            "Yesterday"
        }

        else -> {
            dateFormatter.format(date)
        }
    }

}
//
//fun extractEmojis(input: String): String {
//    val emojis = EmojiParser.extractEmojis(input)
//    return if (emojis.isNotEmpty()) emojis.first() else ""
//}

fun toCamelCase(input: String): String {
    val words = when {
        input.contains(" ") -> input.split(" ")
        input.contains("_") -> input.split("_")
        else -> listOf(input)
    }

    if (words.isEmpty()) {
        return ""
    }

    val camelCaseWords = words.mapIndexed { index, word ->
        word.substring(0, 1).uppercase() + word.substring(1).lowercase()
    }

    return camelCaseWords.joinToString("")
}

fun String.extractCountryCode(): String {
    val pattern = Regex("([a-zA-Z]+-[a-zA-Z]+)-.*")
    val matchResult = pattern.find(this)
    return matchResult?.groupValues?.get(1) ?: "en-US"
}
