# E-Kay-Registration

An example of an Android Registration app written in `Kotlin`.

## Goal

The main goal of the app was to make a **Registration Form** where a user will fill `EditText` forms: **Full Name**, **Age**, and choose his **Country** using a `Spinner`.
If the user's information is legal, the **SignUp** `Button` will appear as `clickable`.

Pressing the **SingUp** button will show a toast.

## General Requirements

- Should run on all previous android versions up to API 16 (Android 4.1).
- Should support both landscape and portrait mode.
- Should run on both tablets and phones.

## App Requirements

- The **Sign Up** button will only be enabled (enabled = the user can press it) when
- Full name contains only valid characters: "A-Z" , "a-z" , " "
- Age is between 16-100
- Pressing on “Sign Up” will show a toast saying “User created!”

## Code

### EditText - Full Name, Age

```xml
android:digits="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz "
```

```Kotlin
        edit_text_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                activeButtonOnLegalState()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
```

**Full Name** characters were restritced using the `android:digits` tag.
By using `activeButtonOnLegalState` we check if all the fields are legal and if so we enable the **SingUp** button.

Using `afterTextChanged` if the filled age wasn't legal, the age field was cleared with a toast, else we check if all fields are legal using `activeButtonOnLegalState()`

### Spinner - Countries

```kotlin
        country_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                activeButtonOnLegalState()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                button_signup.isEnabled = false
            }
        }
```

By using `onItemSelectedListener` and overriding the template `OnItemSelectedListener`, we active the button on legal state.

### Button - Sign Up

```xml
android:onClick="resetWithToast"
```

```kotlin
    fun resetWithToast() {
        Toast.makeText(applicationContext, "User created!", Toast.LENGTH_SHORT).show()
        edit_text_name.text.clear()
        edit_text_age.text.clear()
        country_spinner.setSelection(0)
        button_signup.isEnabled = false
    }
```

Added onClick tag for toast calling and resetting the layout.

#### activeButtonOnLegalState - function

```kotlin
    private fun activeButtonOnLegalState() {
        val defaultCountry = getString(R.string.default_country)
        val currentAge = if (edit_text_age.text.isNotBlank()) edit_text_age.text.toString().toInt() else 0
        val legalAge = currentAge in 16..100
        button_sign_up.isEnabled =
                country_spinner.selectedItem.toString() != defaultCountry &&
                edit_text_name.text.isNotBlank() && edit_text_name.text.toString() != ""
                edit_text_age.text.isNotBlank() && legalAge
    }
```

On function call checking if all fields on the current layout are legal, if so enabling the **SignUp** button.