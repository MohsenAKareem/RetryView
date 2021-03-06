package sa.ibtikar.retryview.utils

import android.os.SystemClock
import android.view.View

/**
 * Created by Mohsen Abdelkareem on 3/8/2021.
 * Email : mohsen.abdelkareem@ibtikar.net.sa
 * Github : https://github.com/MohsenAKareem
 */
class SafeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}