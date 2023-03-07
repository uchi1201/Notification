package com.android.example.notification.ui.notification.division

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.android.example.notification.MainApplication
import com.android.example.notification.room.AppDataBase
import com.android.example.notification.ui.category.CategoryManagementViewModel

class NotificationDivisionViewModel(app:Application) : AndroidViewModel(app) {

    //3/7
    private var categoryManagementViewModel: CategoryManagementViewModel?= null
    private var dataBase2: AppDataBase? = null

    /**
     * カテゴリーSpinnerItemのデータを設定
     * @return ArrayList<String>
     */
    fun getCategoryList(): ArrayList<String>{
        //Todo サーバー側からもらうか
        //一旦仮データ
        //DBを取得
        dataBase2 = MainApplication.instance().appDataBase

        categoryManagementViewModel = dataBase2?.let { CategoryManagementViewModel(it) }

        //DBから全部データを取得
        categoryManagementViewModel?.getAllCategoryData()

        if(categoryManagementViewModel?.categoryDbData?.size == 0){
            //DBのデータがない時データ追加
             getApplication<Application>().let { categoryManagementViewModel?.insertDataBaseData(it.applicationContext) }

            //DBから全部データを取得
            categoryManagementViewModel?.getAllCategoryData()
        }
        var cate = categoryManagementViewModel?.categoryDbData

        var categorySpList: ArrayList<String> = ArrayList()

        if (cate != null) {
            for(catename in cate)
            {
                categorySpList.add(catename.category)
            }
        }
//        categorySpList.add("食費")
//        categorySpList.add("水道")
           categorySpList.add("その他")
        return categorySpList
    }


}