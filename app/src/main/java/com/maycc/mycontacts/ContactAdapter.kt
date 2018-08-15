package com.maycc.mycontacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_contact_layout.view.*

class ContactAdapter(private val context: Context, val contacts: ArrayList<Contact>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        val viewHolder: ViewHolder?

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_contact_layout, null)
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

    private class ViewHolder(view: View) {
        val ivContact: ImageView = view.ivContact
        val tvName: TextView = view.tvName
        val tvPhone: TextView = view.tvPhone
    }
}