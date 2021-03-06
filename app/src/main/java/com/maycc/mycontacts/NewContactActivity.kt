package com.maycc.mycontacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_new_contact.*

class NewContactActivity: AppCompatActivity() {

    private val photos = arrayOf(R.drawable.foto_01, R.drawable.foto_02, R.drawable.foto_03,
                        R.drawable.foto_04, R.drawable.foto_05, R.drawable.foto_06)
    private var selectedPhoto = photos[0]
    private var idContactToUpdate = -1
    private var update = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        if (intent.hasExtra("EDIT CONTACT")) {
            update = 1

            val contactToEdit = intent.getSerializableExtra("EDIT CONTACT") as Contact
            selectedPhoto = contactToEdit.img
            idContactToUpdate = contactToEdit.id
            showDataContact(contactToEdit)
        }

        showBackButton()
        addListenerIVContact()
    }

    private fun showDataContact(contact: Contact) {
        ivContact.setImageResource(contact.img)
        edtName.setText(contact.name)
        edtLastName.setText(contact.lastName)
        edtPhone.setText(contact.phone)
        edtEmail.setText(contact.email)
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
            android.R.id.home -> {
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
        val photo = selectedPhoto

        val contact = Contact(idContactToUpdate, name, lastName, phone, email, photo)

        if (update == 1) {
            MainActivity.updateContact(contact)
            showToast(this, "Contacto actualizado!!!")

        // Create Contact
        } else {
            val number = MainActivity.getNumberOfContacts()
            val idContactNew = number + 1
            contact.id = idContactNew
            MainActivity.addContact(contact)

            showToast(this, "Contacto creado!!!")
        }
    }

    private fun addListenerIVContact() {
        ivContact.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val alert = AlertDialog.Builder(this)

        val adapterDialog = ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item)
        adapterDialog.add("Foto 01")
        adapterDialog.add("Foto 02")
        adapterDialog.add("Foto 03")
        adapterDialog.add("Foto 04")
        adapterDialog.add("Foto 05")
        adapterDialog.add("Foto 06")

        alert.setTitle("Selecciona una imagen: ")
        
        alert.setAdapter(adapterDialog) {dialog, which ->
            setPhoto(which)
        }

        alert.setNegativeButton("CANCELAR") {dialog, which ->
            dialog.dismiss()
        }

        alert.show()
    }

    private fun setPhoto(index: Int) {
        selectedPhoto = photos[index]
        ivContact.setImageResource(selectedPhoto)
    }
}
