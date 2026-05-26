package com.example.projectkmept.Abdullin228

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RedFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View(inflater.context)
        view.setBackgroundColor(Color.RED)
        return view
    }
}

class BlueFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View(inflater.context)
        view.setBackgroundColor(Color.BLUE)
        return view
    }
}

class GreenFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View(inflater.context)
        view.setBackgroundColor(Color.GREEN)
        return view
    }
}