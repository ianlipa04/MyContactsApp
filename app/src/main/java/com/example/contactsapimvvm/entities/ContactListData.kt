package com.example.contactsapimvvm.entities

data class ContactListData(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)