package com.example.projectkmept.screens

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.projectkmept.R
import com.example.projectkmept.model.ApiRecipe

class ArsenchikActivity : AppCompatActivity() {

    private lateinit var navHome: View
    private lateinit var navUpload: View
    private lateinit var navScan: View
    private lateinit var navNotification: View
    private lateinit var navProfile: View

    private lateinit var ivHome: ImageView
    private lateinit var ivUpload: ImageView
    private lateinit var ivScan: ImageView
    private lateinit var ivNotification: ImageView
    private lateinit var ivProfile: ImageView

    private lateinit var tvHome: TextView
    private lateinit var tvUpload: TextView
    private lateinit var tvScan: TextView
    private lateinit var tvNotification: TextView
    private lateinit var tvProfile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        navHome = findViewById(R.id.navHome)
        navUpload = findViewById(R.id.navUpload)
        navScan = findViewById(R.id.navScan)
        navNotification = findViewById(R.id.navNotification)
        navProfile = findViewById(R.id.navProfile)

        ivHome = findViewById(R.id.ivHome)
        ivUpload = findViewById(R.id.ivUpload)
        ivScan = findViewById(R.id.ivScan)
        ivNotification = findViewById(R.id.ivNotification)
        ivProfile = findViewById(R.id.ivProfile)

        tvHome = findViewById(R.id.tvHome)
        tvUpload = findViewById(R.id.tvUpload)
        tvScan = findViewById(R.id.tvScan)
        tvNotification = findViewById(R.id.tvNotification)
        tvProfile = findViewById(R.id.tvProfile)

        navHome.setOnClickListener {
            openRootFragment(GridFragment())
            selectTab(Tab.HOME)
        }

        navUpload.setOnClickListener {
            openRootFragment(RecipeFragment())
            selectTab(Tab.UPLOAD)
        }

        navScan.setOnClickListener {
            openRootFragment(ColorFragment.newInstance(Color.parseColor("#EAF8EE")))
            selectTab(Tab.SCAN)
        }

        navNotification.setOnClickListener {
            openRootFragment(ColorFragment.newInstance(Color.parseColor("#DDEBFF")))
            selectTab(Tab.NOTIFICATION)
        }

        navProfile.setOnClickListener {
            openRootFragment(ColorFragment.newInstance(Color.parseColor("#F2F6FF")))
            selectTab(Tab.PROFILE)
        }

        if (savedInstanceState == null) {
            openRootFragment(GridFragment())
            selectTab(Tab.HOME)
        }
    }

    fun openRecipeDetail(recipe: ApiRecipe) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, RecipeFragment.newInstance(recipe))
            .addToBackStack("recipe_detail")
            .commit()
    }

    private fun openRootFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun selectTab(tab: Tab) {
        val green = ContextCompat.getColor(this, R.color.green_selected)
        val gray = ContextCompat.getColor(this, R.color.gray_unselected)

        resetAll(gray)

        when (tab) {
            Tab.HOME -> {
                ivHome.setColorFilter(green)
                tvHome.setTextColor(green)
            }
            Tab.UPLOAD -> {
                ivUpload.setColorFilter(green)
                tvUpload.setTextColor(green)
            }
            Tab.SCAN -> {
                ivScan.setColorFilter(green)
                tvScan.setTextColor(green)
            }
            Tab.NOTIFICATION -> {
                ivNotification.setColorFilter(green)
                tvNotification.setTextColor(green)
            }
            Tab.PROFILE -> {
                ivProfile.setColorFilter(green)
                tvProfile.setTextColor(green)
            }
        }
    }

    private fun resetAll(gray: Int) {
        ivHome.setColorFilter(gray)
        ivUpload.setColorFilter(gray)
        ivScan.setColorFilter(gray)
        ivNotification.setColorFilter(gray)
        ivProfile.setColorFilter(gray)

        tvHome.setTextColor(gray)
        tvUpload.setTextColor(gray)
        tvScan.setTextColor(gray)
        tvNotification.setTextColor(gray)
        tvProfile.setTextColor(gray)
    }

    enum class Tab {
        HOME, UPLOAD, SCAN, NOTIFICATION, PROFILE
    }
}
