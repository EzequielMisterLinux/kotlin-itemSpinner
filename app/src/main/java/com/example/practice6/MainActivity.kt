package com.example.practice6

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var simpleSpinner: Spinner
    private lateinit var customSpinner: Spinner
    private lateinit var countries: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simpleSpinner = findViewById(R.id.spNormal)
        customSpinner = findViewById(R.id.spCustom)

        countries = resources.getStringArray(R.array.countries)

        setupSimpleSpinner()
        setupCustomSpinner()
    }

    private fun setupSimpleSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        simpleSpinner.adapter = adapter

        simpleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position)
                Toast.makeText(this@MainActivity, "Usted seleccionó $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    private fun setupCustomSpinner() {
        val customAdapter = CustomSpinnerAdapter(countries)
        customSpinner.adapter = customAdapter
    }

    inner class CustomSpinnerAdapter(private val countries: Array<String>) : BaseAdapter() {
        private val countryFlags = intArrayOf(
            R.drawable.all_countries,
            R.drawable.australia,
            R.drawable.china,
            R.drawable.india,
            R.drawable.united_kingdom,
            R.drawable.united_states
        )

        override fun getCount(): Int = countries.size
        override fun getItem(position: Int): Any = countries[position]
        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: layoutInflater.inflate(R.layout.custom_spinner_item, parent, false)
            val flag = view.findViewById<ImageView>(R.id.flag)
            val countryName = view.findViewById<TextView>(R.id.countryName)

            flag.setImageResource(countryFlags[position])
            countryName.text = countries[position]

            return view
        }
    }
}