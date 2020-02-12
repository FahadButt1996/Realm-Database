package com.example.realmdatabase.database

import io.realm.RealmObject

open class User(
    var name: String? = "",
    var email: String? = ""
) : RealmObject()