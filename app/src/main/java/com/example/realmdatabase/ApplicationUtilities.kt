package com.example.realmdatabase

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

fun initializeRealm(context: Context) {
    Realm.init(context)
    val realmConfig = RealmConfiguration.Builder()
        .name("Testing.realm")
        .schemaVersion(2)
        .migration(RealmDBMigration())
        .build()
    Realm.setDefaultConfiguration(realmConfig)
}