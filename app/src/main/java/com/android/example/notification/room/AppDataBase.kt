package com.android.example.notification.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.example.notification.room.dao.CategoryDao
import com.android.example.notification.room.dao.BudgetDao
import com.android.example.notification.room.data.CategoryData
import com.android.example.notification.room.data.BudgetTableData
/**
 * カテゴリーDBの作成
 */
@Database(entities = [CategoryData::class, BudgetTableData::class], version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun budgetDao(): BudgetDao
}