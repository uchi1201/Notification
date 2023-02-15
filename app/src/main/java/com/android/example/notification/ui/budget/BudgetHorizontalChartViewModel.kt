package com.android.example.notification.ui.budget

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.notification.MainApplication
import com.android.example.notification.data.BudgetGraphData
import com.android.example.notification.data.BudgetValueBean
import com.android.example.notification.room.AppDataBase
import com.android.example.notification.room.data.BudgetTableData
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate

class BudgetHorizontalChartViewModel(dataBase: AppDataBase) : ViewModel() {

    //予算総額設定
//    private val _totalBarData= MutableLiveData<BarData>().apply {
//        value = createTotalBarData()
//    }
//    val totalBarData: LiveData<BarData> = _totalBarData
    //予算設定
    val totalbarData= MutableLiveData<BarData>()
    val barData= MutableLiveData<BarData>()
    //XのLabel
//    private val _xLabel = MutableLiveData<MutableList<String>>().apply {
//        value = barChartXLabel()
//    }
//    val xLabel:LiveData<MutableList<String>> = _xLabel

    private var budgetData = BudgetTableData(category = "", budget = 0.0f, budgetTotal = 0)
    val budgetDao = dataBase.budgetDao()
    val categoryDao = dataBase.categoryDao()

   //仮データ
    fun createBudgetDataList(month:String):MutableList<BudgetValueBean>{
       val results = mutableListOf<BudgetValueBean>()
       if(month == "1"){
           val data  =BudgetGraphData ("服飾費",4000f,6000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",10000f,10000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際費",1000f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ( "電気",2800f,9200f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "2"){
           val data  =BudgetGraphData ("服飾費",3000f,6000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",2800f,6000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際費",5000f,8000f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("電気",3800f,9000f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "3"){
           val data  =BudgetGraphData ("服飾費",1000f,7000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",1800f,2000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際費",5600f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("水道",3800f,9200f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "4"){
           val data  =BudgetGraphData ("服飾費",4000f,6000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",7800f,10000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際費",1000f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("水道",2800f,9200f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "5"){
           val data  =BudgetGraphData ("服飾費",1000f,7000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",1800f,2000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際費",5600f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("ガス",3800f,9200f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "6"){
           val data  =BudgetGraphData ("服飾費",3000f,6000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",2800f,6000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際費",5000f,8000f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("ガス",3800f,9000f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "7"){
           val data  =BudgetGraphData ("服飾費",1000f,7000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",1800f,2000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際",5600f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("定期支払い",3800f,9200f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "8"){
           val data  =BudgetGraphData ("服飾費",4000f,6000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",7800f,10000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際",1000f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("定期支払い",2800f,9200f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "9"){
           val data  =BudgetGraphData ("服飾費",3000f,6000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",2800f,6000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際",5000f,8000f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("定期支払い",3800f,9000f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "10"){
           val data  =BudgetGraphData ("服飾費",1000f,7000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",1800f,2000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際",5600f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("定期支払い",3800f,9200f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "11"){
           val data  =BudgetGraphData ("服飾費",4000f,6000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",7800f,10000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際",1000f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("定期支払い",2800f,9200f)
           results.add( BudgetValueBean(data3))
       }
       else if(month == "12"){
           val data  =BudgetGraphData ("服飾費",1000f,7000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",1800f,2000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際",5600f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("定期支払い",3800f,9200f)
           results.add( BudgetValueBean(data3))
       }
       else{
           val data  =BudgetGraphData ("服飾費",4000f,6000f)
           results.add( BudgetValueBean(data))
           val data1  =BudgetGraphData ("食費",7800f,10000f)
           results.add( BudgetValueBean(data1))
           val data2  =BudgetGraphData ("交際",1000f,8800f)
           results.add( BudgetValueBean(data2))
           val data3  =BudgetGraphData ("定期支払い",2800f,9200f)
           results.add( BudgetValueBean(data3))
       }

        return results
    }

    private fun insertData(month: String) {
        var budgetDataList = createBudgetDataList(month)
        for(item in budgetDataList){
            budgetData.budget = item.data.budget
            budgetData.category = item.data.category
            budgetData.budgetTotal =  item.data.budgetTotal.toInt()
            budgetDao.insert(budgetData)
        }
    }

    fun getTotalBarData(month:String){
        totalbarData.value = createTotalBarData(month)
    }

    private fun createTotalBarData(month: String): BarData {
        //表示用サンプルデータの予算総額作成
        var budgetList = budgetDao.getAll()
        var total = 0f
        var actual = 0f

        if(budgetList.isEmpty()){
            insertData(month)
            budgetList = budgetDao.getAll()
        }

        for(i in budgetList.indices){
            total += budgetList[i].budgetTotal
            actual += budgetList[i].budget
        }

        //①Entryにデータ格納
        var entryList = mutableListOf<BarEntry>()
        entryList.add(
            BarEntry(1f, actual)
        )
        //BarDataSetにデータ格納
        val barDataSet = BarDataSet(entryList, "totalBudget")
        barDataSet.setDrawIcons(false)
        //BarDataSetのカラー設定
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        barDataSet.valueFormatter =object : ValueFormatter() {
            override fun getFormattedValue(v: Float): String? {
                return "${v.toInt()}/${total.toInt()}"
            }
        }
        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(barDataSet)
        //BarDataにBarDataSet格納して返却
        return BarData(dataSets)
    }

    fun getBarData(month:String){
        barData.value = createBarData(month)
    }

   private fun createBarData(month:String): BarData {
       var budgetList = budgetDao.getAll()
       var categoryList = categoryDao.getAll()

       if(budgetList.isEmpty()){
           insertData(month)
           budgetList = budgetDao.getAll()
       }

       var entryList = mutableListOf<BarEntry>()
       var colorList = mutableListOf<Int>()

       for(i in budgetList.indices){
           entryList.add(
               BarEntry(i.toFloat(), budgetList[budgetList.size-1-i].budget/budgetList[budgetList.size-1-i].budgetTotal*100)
           )
           for(j in categoryList.indices){
               if(budgetList[i].category == categoryList[j].category  ){
                   colorList.add(Color.parseColor(categoryList[j].color))
               }
           }
       }

        val barDataSet = BarDataSet(entryList, "budget")
        barDataSet.setDrawIcons(false)
        barDataSet.colors = colorList.reversed()
        barDataSet.valueFormatter =object : ValueFormatter() {
            var index =0
            override fun getFormattedValue(v: Float): String? {
                if(index==budgetList.count()){
                    index=0
                }
                val budget = budgetList[budgetList.size-1-index].budget
                val budgetTotal = budgetList[budgetList.size-1-index].budgetTotal
                index++
                return "${budget.toInt()}/${budgetTotal}"
            }
        }
        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(barDataSet)
        //BarDataにBarDataSet格納して返却
        return BarData(dataSets)
    }

}
