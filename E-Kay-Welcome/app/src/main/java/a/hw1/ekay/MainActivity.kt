package a.hw1.ekay

import android.content.Intent
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

        edit_text_name.setOnEditorActionListener { _, actionId, _ ->
            var handle = true
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                activeButtonOnLegalState()
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
                    button_sign_up.isEnabled = false
                } else {
                    activeButtonOnLegalState()
                }
                handle = false
            }
            handle
        }

        country_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                activeButtonOnLegalState()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                button_sign_up.isEnabled = false
            }
        }

        button_sign_up.setOnClickListener {
            val intent = Intent(this@MainActivity,OrderActivity::class.java)

            intent.putExtra("userName",edit_text_name.text.toString())
            startActivity(intent)
        }

    }

    private fun activeButtonOnLegalState() {
        val defaultCountry = getString(R.string.default_country)
        button_sign_up.isEnabled =
                country_spinner.selectedItem.toString() != defaultCountry &&
                edit_text_name.text.isNotBlank() &&
                edit_text_age.text.isNotBlank()
    }
}
