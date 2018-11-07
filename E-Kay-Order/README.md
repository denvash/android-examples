# E-Kay-Welcome

An example of an Android Registration app written in `Kotlin`.

## Goal

After signing-up, show a **Welcome Message** with the signed **User Name**.

## General Requirements

- Should run on all previous android versions up to API 16 (Android 4.1).
- Should support both landscape and portrait mode.
- Should run on both tablets and phones.

## App Requirements

- Pressing the *Sign Up* button will switch to a new activity.
- *Username* is the name of the user that just signed up. Note that the name should be a dynamic one (and not mentioned in XML).

## Code

### Button - Sign up

```Kotlin
        button_sign_up.setOnClickListener {
            val intent = Intent(this@MainActivity,OrderActivity::class.java)

            intent.putExtra("userName",edit_text_name.text.toString())
            startActivity(intent)
        }
```

Switching to new activity after clickin the *SignUp* button.
Passing the *username* value for next activity.

### EditText - Welcome Message

```xml
<string name="welcome_message">Welcome %1$s!</string>
```

```Kotlin
class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_activity)

        val welcomeMsg = resources.getString(R.string.welcome_message, intent.getStringExtra("userName"))

        display_user_name.text = welcomeMsg
    }
}

```

On new activity, updating the `TextView` component while creating the activity.
Using dynamic string from `string.xml` resource.