package com.example.realmdatabase

/**
 * Created by fahad.waqar on 3:13 PM - 3/18/2019
 **/
interface GenericAdapterCallback {
    fun <T> getClickedObject(clickedObj: T, position: T, callingID: String = "")
}