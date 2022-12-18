package com.example.contactsapimvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.contactsapimvvm.R
import com.example.contactsapimvvm.entities.Data
import com.example.contactsapimvvm.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_contact_detail.*

@AndroidEntryPoint
class ContactDetailsFragment : Fragment() {

    private var contactData: List<Data>? = null
    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        LayoutInflater.from(context).inflate(R.layout.fragment_contact_detail, container, false)
            .apply {
                setViewModelRoom()
            }

    fun setContactDatails(pos: Int, data: List<Data>) {
        this.contactData = data
        this.position = pos
    }

    fun setViewModelRoom() {
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.getContactData().observe(viewLifecycleOwner) {
            if (it != null) {
                it[position].apply {
                    val fn = it[position].first_name
                    val ln = it[position].last_name
                    iv_profile.load(it[position].avatar)
                    tv_name.text = fn.plus(" $ln")
                    tv_email.text = it[position].email
                }
            } else {
                Toast.makeText(activity, "Failed to load User Data", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.loadContactListData()
    }
}