package a.hw1.ekay

import android.app.Activity
import android.content.Intent
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Activity.finishAndRemoveTaskCompat() {
    if (android.os.Build.VERSION.SDK_INT >= 21) {
        finishAndRemoveTask()
    } else {
        val intent = Intent(this, ExitHelper::class.java)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NO_ANIMATION or
                    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

        startActivity(intent)
    }
    finishAffinity()
}