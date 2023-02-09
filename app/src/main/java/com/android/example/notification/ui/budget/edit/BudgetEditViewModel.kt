package com.android.example.notification.ui.budget.edit

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.notification.room.AppDataBase

class BudgetEditViewModel(dataBase: AppDataBase) : ViewModel() {

    val budgetDao = dataBase.budgetDao()
    val categoryDao = dataBase.categoryDao()

    fun getCategoryList(): ArrayList<String>{
        var budgetList = budgetDao.getAll()
        var categoryList = categoryDao.getAll()
        var budgetListCategory: ArrayList<String> = ArrayList()

        //予算登録されているカテゴリを取得
        for(i in budgetList.indices){
            budgetListCategory.add(budgetList[i].category)
        }

        //登録できるカテゴリを取得
        var categorySpList: ArrayList<String> = ArrayList()
        var item: String
        for(i in categoryList.indices){
            item = categoryList[i].category
            if(!budgetListCategory.contains(item)){
                categorySpList.add(categoryList[i].category)
            }
        }

        if(categorySpList.isNotEmpty()){
            categorySpList.add("カテゴリを選択")
        }
        return categorySpList
    }
}