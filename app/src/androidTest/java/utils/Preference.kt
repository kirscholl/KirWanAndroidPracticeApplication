package utils

import android.content.Context
import android.content.SharedPreferences
import com.example.kirwanandroidpracticeapplication.App.App

/**
 * kotlin委托属性 以及 SharedPreference实例
 */
class Preference<T>(val name: String, private val default: T){

    companion object{
        private val file_name = "wan_android_file"


        private val prefs: SharedPreferences by lazy {
            App.context.getSharedPreferences(file_name, Context.MODE_PRIVATE)
        }

        /**
         * 删除全部数据
         */
        fun clearPreference(){
            prefs.edit().clear().apply()
        }

        /**
         * 根据key删除存储数据
         */
        fun clearPreference(key: String){
            prefs.edit().remove(key).apply()
        }

        /**
         * 查询某个key值是否已经存在
         */
        fun contains(key: String): Boolean{
            return prefs.contains(key)
        }

        /**
         * 返回所有键值对
         */
        fun getAll(): Map<String, *>{
            return prefs.all
        }
    }
}