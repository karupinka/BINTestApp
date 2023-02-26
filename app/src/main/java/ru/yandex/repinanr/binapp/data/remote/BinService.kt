package ru.yandex.repinanr.binapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.yandex.repinanr.binapp.data.model.response.BinInfoResponse

interface BinService {

    @GET("{id}")
    suspend fun getBinInfo(@Path("id") id: String): BinInfoResponse
}