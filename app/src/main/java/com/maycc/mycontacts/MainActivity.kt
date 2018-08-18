package com.maycc.mycontacts

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity() {
    private var contacts: ArrayList<Contact> = ArrayList()
    private lateinit var adapter: ContactAdapter

    private val addContactCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)
        loadData()
        initListViewContacts()
        addListenerListViewContacts()
    }

    private fun loadData() {
        contacts.add(Contact("Maycc", "Suárez", "0985045480", "mr@gmail.com", R.drawable.foto_01))
        contacts.add(Contact("Kevin", "Suárez", "0985045480", "kv@gmail.com", R.drawable.foto_02))
        contacts.add(Contact("Anita", "Jiménez", "0985045480", "anita@gmail.com", R.drawable.foto_03))
        contacts.add(Contact("Juan", "Mora", "0985045480", "juan@gmail.com", R.drawable.foto_04))

    }

    private fun initListViewContacts() {
        adapter = ContactAdapter(this, contacts)
        lvContacts.adapter = adapter
    }

    private fun addListenerListViewContacts() {
        lvContacts.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("CONTACT", contacts[position])
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.itemNew -> {
                val intent = Intent(this, NewContactActivity::class.java)
                startActivityForResult(intent, addContactCode)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == addContactCode) {
            if (resultCode == Activity.RESULT_OK) {
                val contact = data?.getSerializableExtra("ADD_CONTACT") as Contact
                addContact(contact)
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun addContact(contact: Contact) {
        contacts.add(contact)
    }

    override fun onResume() {
        adapter.notifyDataSetChanged()
        super.onResume()
    }

}
