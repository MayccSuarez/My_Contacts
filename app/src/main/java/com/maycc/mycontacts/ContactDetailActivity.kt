package com.maycc.mycontacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_contact_detail.*

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details_contact, menu)

        return super.onCreateOptionsMenu(menu)
    }

}
