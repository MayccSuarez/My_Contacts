package com.maycc.mycontacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ContactDetailActivity : AppCompatActivity() {

    private val tag = ContactDetailActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contactPosition = intent.getIntExtra("CONTACT_POSITION", 0)
        Log.d(tag, contactPosition.toString())
    }
}
