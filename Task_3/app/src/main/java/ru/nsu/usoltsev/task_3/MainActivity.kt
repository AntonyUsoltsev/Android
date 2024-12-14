package ru.nsu.usoltsev.task_3

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.usoltsev.task_3.view.CurrencyAdapter
import ru.nsu.usoltsev.task_3.model.Currency
import ru.nsu.usoltsev.task_3.model.CurrencyResponseDto
import ru.nsu.usoltsev.task_3.service.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var currencySpinner: Spinner
    private lateinit var amountEditText: EditText
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var recyclerView: RecyclerView

    private var currencies: Map<String, Currency> = emptyMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currencySpinner = findViewById(R.id.currency_spinner)
        amountEditText = findViewById(R.id.amount_edit_text)
        convertButton = findViewById(R.id.convert_button)
        resultTextView = findViewById(R.id.result_text_view)
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)

        loadCurrencies()

        convertButton.setOnClickListener {
            convertCurrency()
        }
    }

    private fun loadCurrencies() {
        RetrofitClient.api.getCurrencies().enqueue(object : Callback<CurrencyResponseDto> {
            override fun onResponse(call: Call<CurrencyResponseDto>, response: Response<CurrencyResponseDto>) {
                println(response)
                if (response.isSuccessful && response.body() != null) {
                    currencies = response.body()!!.Valute
                    currencies.values.forEach { currency ->
                        currency.NormalizeValue = currency.Value / currency.Nominal
                    }
                    setupSpinner()
                    recyclerView.adapter = CurrencyAdapter(currencies.values.toList())
                } else {
                    Toast.makeText(this@MainActivity, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CurrencyResponseDto>, t: Throwable) {
                println("Ошибка сети: ${t.message}")
                Toast.makeText(this@MainActivity, "Ошибка сети: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupSpinner() {
        val currencyNames = currencies.values.map { it.Name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySpinner.adapter = adapter
    }

    private fun convertCurrency() {
        val amountText = amountEditText.text.toString()
        if (amountText.isEmpty()) {
            Toast.makeText(this, "Введите сумму", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.toDouble()
        val selectedCurrency = currencies.values.toList()[currencySpinner.selectedItemPosition]
        val result = amount / selectedCurrency.NormalizeValue
        resultTextView.text = String.format("%.4f %s", result, selectedCurrency.CharCode)
    }
}
