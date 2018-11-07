package a.hw1.ekay

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.Toast


class AndroidDeviceAdapter(internal var context: Context, private val devices: Array<String>) :
    RecyclerView.Adapter<AndroidDeviceAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var simpleCheckedTextView: CheckedTextView =
            view.findViewById<View>(R.id.simpleCheckedTextView) as CheckedTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = parent.inflate(R.layout.row_item, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.simpleCheckedTextView.text = devices[position]

        // perform on Click Event Listener on CheckedTextView
        holder.simpleCheckedTextView.setOnClickListener {

            val value = holder.simpleCheckedTextView.isChecked
            if (value) {
                // set check mark drawable and set checked property to false
                holder.simpleCheckedTextView.setCheckMarkDrawable(R.drawable.check_ic)
                holder.simpleCheckedTextView.isChecked = false
                Toast.makeText(context, "un-Checked", Toast.LENGTH_LONG).show()
            } else {

                // set check mark drawable and set checked property to true
                holder.simpleCheckedTextView.setCheckMarkDrawable(R.drawable.check)
                holder.simpleCheckedTextView.isChecked = true
                Toast.makeText(context, "Checked", Toast.LENGTH_LONG).show()
            }
        }


    }


    override fun getItemCount() = devices.size
}
