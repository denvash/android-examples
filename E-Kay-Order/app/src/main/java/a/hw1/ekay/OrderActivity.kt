package a.hw1.ekay

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_activity.*
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi


class OrderActivity : AppCompatActivity() {

    private val androidDevices = arrayOf("MI5", "MI5S", "SAMSUNG", "NEXUS", "ONE PLUS ONE")

    // Box a string (use it as reference) for order message
    inner class LastOrder {
        var str: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_activity)


        // Display welcome message
        val welcomeMsg = resources.getString(R.string.welcome_message, intent.getStringExtra("userName"))
        display_user_name.text = welcomeMsg


        // Setting RecycleView with LinearLayout
        makeOrder_recycleView.layoutManager = LinearLayoutManager(applicationContext)
        makeOrder_recycleView.addItemDecoration(
            DividerItemDecoration(this@OrderActivity, LinearLayoutManager.HORIZONTAL)
        )

        // Setting Adapter
        val lastOrder = LastOrder()
        makeOrder_recycleView.adapter = OrderAdapter(this@OrderActivity, androidDevices, lastOrder)


        // Button Alert-Dialog
        order_button.setOnClickListener {
            AlertDialog.Builder(this@OrderActivity)
                .setTitle("E-Kay")
                .setMessage("Approve order for: ${lastOrder.str}?")
                .setPositiveButton("YES") { _, _ ->
                    Toast.makeText(applicationContext, "order sent!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("NO") { _, _ -> }
                .create()
                .show()
        }
    }


    // Close app at back press
    override fun onBackPressed() {
        finishAndRemoveTaskCompat()
    }
}
