package com.maycc.mycontacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contact_detail.*
import kotlinx.android.synthetic.main.toolbar_detail.*

class ContactDetailActivity : AppCompatActivity() {

    private val tag = ContactDetailActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        showBackButton()

        val contact = intent.getSerializableExtra("CONTACT") as Contact

        showContactDetails(contact)
    }

    private fun showBackButton() {
        setSupportActionBar(toolBarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showContactDetails(contact: Contact) {
        ivDetailContact.setImageResource(contact.img)
        tvDetailName.text = "${contact.name} ${contact.lastName}"
        tvDetailPhone.text = contact.phone
        tvDetailEmail.text = contact.email
    }
}
