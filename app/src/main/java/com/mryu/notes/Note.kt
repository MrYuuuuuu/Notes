package com.mryu.notes

import android.graphics.Color
import java.io.File

data class Note(
    val uid:Int,
    val title:String,
    val category:Category,
    val file:File

)
data class Category(
    val uid: Int,
    val category:String,
    val color:Color
)