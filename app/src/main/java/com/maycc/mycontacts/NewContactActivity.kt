package com.maycc.mycontacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_new_contact.*

class NewContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        showBackButton()
    }

    private fun showBackButton() {
        setSupportActionBar(toolbarNewContact)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_new_contact, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home ->  {
                finish()
                return true
            }

            R.id.itemSave -> {
                val emptyFields = validateFields()

                if (emptyFields == 0) {
                    saveContact()
                    finish()
                } else {
                    showToast(this, "Todos los campos son obligatorios!!!")
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun validateFields() :Int {
        var count = 0

        val inputs = arrayListOf<String>()
        inputs.add(edtName.text.toString())
        inputs.add(edtLastName.text.toString())
        inputs.add(edtPhone.text.toString())
        inputs.add(edtEmail.text.toString())

        for (i in inputs) {
            if (i.isEmpty())
                count++
        }

        return count
    }

    private fun saveContact() {
        val name = edtName.text.toString()
        val lastName = edtLastName.text.toString()
        val phone = edtPhone.text.toString()
        val email = edtEmail.text.toString()
        val photo = R.drawable.foto_01

        MainActivity.addContact(Contact(name, lastName, phone, email, photo))
    }
}
