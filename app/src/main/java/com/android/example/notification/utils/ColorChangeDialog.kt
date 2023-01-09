package com.android.example.notification.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.*
import android.widget.*
import com.android.example.notification.R
import com.android.example.notification.ui.category.ColorChangeGridViewAdapter


class ColorChangeDialog {

    private var mChangeColorClickListener: OnChangeColorClickListener? = null

    private val listItem: ArrayList<Map<String, String>> = ArrayList()

    var totalColors :Array<String> =
        arrayOf("#FFE60012", "#FFEB6100", "#FFF39800", "#FFFCC800","#FFFFF100",
            "#FFCFDB00", "#FF8FC31F", "#FF22AC38", "#FF009944", "#FF009B6B",
            "#FF009E96", "#FF00A0C1", "#FF00A0E9","#FF0086D1","#FF0068B7",
            "#FF00479D", "#FF1D2088", "#FF601986","#FF920783","#FFBE0081")

    private fun getRandomColors(colors: ArrayList<String>):ArrayList<String>{
        var colorsList:ArrayList<String> = ArrayList()
        var colorUtils = ColorUtils()
        for (i in 1..20) {
            //20個のランダム色値を取得
            var color = colorUtils.getRandColor()
            //取得した色値が存在する場合は再取得
            while (colors.contains(color)) {
                color = colorUtils.getRandColor()
            }
            //色値が追加
            colorsList.add(color)
        }
        return colorsList
    }

     fun getColors(colors: ArrayList<String>):ArrayList<String>?{
        var colorsList:ArrayList<String>? = ArrayList()
        for (item in totalColors) {
            //取得した色値が存在しない場合
            if (!colors.contains(item)) {
                //色値が追加
                colorsList?.add(item)
            }
        }
        return colorsList
    }

    fun createColorDialog(context: Context?,colors: ArrayList<String>): Dialog? {
        val inflater = LayoutInflater.from(context)
        val v: View = inflater.inflate(R.layout.dialog_change_color, null)
        val gridView = v.findViewById<View>(R.id.grid) as GridView
        //色値を取得
        val colorsList = getColors(colors)
        if(colorsList!=null) {
            for (i in colorsList.indices) {
                val map: MutableMap<String, String> = HashMap()
                map["colors"] = colorsList[i]
                listItem.add(map)
            }
        }
        val adapter = ColorChangeGridViewAdapter(context, gridView,listItem)
        gridView.adapter = adapter

        val changeColorDialog = Dialog(context!!)
        changeColorDialog.setCancelable(true)
        changeColorDialog.setCanceledOnTouchOutside(true)
        changeColorDialog.setContentView(
            v, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        )
        val window = changeColorDialog.window
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setGravity(Gravity.CENTER)
        window.attributes = lp
        window.setWindowAnimations(R.style.PopWindowAnimStyle)
        changeColorDialog.show()
        gridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, view, position, _ ->
                changeColorDialog.dismiss()
                if (view != null) {
                    changeColor(context,view,position,adapter)
                }
            }

        return changeColorDialog
    }

    private fun changeColor(context: Context?,view:View, position: Int,adapter: ColorChangeGridViewAdapter) {
        mChangeColorClickListener?.onChangeColorClick(view,position,adapter)


    }


    open fun setChangeColorClickListener(listener: OnChangeColorClickListener) {
        mChangeColorClickListener = listener
    }

    interface OnChangeColorClickListener {
        fun onChangeColorClick(view:View,Position: Int, adapter: ColorChangeGridViewAdapter)
    }


}