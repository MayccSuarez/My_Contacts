package com.maycc.mycontacts

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var adapter: ContactAdapter
        private var contacts: ArrayList<Contact> = ArrayList()

        fun getContact(position: Int) :Contact {
            return adapter.getItem(position) as Contact
        }

        fun deleteContact(id: Int) {
            val position = adapter.findPositionContact(id)
            adapter.deleteItem(position)
        }

        fun updateContact(contact: Contact) {
            val position = adapter.findPositionContact(contact.id)
            adapter.updateItem(position, contact)
        }

        fun addContact(contact: Contact) {
            adapter.addItem(contact)
        }

        fun getNumberOfContacts() = adapter.count
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)
        loadData()
        initListViewContacts()
        addListenerListViewContacts()
    }

    private fun loadData() {
        contacts.add(Contact(1, "Maycc", "Suárez", "0985045480", "mr@gmail.com", R.drawable.foto_01))
        contacts.add(Contact(2, "Kevin", "Suárez", "0985045480", "kv@gmail.com", R.drawable.foto_02))
        contacts.add(Contact(3, "Anita", "Jiménez", "0985045480", "anita@gmail.com", R.drawable.foto_03))
        contacts.add(Contact(4, "Juan", "Mora", "0985045480", "juan@gmail.com", R.drawable.foto_04))

    }

    private fun initListViewContacts() {
        adapter = ContactAdapter(this, contacts)
        lvContacts.adapter = adapter
    }

    private fun addListenerListViewContacts() {
        lvContacts.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("CONTACT", getContact(position))
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        bindSearchView(menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun bindSearchView(menu: Menu?) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val itemSearch = menu?.findItem(R.id.itemSearch)
        val searchView = itemSearch?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        addListenerSearchView(searchView)
    }

    private fun addListenerSearchView(searchView: SearchView) {
        searchView.queryHint = "Nombre del contacto"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    adapter.search(newText)
                }
                return true
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.itemNew -> {
                val intent = Intent(this, NewContactActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}
