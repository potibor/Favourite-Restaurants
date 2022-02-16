package com.hsnozan.favoriterestaurants.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import com.hsnozan.favoriterestaurants.R
import com.hsnozan.favoriterestaurants.data.model.SortingValueType
import com.hsnozan.favoriterestaurants.data.model.SortingValueType.*

/**
 * Created by ozan.al on 16.02.2022.
 */

class CustomDialog(val listener: OnDialogClickListener) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.custom_dialog, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_corner)
        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        val averageProductPrice = view.findViewById<AppCompatTextView>(R.id.averageProductPrice)
        val bestMatch = view.findViewById<AppCompatTextView>(R.id.bestMatch)
        val deliveryCosts = view.findViewById<AppCompatTextView>(R.id.deliveryCosts)
        val distance = view.findViewById<AppCompatTextView>(R.id.distance)

        averageProductPrice.setOnClickListener {
            filterSelectedBy(AVERAGE_PRODUCT_PRICE)
        }
        bestMatch.setOnClickListener {
            filterSelectedBy(BEST_MATCH)
        }
        deliveryCosts.setOnClickListener {
            filterSelectedBy(DELIVERY_COSTS)
        }
        distance.setOnClickListener {
            filterSelectedBy(DISTANCE)
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun filterSelectedBy(type: SortingValueType) {
        listener.onSelect(type)
        dialog?.dismiss()
    }
}