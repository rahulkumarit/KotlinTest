package com.rnd.kotlintest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Devrepublic-14 on 7/14/2017.
 */

class Category {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    class Datum {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("image")
        @Expose
        var image: String? = null
        @SerializedName("sequence")
        @Expose
        var sequence: Int? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("valid_to")
        @Expose
        var validTo: Any? = null
        @SerializedName("valid_from")
        @Expose
        var validFrom: Any? = null
        @SerializedName("is_active")
        @Expose
        var isActive: Int? = null
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null
        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
    }

}

