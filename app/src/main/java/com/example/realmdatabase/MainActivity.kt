package com.example.realmdatabase

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.realmdatabase.database.User
import com.facebook.stetho.Stetho
import io.realm.Realm
import io.realm.Realm.getDefaultInstance
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, GenericAdapterCallback {

    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)
        initializeRealm(this@MainActivity)
        initData()
        initListeners()
        readDataFromRealm()
    }

    private fun initData() {
        if (!::realm.isInitialized) {
            realm = getDefaultInstance()
        }
    }

    private fun initListeners() {
        insert.setOnClickListener(this@MainActivity)
    }

    private fun deleteDataFromLocalDatabase(data: User) {
        realm.beginTransaction()
        val result = realm.where(User::class.java).equalTo("name", data.name).findAll()
        result.deleteAllFromRealm()
        readDataFromRealm()
        realm.commitTransaction()
    }

    private fun validateTextFeilds(): Boolean {
        if (username.text.isEmpty()) {
            showToast("Username is Empty.")
            return false
        } else if (email.text.isEmpty()) {
            showToast("Email is Empty.")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            showToast("Email is not Valid.")
            return false
        }
        return true
    }

    private fun readDataFromRealm() {
        val list = ArrayList<User>()
        val result = realm.where(User::class.java).findAllSorted("name")

        for (data in result) {
            list.add(User(data.name, data.email , data.age , data.cnic , data.nationality))
        }
        setDataToRecyclerview(list)

    }

    private fun setDataToRecyclerview(list: ArrayList<User>) {
        val adapter = UserAdapter(list, this@MainActivity, this@MainActivity)
        user_recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun addUserToLocaldatabase() {
        realm.beginTransaction()

        realm.executeTransactionAsync(object : Realm.Transaction {
            override fun execute(realm: Realm?) {
                val user = realm?.createObject(User::class.java)
                user?.name = username.text.toString()
                user?.email = email.text.toString()
                user?.age = "19"
                user?.cnic = "1234567890"
                user?.nationality = "Pakistan"
            }
        }, object : Realm.Transaction.OnSuccess {
            override fun onSuccess() {
                username.setText("")
                email.setText("")
                readDataFromRealm()

            }
        }, object : Realm.Transaction.OnError {
            override fun onError(error: Throwable?) {
                Log.d("database", "onError")
            }
        })

        realm.commitTransaction()
    }

    // Override Methods
    override fun <T> getClickedObject(clickedObj: T, position: T, callingID: String) {
        when (callingID) {
            "User" -> {
                val data = clickedObj as User
                deleteDataFromLocalDatabase(data)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.insert -> {
                if (validateTextFeilds()) {
                    addUserToLocaldatabase()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
