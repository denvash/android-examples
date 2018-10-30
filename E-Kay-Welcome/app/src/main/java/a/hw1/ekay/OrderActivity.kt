package a.hw1.ekay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_order_activity.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_activity)

        val welcomeMsg = resources.getString(R.string.welcome_message, intent.getStringExtra("userName"))

        display_user_name.text = welcomeMsg
    }
}
