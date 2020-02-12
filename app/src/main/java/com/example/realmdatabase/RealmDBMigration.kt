package com.example.realmdatabase

import io.realm.DynamicRealm
import io.realm.RealmMigration

class RealmDBMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        val schema = realm?.schema
        var _oldVersion = oldVersion

        if (_oldVersion == 0L) {
            schema?.get("User")?.addField("age", String::class.java)
                ?.addField("cnic", String::class.java)
            _oldVersion++
        }

        if (_oldVersion == 1L) {
            schema?.get("User")
                ?.addField("nationality", String::class.java)
            _oldVersion++
        }
    }
}