package com.example.entryapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.entryapplication.R
import com.example.entryapplication.fragment.DisplayFragment
import com.example.entryapplication.fragment.RelationFragment
import com.example.entryapplication.model.Customer
import com.example.entrydisplay.database.CustomerDatabase
import com.example.entrydisplay.database.CustomerEntity
import com.example.entrydisplay.fragments.EntryFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

//Testing Android Terminal
class MainActivity : AppCompatActivity(), EntryFragment.EnterCustomerListener,
    RelationFragment.RelationFragmentListener {

    private val entryFragment = EntryFragment()
    private val relationFragment = RelationFragment()
    private val displayFragment = DisplayFragment()
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

        clear_button.setOnClickListener{
            myDAO.customerDAO().deleteAllCustomers()
            displayFragment.clearView()
        }

        entryFragment.setEnterCusomterListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.entry_frame_layout, entryFragment)
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.display_frame_layout, displayFragment).commit()


    }

    override fun addToCustomers(customer: Customer) {
        val input = CustomerEntity(customer.name, customer.relation)
        myDAO.customerDAO().insertNewCustomer(input)
        displayFragment.updateView(customer)
    }

    override fun setRelation() {
        relationFragment.setRelationFragmentListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.relation_frame_layout, relationFragment).commit()
    }
    override fun sendRelation(relation: String) {
        entryFragment.obtainRelation(relation)
    }
}
