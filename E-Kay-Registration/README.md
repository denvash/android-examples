# E-Kay-Registration

An example of an Android Registartion app written in `Kotlin`.

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

### EditText - Full Name

```xml
android:digits="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz "
```

```Kotlin
        edit_text_name.setOnEditorActionListener { _, actionId, _ ->
            var handle = true
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                activeButtonOnLegalState()
                handle = false
            }
            handle
        }
```

**Full Name** characters were restritced using the `android:digits` tag.
By using `setOnEditorActionListener` on action `EditorInfo.IME_ACTION_NEXT` we check if all the fields are legal and if so we enable the **SingUp** button.

### EditText - Age

```Kotlin
        edit_text_age.setOnEditorActionListener { v, actionId, _ ->
            var handle = true
            val ageRestriction = 16..100
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (v.text.toString().toInt() !in ageRestriction) {
                    Toast.makeText(applicationContext, "Age restricted to 16-100", Toast.LENGTH_SHORT).show()
                    edit_text_age.text.clear()
                    button_signup.isEnabled = false
                } else {
                    activeButtonOnLegalState()
                }
                handle = false
            }
            handle
        }
```

Using `setOnEditorActionListener` if the filled age wasn't legal, the age field was cleared with a toast, else we check if all fields are legal using `activeButtonOnLegalState()`

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
    fun resetWithToast(view: View) {
        Toast.makeText(applicationContext, "User created!", Toast.LENGTH_SHORT).show()
        edit_text_name.text.clear()
        edit_text_age.text.clear()
        country_spinner.setSelection(0)
        button_signup.isEnabled = false
    }
```

Added onClick tag for toast calling and reseting the layout.

#### activeButtonOnLegalState - function

```kotlin
    private fun activeButtonOnLegalState() {
        val defaultCountry = getString(R.string.default_country)
        button_signup.isEnabled =
                country_spinner.selectedItem.toString() != defaultCountry &&
                edit_text_name.text.isNotBlank() &&
                edit_text_age.text.isNotBlank()
    }
```

On function call checking if all fields on the current layout are legal, if so enabling the **SignUp** button.

## Potrait Mode

Added `<ScrollView/>` which wrappes the `<LinearLayout/>`.