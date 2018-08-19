package com.maycc.mycontacts

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_contact_detail.*

class ContactDetailActivity : AppCompatActivity() {

    private val tag = ContactDetailActivity::class.java.simpleName
    private lateinit var contact: Contact
    private val editContact = "EDIT CONTACT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        showBackButton()

        contact = intent.getSerializableExtra("CONTACT") as Contact

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.itemDelete -> {
                deleteContact()
                return true
            }

            R.id.itemEdit -> {
                sendContactToEdit()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteContact() {
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)

        showToast(this, "Contacto eliminado!!!")
        finish()
    }

    private fun sendContactToEdit() {
        val intent = Intent(this, NewContactActivity::class.java)
        intent.putExtra(editContact, contact)
        Log.d(tag, contact.lastName)
        startActivity(intent)
        finish()
    }

}
