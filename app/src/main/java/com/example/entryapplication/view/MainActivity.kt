package com.example.entryapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.entryapplication.R
import com.example.entryapplication.fragment.RelationFragment
import com.example.entryapplication.model.Customer
import com.example.entrydisplay.database.CustomerDatabase
import com.example.entrydisplay.database.CustomerEntity
import com.example.entrydisplay.fragments.EntryFragment

class MainActivity : AppCompatActivity(), EntryFragment.EnterCustomerListener, RelationFragment.RelationFragmentListener {

    private val entryFragment = EntryFragment()
    private val relationFragment = RelationFragment()
    private lateinit var relationString: String
    private lateinit var myDAO: CustomerDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDAO = Room.databaseBuilder(
            this,
            CustomerDatabase::class.java,
            "customer.db"
        )
            .allowMainThreadQueries()
            .build()

        entryFragment.setEnterCusomterListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.entry_frame_layout, entryFragment)
            .commit()

    }

    override fun addToCustomers(customer: Customer) {
        val input = CustomerEntity(customer.name, customer.relation)
        myDAO.customerDAO().insertAllCustomer(input)
    }

    override fun obtainRelation(): String {
        relationFragment.setRelationFragmentListener(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.relation_frame_layout, relationFragment).commit()
        relationString = ""
        return relationString
    }

    override fun sendRelation(relation: String){
        relationString = relation
    }

}
