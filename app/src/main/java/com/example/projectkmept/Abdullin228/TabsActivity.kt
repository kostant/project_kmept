package com.example.projectkmept.Abdullin228
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projectkmept.R

class TabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        // Изначально показываем Grid
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, GridFragment())
            .commit()

        val tab1 = findViewById<TextView>(R.id.tab1)
        val tab2 = findViewById<TextView>(R.id.tab2)
        val tab3 = findViewById<TextView>(R.id.tab3)
        val tab4 = findViewById<TextView>(R.id.tab4)

        // Функция подсветки вкладки
        fun highlightTab(selectedTab: TextView) {
            listOf(tab1, tab2, tab3, tab4).forEach {
                it.setTextColor(ContextCompat.getColor(this, R.color.gray))


            }
            selectedTab.setTextColor(ContextCompat.getColor(this, R.color.green))


        }

        // Обработчики
        tab1.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, GridFragment())
                .commit()
            highlightTab(tab1)
        }

        tab2.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, RedFragment())
                .commit()
            highlightTab(tab2)
        }

        tab3.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, BlueFragment())
                .commit()
            highlightTab(tab3)
        }

        tab4.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, GreenFragment())
                .commit()
            highlightTab(tab4)
        }
        // Можно вызвать один раз, чтобы подсветить изначально
        highlightTab(tab1)
    }
}