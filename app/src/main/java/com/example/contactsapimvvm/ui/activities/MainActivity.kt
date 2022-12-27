package com.example.contactsapimvvm.ui.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRvRoomRvData()
        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        val vf = supportFragmentManager.findFragmentById(R.id.cl_container)
        if (vf != null) {
            supportFragmentManager.popBackStack()
        } else {
            count++
            if (count < 2) {
                Toast.makeText(this, "Press again to exit...", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    count = 0
                }, 3000)
            } else if (count == 2) {
                finishAffinity()
            }
        }

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