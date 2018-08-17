package com.maycc.mycontacts

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity() {

    var contacts: ArrayList<Contact> = ArrayList()

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
        val adapter = ContactAdapter(this, contacts)
        lvContacts.adapter = adapter
    }

    private fun addListenerListViewContacts() {
        lvContacts.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("CONTACT_POSITION", position)
            intent.putExtra("CONTACT", contacts[position])
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }
}
