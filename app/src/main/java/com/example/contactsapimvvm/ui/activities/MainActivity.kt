package com.example.contactsapimvvm.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapimvvm.R
import com.example.contactsapimvvm.ui.adapters.ContactListRVAdapter
import com.example.contactsapimvvm.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var rvAdapter: ContactListRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRvRoomRvData()
    }

    fun setRvRoomRvData() {
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.getContactData().observe(this) {
            if (it != null) {
                recyclerView.layoutManager = LinearLayoutManager(this)
                rvAdapter = ContactListRVAdapter(it)
                val adapter = rvAdapter
                recyclerView.adapter = adapter
                rvAdapter.setListData(it)
                rvAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Failed to get contact list", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.loadContactListData()
    }
}