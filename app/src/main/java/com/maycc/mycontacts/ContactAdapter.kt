package com.maycc.mycontacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_contact_layout.view.*

class ContactAdapter(private val context: Context, private var contacts: ArrayList<Contact>) : BaseAdapter() {
    private val copyContacts: ArrayList<Contact> = ArrayList(contacts)

    companion object {
        var itemLayout = 0

        fun setItemL(id: Int) {
            itemLayout = id
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        val viewHolder: ViewHolder?

        if (view == null) {
            view = LayoutInflater.from(context).inflate(itemLayout, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        val contact = getItem(position) as Contact
        viewHolder.ivContact.setImageResource(contact.img)
        viewHolder.tvName.text = contact.name
        viewHolder.tvPhone.text = contact.phone

        return view!!
    }

    override fun getItem(position: Int): Any {
        return contacts[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return contacts.count()
    }

    fun search(txt: String ) {
        contacts.clear()

        if (txt.isEmpty()) {
            contacts = ArrayList(copyContacts)
        } else {
            var nameContact: String

            for (contact in copyContacts) {
                nameContact = contact.name.toLowerCase()

                if (nameContact.contains(txt.toLowerCase()))
                    contacts.add(contact)
            }
        }

        notifyDataSetChanged()
    }

    fun findPositionContact(id :Int) :Int {
        val position :Int

        for (i in copyContacts.indices) {
            val idContact = copyContacts[i].id
            if (idContact == id) {
                position = i
                return position
            }
        }

        return -1
    }

    fun deleteItem(position: Int) {
        copyContacts.removeAt(position)
        contacts = ArrayList(copyContacts)
        notifyDataSetChanged()
    }

    fun updateItem(position: Int, contact: Contact) {
        copyContacts[position] = contact
        contacts = ArrayList(copyContacts)
        notifyDataSetChanged()
    }

    fun addItem(contact: Contact) {
        copyContacts.add(contact)
        contacts = ArrayList(copyContacts)
        notifyDataSetChanged()
    }

    private class ViewHolder(view: View) {
        val ivContact: ImageView = view.ivContact
        val tvName: TextView = view.tvName
        val tvPhone: TextView = view.tvPhone
    }
}