package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("photos")
    val photos:List<T>?

)
