package com.soda.tapieiattractions.model

import com.google.gson.annotations.SerializedName

/**
 * 分類資料
 */
data class CategoryModel(
    val `data`: Category,
    val total: Int
){
    data class Category(
        @SerializedName("Category")
        val category: List<Tag>,

        @SerializedName("Friendly")
        val friendly: List<Tag>,

        @SerializedName("Services")
        val services: List<Tag>,

        @SerializedName("Target")
        val target: List<Tag>
    )

    data class Tag(
        val id: Int,
        val name: String
    )
}
