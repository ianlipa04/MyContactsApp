package com.example.contactsapimvvm.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapimvvm.R
import com.example.contactsapimvvm.entities.Data
import com.example.contactsapimvvm.ui.adapters.ContactListRVAdapter
import com.example.contactsapimvvm.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel
    lateinit var rvAdapter: ContactListRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //    setRvData()
        setRvRoomRvData()
    }

    fun setRvData() {
/*        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.getContactListDataObserver().observe(this) {
            if (it != null) {
                recyclerView.layoutManager = LinearLayoutManager(this)
                rvAdapter = ContactListRVAdapter(it.data)
                val adapter = rvAdapter
                recyclerView.adapter = adapter
                rvAdapter.setListData(it.data)
                rvAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Failed to get contact list", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.loadContactListData()*/
    }

    fun setRvRoomRvData() {
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.getContactData().observe(this, Observer<List<Data>> {
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
        })
        viewModel.loadContactListData()
    }
}