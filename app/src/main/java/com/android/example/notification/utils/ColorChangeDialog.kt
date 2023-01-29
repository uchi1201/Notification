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
   // 色変更ポップアップの色値
    var totalColors :Array<String> =
        arrayOf("#FFE60012", "#FFEB6100", "#FFF39800", "#FFFCC800","#FFFFF100",
            "#FFCFDB00", "#FF8FC31F", "#FF22AC38", "#FF009944", "#FF009B6B",
            "#FF009E96", "#FF00A0C1", "#FF00A0E9","#FF0086D1","#FF0068B7",
            "#FF00479D", "#FF1D2088", "#FF601986","#FF920783","#FFBE0081")

    /**
     * 使用している色は選択できないため、その以外色を取得
     * @param colors ArrayList<String>
     * @return ArrayList<String>?
     */
     fun getColors(colors: ArrayList<String>):ArrayList<String>?{
        var colorsList:ArrayList<String>? = ArrayList()
        //全体の色から使用している色以外の色を抽出
        for (item in totalColors) {
            //取得した色値が存在しない場合
            if (!colors.contains(item)) {
                //色値が追加
                colorsList?.add(item)
            }
        }
        return colorsList
    }

    /**
     * 色変更で出るポップアップ
     * @param context Context?
     * @param colors ArrayList<String>
     * @return Dialog?
     */
    fun createColorDialog(context: Context?,colors: ArrayList<String>): Dialog? {
        val inflater = LayoutInflater.from(context)
        val v: View = inflater.inflate(R.layout.dialog_change_color, null)
        //色変更で出るポップアップを設定
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
        //ポップアップの色GridViewListのアダプター設定
        setColorGridViewAdapter(context,v,changeColorDialog,colors)
        return changeColorDialog
    }

    private fun setColorGridViewAdapter(context: Context?, v: View,dialog:Dialog,colors: ArrayList<String>){
        val gridView = v.findViewById<View>(R.id.grid) as GridView
        //未選択の色を取得
        val colorsList = getColors(colors)
        if(colorsList!=null) {
            for (i in colorsList.indices) {
                val map: MutableMap<String, String> = HashMap()
                map["colors"] = colorsList[i]
                listItem.add(map)
            }
        }
        //アダプター設定
        val adapter = ColorChangeGridViewAdapter(context, gridView,listItem)
        gridView.adapter = adapter
        //選択色の押下処理
        gridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, view, position, _ ->
                //選択した後ダイアログを閉じる
                dialog.dismiss()
                if (view != null) {
                    //選択した色を遷移元画面に伝える
                    changeColor(context,view,position,adapter)
                }
            }
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