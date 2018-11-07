package a.hw1.ekay

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_activity.*
import kotlinx.android.synthetic.main.activity_order_activity.view.*
import android.widget.AdapterView.OnItemClickListener



class OrderActivity : AppCompatActivity() {

    private val androidDevices = arrayOf("MI5", "MI5S", "SAMSUNG", "NEXUS", "ONE PLUS ONE")

    companion object {
        var last: String = ""
        lateinit var orderButton: Button
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_activity)

        orderButton = order_button

        // Display welcome message
        val welcomeMsg = resources.getString(R.string.welcome_message, intent.getStringExtra("userName"))
        display_user_name.text = welcomeMsg

        // Display devices list
        makeOrder_recycleView.layoutManager = LinearLayoutManager(applicationContext)
        makeOrder_recycleView.addItemDecoration(
            DividerItemDecoration(
                this@OrderActivity,
                LinearLayoutManager.HORIZONTAL
            )
        )
        makeOrder_recycleView.adapter = AndroidDeviceAdapter(this@OrderActivity, androidDevices)

        // Button Alert-Dialog
        order_button.setOnClickListener {
            val builder = AlertDialog.Builder(this@OrderActivity)

            builder
                .setTitle("E-Kay")
                .setMessage("Approve order for:$last?")
                .setPositiveButton("YES") { _, _ ->
                    Toast.makeText(applicationContext, "order sent!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("NO") { _, _ -> }

            val alertDialog = builder.create()
            alertDialog.show()
        }


    }
}
