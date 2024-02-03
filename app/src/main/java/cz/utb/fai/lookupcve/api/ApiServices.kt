package cz.utb.fai.lookupcve.api

import cz.utb.fai.lookupcve.model.CveJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("2.0")
    suspend fun getCveJson(@Query("cveId") cveId : String) : Response<CveJson>


}