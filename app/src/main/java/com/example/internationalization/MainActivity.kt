package com.example.internationalization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.internationalization.databinding.ActivityMainBinding
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources

import android.util.DisplayMetrics
import android.view.View
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"

        const val ZH_SIMPLE = "zh_simple"
        const val EN = "en"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"MainActivity onCreate")
        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.tv.run {
            append("\n\n")
            append(resources.getString(R.string.app_name))
            append("\n\n")
            append(resources.getString(R.string.tv_text))
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"MainActivity onRestart")
    }

    override fun recreate() {
        super.recreate()
        Log.d(TAG,"MainActivity recreate")
    }
    fun chinese(view: View) {switchLanguage(ZH_SIMPLE)}//"zh_simple"
    fun english(view: View) {switchLanguage(EN)}//"en"

    /**
     * 切换语言
     *
     * @param language
     */
    private fun switchLanguage(language: String) {

        //设置应用语言类型
        val resources: Resources = resources
        val config: Configuration = resources.getConfiguration()
        val dm: DisplayMetrics = resources.getDisplayMetrics()
        if (language == ZH_SIMPLE) {//"zh_simple"
            config.locale = Locale.SIMPLIFIED_CHINESE
        } else if (language == EN) {//"en"
            config.locale = Locale.ENGLISH
        } else {
            config.locale = Locale.getDefault()
        }
        resources.updateConfiguration(config, dm)

        //更新语言后，destroy当前页面，重新绘制
        finish()
        val it = Intent(this@MainActivity, MainActivity::class.java)

        //清空任务栈确保当前打开activit为前台任务栈栈顶
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }


}