package a.hw1.ekay

import android.app.Activity
import android.os.Bundle

class ExitHelper : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()
    }
}