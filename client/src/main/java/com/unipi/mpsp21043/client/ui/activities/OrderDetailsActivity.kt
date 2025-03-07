package com.unipi.mpsp21043.client.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.unipi.mpsp21043.client.R
import com.unipi.mpsp21043.client.adapters.OrderCartProductListAdapter
import com.unipi.mpsp21043.client.databinding.ActivityOrderDetailsBinding
import com.unipi.mpsp21043.client.models.Order
import com.unipi.mpsp21043.client.utils.Constants

class OrderDetailsActivity : BaseActivity() {

    /**
     * Class variables
     *
     * @see binding
     * */
    private lateinit var binding: ActivityOrderDetailsBinding

    // A global variable for Order details.
    private lateinit var mOrderDetails: Order


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // This calls the parent constructor
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // This is used to align the xml view to this class

        init()
        setupUI()
    }

    private fun init() {
        showShimmerUI()

        if (intent.hasExtra(Constants.EXTRA_ORDER_DETAILS)) {
            mOrderDetails = if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra(Constants.EXTRA_ORDER_DETAILS, Order::class.java)!!
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(Constants.EXTRA_ORDER_DETAILS)!!
            }

            setOrderItemsRecyclerView()
        }
    }

    private fun showShimmerUI() {
        binding.apply {
            shimmerLayout.root.visibility = View.VISIBLE
            shimmerLayout.shimmerViewHeadContainer.startShimmer()
            shimmerLayout.shimmerViewBottomContainerOrderItems.startShimmer()
        }
    }

    private fun hideShimmerUI() {
        binding.apply {
            shimmerLayout.root.visibility = View.GONE
            shimmerLayout.shimmerViewHeadContainer.stopShimmer()
            shimmerLayout.shimmerViewBottomContainerOrderItems.stopShimmer()
        }
    }

    private fun setOrderItemsRecyclerView() {
        // Sets RecyclerView's properties
        binding.recyclerViewOrderItems.run {
            adapter = OrderCartProductListAdapter(
                        this@OrderDetailsActivity,
                        mOrderDetails.cartItems
            )
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@OrderDetailsActivity, LinearLayoutManager.VERTICAL, false)
        }

        hideShimmerUI()
    }

    private fun setupUI() {
        setupActionBar()

        binding.apply {

            txtViewAddressValue.text = String.format(
                getString(R.string.text_format_order_address),
                mOrderDetails.address.fullName,
                String.format(
                    getString(R.string.text_format_phone),
                    mOrderDetails.address.phoneCode,
                    mOrderDetails.address.phoneNumber
                ),
                mOrderDetails.address.address,
                mOrderDetails.address.zipCode,
                mOrderDetails.address.additionalNote,
            )

            txtViewTotalAmtValue.text = String.format(
                getString(R.string.text_format_price),
                Constants.DEFAULT_CURRENCY,
                mOrderDetails.totalAmount
            )

            txtViewPaymentMethodValue.text = mOrderDetails.paymentMethod

            when (mOrderDetails.orderStatus) {
                0 -> {
                    textViewOrderStatusValue.text = getString(R.string.text_pending)
                    textViewOrderStatusValue.setTextColor(getColor(R.color.colorRed))
                }
                1 -> {
                    textViewOrderStatusValue.text = getString(R.string.text_processing)
                    textViewOrderStatusValue.setTextColor(getColor(R.color.colorYellowOrange))
                }
                2 -> {
                    textViewOrderStatusValue.text = getString(R.string.text_completed)
                    textViewOrderStatusValue.setTextColor(getColor(R.color.colorPrimary))
                }
            }
        }
    }

    private fun setupActionBar() {
        binding.toolbar.apply {
            setSupportActionBar(toolbar)
            textViewActionLabel.text = Constants.DATE_TIME_FORMAT.format(mOrderDetails.orderDate)
        }

        val actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.svg_chevron_left)
            it.setHomeActionContentDescription(getString(R.string.text_go_back))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        finish()
        return super.getOnBackInvokedDispatcher()
    }

}
