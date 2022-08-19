package com.example.nasaapi.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.nasaapi.R

class EdittextCustomView(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    var imgSearch: ImageView
    private var imgDelete: ImageView
    var edtSearch: EditText

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_edittext, this, true)
        imgDelete = view.findViewById(R.id.imgClear)
        imgSearch = view.findViewById(R.id.imgSearch)
        edtSearch = view.findViewById(R.id.edtSearch)
        imgDelete.visibility = View.GONE
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                imgDelete.visibility = View.VISIBLE
                imgDelete.setOnClickListener {
                    edtSearch.text = null
                    imgDelete.visibility = View.GONE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }


}