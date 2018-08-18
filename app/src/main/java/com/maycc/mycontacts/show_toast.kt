package com.maycc.mycontacts

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

fun showToast(activity: AppCompatActivity, txt: String) {
    Toast.makeText(activity, txt, Toast.LENGTH_SHORT).show()
}