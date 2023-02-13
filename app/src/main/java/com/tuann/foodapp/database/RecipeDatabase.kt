package com.tuann.foodapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tuann.foodapp.dao.RecipeDao
import com.tuann.foodapp.entities.*
import com.tuann.foodapp.entities.conventer.CategoryListConverter
import com.tuann.foodapp.entities.conventer.MealListConverter

@Database(
    entities = [Recipes::class, CategoryItems::class, Category::class, Meal::class, MealsItems::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(CategoryListConverter::class, MealListConverter::class)
abstract class RecipeDatabase : RoomDatabase() {

    companion object {

        var recipesDatabase: RecipeDatabase? = null
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE Contact ADD COLUMN seller_id TEXT NOT NULL DEFAULT ''")
            }
        }

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase {
            if (recipesDatabase == null) {
                recipesDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                )
                    .build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}