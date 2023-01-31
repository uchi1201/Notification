package com.android.example.notification.ui.budget

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.notification.data.*
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.android.example.notification.room.AppDataBase

class BudgetViewModel(dataBase: AppDataBase) : ViewModel() {

    val budgetData = MutableLiveData<MutableList<BudgetData>>()
    val pieData = MutableLiveData<PieData>()
    val categoryDao = dataBase.categoryDao()

    fun getListData(month: String){
        var budgetDataBean =  getBudgetBean(month)
        for (i in budgetDataBean.indices){
            budgetData.value = budgetDataBean[i].data
        }
    }

    private fun getBudgetBean(month: String): MutableList<BudgetUsedBean> {
        return getBudgetData(month)
    }

    private fun getBudgetData(month: String):MutableList<BudgetUsedBean> {
        var results = mutableListOf<BudgetUsedBean>()
        var dataBean = BudgetUsedBean(data =  mutableListOf<BudgetData>())
        var budgetData = mutableListOf<BudgetData>()
        if (month == "1") {
            val value1 = BudgetData(category = "服飾費", budget = "4000", percentage = "22.5%")
            budgetData.add(value1)
            val value2 = BudgetData(category = "食費", budget = "10000", percentage = "56.2%")
            budgetData.add(value2)
            val value3 = BudgetData(category = "交際費", budget = "1000", percentage = "5.6%")
            budgetData.add(value3)
            val value4 = BudgetData(category = "電気", budget = "2800", percentage = "15.7%")
            budgetData.add(value4)
            dataBean = BudgetUsedBean(data = budgetData)
            results.add(dataBean)
        }
        else if(month=="2")
        {
            val value1 = BudgetData(category = "服飾費", budget = "3000", percentage = "20.5%")
            budgetData.add(value1)
            val value2 = BudgetData(category = "食費", budget = "2800", percentage = "19.2%")
            budgetData.add(value2)
            val value3 = BudgetData(category = "交際費", budget = "5000", percentage = "34.2%")
            budgetData.add(value3)
            val value4 = BudgetData(category = "電気", budget = "3800", percentage = "26.0%")
            budgetData.add(value4)
            dataBean = BudgetUsedBean(data = budgetData)
            results.add(dataBean)
        }
        else if(month=="3")
        {
            val value1 = BudgetData(category = "服飾費", budget = "1000", percentage = "8.2%")
            budgetData.add(value1)
            val value2 = BudgetData(category = "食費", budget = "1800", percentage = "14.8%")
            budgetData.add(value2)
            val value3 = BudgetData(category = "交際費", budget = "5600", percentage = "45.9%")
            budgetData.add(value3)
            val value4 = BudgetData(category = "水道", budget = "3800", percentage = "31.1%")
            budgetData.add(value4)
            dataBean = BudgetUsedBean(data = budgetData)
            results.add(dataBean)
        }
        else if(month=="4")
        {
            val value1 = BudgetData(category = "服飾費", budget = "4000", percentage = "25.6%")
            budgetData.add(value1)
            val value2 = BudgetData(category = "食費", budget = "7800", percentage = "50.0%")
            budgetData.add(value2)
            val value3 = BudgetData(category = "交際費", budget = "1000", percentage = "6.4%")
            budgetData.add(value3)
            val value4 = BudgetData(category = "水道", budget = "2800", percentage = "17.9%")
            budgetData.add(value4)
            dataBean = BudgetUsedBean(data = budgetData)
            results.add(dataBean)
        }
        else if(month=="5")
        {
            val value1 = BudgetData(category = "服飾費", budget = "1000", percentage = "8.2%")
            budgetData.add(value1)
            val value2 = BudgetData(category = "食費", budget = "1800", percentage = "14.8%")
            budgetData.add(value2)
            val value3 = BudgetData(category = "交際費", budget = "5600", percentage = "45.9%")
            budgetData.add(value3)
            val value4 = BudgetData(category = "ガス", budget = "3800", percentage = "31.1%")
            budgetData.add(value4)
            dataBean = BudgetUsedBean(data = budgetData)
            results.add(dataBean)
        }
        else if(month=="6")
        {
            val value1 = BudgetData(category = "服飾費", budget = "3000", percentage = "20.5%")
            budgetData.add(value1)
            val value2 = BudgetData(category = "食費", budget = "2800", percentage = "19.2%")
            budgetData.add(value2)
            val value3 = BudgetData(category = "交際費", budget = "5000", percentage = "34.2%")
            budgetData.add(value3)
            val value4 = BudgetData(category = "ガス", budget = "3800", percentage = "26.0%")
            budgetData.add(value4)
            dataBean = BudgetUsedBean(data = budgetData)
            results.add(dataBean)
        }
        else{
            val value1 = BudgetData(category = "服飾費", budget = "3000", percentage = "20.5%")
            budgetData.add(value1)
            val value2 = BudgetData(category = "食費", budget = "2800", percentage = "19.2%")
            budgetData.add(value2)
            val value3 = BudgetData(category = "交際費", budget = "5000", percentage = "34.2%")
            budgetData.add(value3)
            val value4 = BudgetData(category = "ガス", budget = "3800", percentage = "26.0%")
            budgetData.add(value4)
            dataBean = BudgetUsedBean(data = budgetData)
            results.add(dataBean)
        }
        return results
    }

    fun getPieData(month:String){
        pieData.value = createPieData(month)
    }
    //仮データ
    private fun getPieDataList(month:String):MutableList<PieDataValueBean>{
        val results = mutableListOf<PieDataValueBean>()
        if(month == "1"){
            val data  = PieGraphData ("服飾費",4000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",10000f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",1000f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("電気",2800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "2"){
            val data  = PieGraphData ("服飾費",3000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",2800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",5000f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("電気",3800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "3"){
            val data  = PieGraphData ("服飾費",1000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",1800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",5600f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("水道",3800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "4"){
            val data  = PieGraphData ("服飾費",4000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",7800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",1000f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("水道",2800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "5"){
            val data  = PieGraphData ("服飾費",1000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",1800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",5600f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("ガス",3800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "6"){
            val data  = PieGraphData ("服飾費",3000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",2800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",5000f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("ガス",3800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "7"){
            val data  = PieGraphData ("服飾費",1000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",1800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",5600f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("定期支払い",3800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "8"){
            val data  = PieGraphData ("服飾費",4000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",7800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",1000f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("定期支払い",2800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "9"){
            val data  = PieGraphData ("服飾費",3000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",2800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",5000f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("定期支払い",3800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "10"){
            val data  = PieGraphData ("服飾費",1000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",1800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",5600f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("定期支払い",3800f)
            results.add( PieDataValueBean(data3))
        }
        else if(month == "11"){
            val data  = PieGraphData ("服飾費",4000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",7800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",1000f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("定期支払い",2800f)
            results.add( PieDataValueBean(data3))
        }
        else{
            val data  = PieGraphData ("服飾費",1000f)
            results.add( PieDataValueBean(data))
            val data1  = PieGraphData ("食費",1800f)
            results.add( PieDataValueBean(data1))
            val data2  = PieGraphData ("交際費",5600f)
            results.add( PieDataValueBean(data2))
            val data3  = PieGraphData ("定期支払い",3800f)
            results.add( PieDataValueBean(data3))
        }

        return results
    }
    private fun createPieData(month:String): PieData {
        var categoryList = categoryDao.getAll()
        var colorList = mutableListOf<Int>()

        //表示用サンプルデータの作成
        val pieDataList = getPieDataList(month)
        //①Entryにデータ格納
        var entryList = mutableListOf<PieEntry>()
        for(i in pieDataList.indices){
            entryList.add(
                PieEntry(pieDataList[i].data.budget, pieDataList[i].data.name)
            )
            for(j in categoryList.indices){
                if(pieDataList[i].data.name == categoryList[j].category  ){
                    colorList.add(Color.parseColor(categoryList[j].color))
                }
            }
        }

        //②PieDataSetにデータ格納
        val pieDataSet = PieDataSet(entryList, "")
        //③DataSetのフォーマット指定
        pieDataSet.colors = colorList

        //④PieDataにPieDataSet格納して返却
        return PieData(pieDataSet)
    }
}
