package a.hw1.ekay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_text_name.setOnEditorActionListener { v, actionId, _ ->
            var handle = true
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                val defaultCountry = getString(R.string.default_country)
                button_signup.isEnabled = v.text.isNotEmpty() && country_spinner.selectedItem.toString() != defaultCountry
                handle = false
            }
            handle
        }

        edit_text_age.setOnEditorActionListener { v, actionId, _ ->
            var handle = true
            val ageRestriction = 16..100
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (v.text.toString().toInt() !in ageRestriction) {
                    Toast.makeText(applicationContext, "Age restricted to 16-100", Toast.LENGTH_SHORT).show()
                    edit_text_age.text.clear()
                }
                handle = false
            }
            handle
        }

        country_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                button_signup.isEnabled = position != 0 && edit_text_name.text.isNotEmpty()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }


    fun resetWithToast(view: View) {
        Toast.makeText(applicationContext, "User created!", Toast.LENGTH_SHORT).show()
        edit_text_name.text.clear()
        edit_text_age.text.clear()
        country_spinner.setSelection(0)
        button_signup.isEnabled = false
    }
}
