package com.maycc.mycontacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contact_detail.*

class ContactDetailActivity : AppCompatActivity() {

    private val tag = ContactDetailActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contactPosition = intent.getIntExtra("CONTACT_POSITION", 0)
        val contact = MainActivity.getContact(contactPosition)

        showContactDetails(contact)
    }

    private fun showContactDetails(contact: Contact) {
        ivDetailContact.setImageResource(contact.img)
        tvDetailName.text = contact.name
        tvDetailLastName.text = contact.lastName
        tvDetailPhone.text = contact.phone
        tvDetailEmail.text = contact.email
    }
}
