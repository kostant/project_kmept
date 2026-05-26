package com.example.projectkmept.Abdullin228

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R

class GridFragment : Fragment() {

    private lateinit var adapter: UsersAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var clearButton: FrameLayout
    private lateinit var emptySearchView: View
    private lateinit var searchResultCount: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_grid, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        searchEditText = view.findViewById(R.id.searchEditText)
        clearButton = view.findViewById(R.id.clearButton)
        emptySearchView = view.findViewById(R.id.emptySearchView)
        searchResultCount = view.findViewById(R.id.searchResultCount)
        val filterButton: FrameLayout = view.findViewById(R.id.filterButton)

        recyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter = UsersAdapter(createUsers().toMutableList())
        recyclerView.adapter = adapter

        setupSearch()

        filterButton.setOnClickListener {
            // Фильтр — здесь можно открыть диалог, пока просто анимация нажатия
            it.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100)
                .withEndAction { it.animate().scaleX(1f).scaleY(1f).setDuration(100).start() }
                .start()
        }

        return view
    }

    private fun setupSearch() {
        // Показывать/скрывать крестик при вводе текста
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s?.toString() ?: ""
                clearButton.visibility = if (query.isNotEmpty()) View.VISIBLE else View.GONE
                performSearch(query)
            }
        })

        // Поиск по нажатию Enter / кнопки поиска на клавиатуре
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                true
            } else false
        }

        // Крестик — очистить поле
        clearButton.setOnClickListener {
            searchEditText.text.clear()
            searchEditText.clearFocus()
            hideKeyboard()
        }
    }

    private fun performSearch(query: String) {
        adapter.filter(query)

        val filtered = adapter.getFilteredCount()
        val total = adapter.getTotalCount()

        if (query.isBlank()) {
            // Обычное состояние
            searchResultCount.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            emptySearchView.visibility = View.GONE
        } else if (filtered == 0) {
            // Ничего не найдено
            searchResultCount.visibility = View.GONE
            recyclerView.visibility = View.GONE
            emptySearchView.visibility = View.VISIBLE
            view?.findViewById<TextView>(R.id.emptySearchText)?.text = "Nothing found for \"$query\""
        } else {
            // Есть результаты
            searchResultCount.visibility = View.VISIBLE
            searchResultCount.text = "Found $filtered of $total recipes"
            recyclerView.visibility = View.VISIBLE
            emptySearchView.visibility = View.GONE
        }
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(searchEditText.windowToken, 0)
    }

    private fun createUsers(): List<UserCard> {
        val av1 = "https://sun9-76.userapi.com/s/v1/ig2/4Mn2ny1YAIm9QQxPEfj6vGWY7Ahy8yRNMm_nzuDdy5SvH6r2H1LDJrOFHoeHr_1ZBVrJyjNxMYvMgA8o6zMZUO8K.jpg?quality=95&as=32x24,48x36,72x54,108x81,160x120,240x180,360x270,480x360,540x405,640x480,720x540,1080x810,1280x960&from=bu&cs=1280x0"
        val av2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFKd6QUoH9FP7pDPO0i2cynKIxdjy8uNmQwg&s"
        val av3 = "https://litcult.ru/u/dd/prose/18062/md_foto.jpg"
        val av4 = "https://masterpiecer-images.s3.yandex.net/e541a1b9331211ee9492c65bced59789:upscaled"

        val photo1 = "https://i.pinimg.com/736x/d0/fa/6b/d0fa6b211aea6ec5755adb2fb28ff6c6.jpg"
        val photo2 = "https://i.pinimg.com/1200x/7d/e2/c3/7de2c3d4337af8fcb2dde497a4812b0d.jpg"
        val photo3 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPSO83Iu5knYmLhQ5874E2z6G93Qd75ZWDzg&s"
        val photo4 = "https://i.pinimg.com/originals/43/96/fa/4396fa964ad101d1936ddaeb805c2464.jpg"

        return listOf(
            UserCard("Calum Lewis", av1, photo1, "Pancake", "Food • >60 mins", false, 184,
                "Fluffy golden pancakes with blueberries, banana and honey. Perfect for a lazy weekend morning.",
                listOf("2 Cups Flour", "2 Eggs", "1 Cup Milk", "2 tbsp Butter", "1 tsp Baking Powder")),
            UserCard("Eilif Sonas", av2, photo2, "Salad", "Food • >60 mins", false, 97,
                "A vibrant fresh salad with crisp greens, roasted seeds, citrus and a light lemon vinaigrette.",
                listOf("Mixed Greens", "1 Orange", "2 tbsp Seeds", "Olive Oil", "Lemon Juice")),
            UserCard("Elena Shelby", av3, photo3, "Beer", "Drink • 30 mins", false, 273,
                "Rich creamy walnut milk blended with raw cacao and maca powder. Tastes like a luxurious dessert.",
                listOf("1 Cup Walnuts", "2 tbsp Cacao", "1 tsp Maca", "3 Cups Water", "2 tbsp Maple Syrup")),
            UserCard("SHARIK", av4, photo4, "Pizza", "Food • >60 mins", false, 145,
                "A hearty grain bowl with roasted vegetables, nuts, dried fruits and a tahini dressing.",
                listOf("1 Cup Quinoa", "Roasted Veggies", "1/4 Cup Nuts", "2 tbsp Tahini", "1 Lemon")),
            UserCard("Кузнечик", av1, photo4, "Pizza", "Food • >60 mins", false, 56,
                "Homemade pizza with fresh tomato sauce and mozzarella.",
                listOf("Pizza Dough", "Tomato Sauce", "Mozzarella", "Basil", "Olive Oil")),
            UserCard("конь мустанг", av3, photo1, "Pancake", "Food • >60 mins", false, 32,
                "Classic American pancakes, soft and fluffy.",
                listOf("1 Cup Flour", "1 Egg", "3/4 Cup Milk", "1 tbsp Sugar", "Butter"))
        )
    }
}
