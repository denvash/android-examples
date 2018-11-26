package a.hw1.ekay

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_text_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                activeButtonOnLegalState()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                activeButtonOnLegalState()
            }
        })

        edit_text_age.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                activeButtonOnLegalState()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                activeButtonOnLegalState()
            }
        })

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
        val currentAge = if (edit_text_age.text.isNotBlank()) edit_text_age.text.toString().toInt() else 0
        val legalAge = currentAge in 16..100
        button_sign_up.isEnabled =
                country_spinner.selectedItem.toString() != defaultCountry &&
                edit_text_name.text.isNotBlank() && edit_text_name.text.toString() != ""
                && legalAge
    }
}
