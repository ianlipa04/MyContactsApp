package com.example.contactsapimvvm.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapimvvm.R
import com.example.contactsapimvvm.entities.Data
import com.example.contactsapimvvm.ui.fragments.ContactDetailsFragment
import kotlinx.android.synthetic.main.item_contact_list.view.*

class ContactListRVAdapter(private val dataSet: List<Data>) :
    RecyclerView.Adapter<ContactListRVAdapter.VviewHolder>() {
    private val fragmentDetails = ContactDetailsFragment()
    private var mList: List<Data>? = null

    fun setListData(list: List<Data>) {
        this.mList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VviewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact_list, parent, false)
        return VviewHolder(view)
    }

    override fun onBindViewHolder(holder: VviewHolder, position: Int) {
        holder.bind(mList!![position])

        holder.itemView.setOnClickListener {
            when (holder.adapterPosition) {
                position -> {
                    fragmentDetails.setContactDatails(holder.adapterPosition, mList!!)
                    val appCompatActiviy = it.context as AppCompatActivity
                    appCompatActiviy.supportFragmentManager.beginTransaction()
                        .replace(R.id.cl_container, fragmentDetails)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class VviewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.name

        fun bind(data: Data) {
            val fn = data.first_name
            val ln = data.last_name
            name.text = fn.plus(" $ln")
        }
    }
}