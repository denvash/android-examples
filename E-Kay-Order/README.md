# E-Kay-Order

![E-Kay-Order-Potrait](https://media.giphy.com/media/7T0dSX6bsPFApxnQV0/giphy.gif) ![E-Kay-Order-Landscape](https://media.giphy.com/media/Nmgxj4tQCJ8kFkhNd3/giphy.gif)

An example of an Android Order app written in `Kotlin`.
*Designed on:*

- `Pixel 2 XL API 28`
- `10.1 WXGA (Tablet) API 28`

## Goal

After signing-up, make an **Order** for available devices.

## General Requirements

- Should run on all previous android versions up to API 16 (Android 4.1).
- Should support both landscape and portrait mode.
- Should run on both tablets and phones.

## App Requirements

- Pressing the *Sign Up* button will switch to a new activity.
- *Username* is the name of the user that just signed up. Note that the name should be a dynamic one (and not mentioned in XML).
- The user can choose **only one** device from the list view. Only one item can be checked.
- When clicking on **Order** button, **Dialog Alert** pops out.
- If the user clicks on **Order** button, and didn’t check any option, Do nothing.
- When clicking on back button from `OrderActivity` Activity, close the application.

## Classes

- `MainActivity.kt`: Signup page.
- `OrderActivity.kt`: Order page.
- `OrderAdapter.kt`: Adapter for `RecycleView` on `OrderActivity`.
- `ExitHelper.kt`: Exit application activity.
- `Extensions.kt`: Extension functions for various classes.

## Code

### OrderActivity.kt

#### Setting Adapter

```Kotlin
// Setting RecycleView with LinearLayout
makeOrder_recycleView.layoutManager = LinearLayoutManager(applicationContext)
makeOrder_recycleView.addItemDecoration(
    DividerItemDecoration(this@OrderActivity, LinearLayoutManager.HORIZONTAL)
)
// Setting Adapter
val lastOrder = LastOrder()
makeOrder_recycleView.adapter = OrderAdapter(this@OrderActivity, androidDevices, lastOrder)
```

Setting the `RecycleView` to show the order items in `LinearLayot`.
Initializing adapter with `androidDevices` array and `lastOrder` for filling the message of **Order** button later on.

#### Button Alert-Dialog

```Kotlin
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
```

Using `AlertDialog.Builder` for alerting after choosing the order.
`lastOrder.str` is updated from `OrderAdapter`.

#### Exit on back

```Kotlin
    // Close app at back press
    override fun onBackPressed() {
        finishAndRemoveTaskCompat()
    }
```

Using kotlin extensions as defined at `Extensions.kt`

```Kotlin
// @Extensions.kt
fun Activity.finishAndRemoveTaskCompat() {
    if (android.os.Build.VERSION.SDK_INT >= 21) {
        finishAndRemoveTask()
    } else {
        val intent = Intent(this, ExitHelper::class.java)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NO_ANIMATION or
                    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        startActivity(intent)
    }
    finishAffinity()
}
```

Finishing the activity or intening it to `ExitHelper`, defined with `Theme.NoDisplay` for fast rendering.

```xml
<activity
    android:name=".ExitHelper"
    android:theme="@android:style/Theme.NoDisplay"
    />
```

```Kotlin
// @ExitHelper.kt
class ExitHelper : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()
    }
}
```

### OrderAdapter.kt

Extends `RecyclerView.Adapter<OrderAdapter.OrderHolder>()` and implements it methods.

```Kotlin
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
    ...
    override fun onBindViewHolder(holder: OrderHolder, position: Int) { ... }
}
```

Inflating `order_item` which is a simple single `CheckedTextView`.
Using kotlin extensions for adding `infalte` functionallity:

```kotlin
    // @Extensions.kt
    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
```

#### Single Selection

```Kotlin
// @OrderAdapter.kt
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

        // Update order message at OrderActivity
        lastOrderRef.str = currentOrder.text.toString()

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
```

Ensures selection of single item using last `CheckedTextView` reference saved at `lastOrder` var.
Changing the **Order** button state (clickable) located at `OrderActivity` by referencing it from given `context`:
`(context as OrderActivity).order_button!!`
