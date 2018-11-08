package a.hw1.ekay

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import kotlinx.android.synthetic.main.activity_order_activity.*


class OrderAdapter(internal var context: Context, private val devices: Array<String>, private val lastOrderRef: OrderActivity.LastOrder) :
    RecyclerView.Adapter<OrderAdapter.OrderHolder>() {

    inner class OrderHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val orderRowItemView: CheckedTextView =
            view.findViewById<View>(R.id.orderCheckedTextView) as CheckedTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val itemView = parent.inflate(R.layout.order_item, false)
        return OrderHolder(itemView)
    }

    override fun getItemCount() = devices.size

    // Manage single CheckedTextView selection
    private lateinit var lastOrder: CheckedTextView
    private var isFirstOrder = true

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {

        // Initialize RecycleView rows on bind
        holder.orderRowItemView.text = devices[position]


        // Manage the rows on click
        holder.orderRowItemView.setOnClickListener {

            val orderButton = (context as OrderActivity).order_button!!
            val currentOrder = holder.orderRowItemView

            // Initialize the late-init var
            if (isFirstOrder) {
                isFirstOrder = !isFirstOrder
                lastOrder = currentOrder
            }

            // Click on other order
            if (lastOrder != currentOrder) {
                lastOrder.setCheckMarkDrawable(R.drawable.check_ic)
                lastOrder.isChecked = false
                lastOrder = currentOrder
            }

            if (currentOrder.isChecked) {
                // set check mark drawable and set checked property to false
                currentOrder.setCheckMarkDrawable(R.drawable.check_ic)
                currentOrder.isChecked = false
                orderButton.isEnabled = false
            } else {
                // set check mark drawable and set checked property to true
                currentOrder.setCheckMarkDrawable(R.drawable.check)
                currentOrder.isChecked = true
                orderButton.isEnabled = true
            }
        }
    }
}
