package ru.nsu.usoltsev.task_3.service
import retrofit2.Call
import retrofit2.http.GET
import ru.nsu.usoltsev.task_3.model.CurrencyResponseDto

interface  CbrApiClient {
    @GET("daily_json.js")
    fun getCurrencies(): Call<CurrencyResponseDto>
}