package com.example.projectkmept.screens

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectkmept.R

class ColorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_color, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем цвет из аргументов
        val color = requireArguments().getInt(ARG_COLOR, Color.WHITE) // Дефолт белый, если что-то пойдет не так
        view.setBackgroundColor(color)
    }

    companion object {
        private const val ARG_COLOR = "ARG_COLOR"

        fun newInstance(color: Int): ColorFragment {
            val fragment = ColorFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_COLOR, color)
            }
            return fragment
        }

        // Дополнительный удобный метод для передачи цвета строкой
        fun newInstance(colorString: String): ColorFragment {
            return newInstance(Color.parseColor(colorString))
        }
    }
}