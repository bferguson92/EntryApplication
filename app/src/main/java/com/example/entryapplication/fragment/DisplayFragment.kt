package com.example.entryapplication.fragment

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.entryapplication.R
import com.example.entryapplication.adapter.DisplayAdapter
import com.example.entryapplication.model.Customer
import com.example.entryapplication.util.Logger
import com.example.entrydisplay.database.CustomerDatabase
import com.example.entrydisplay.database.CustomerEntity
import kotlinx.android.synthetic.main.display_fragment_layout.view.*

class DisplayFragment : Fragment() {
    private lateinit var dao: CustomerDatabase
    private val customerList = mutableListOf<Customer>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.display_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = Room.databaseBuilder(view.context, CustomerDatabase::class.java, "customer.db")
            .allowMainThreadQueries().build()

        val customerEntites = dao.customerDAO().getAllCustomer()
        customerEntites.moveToFirst()


        try {

            customerList.add(getCustomer(customerEntites))

            while(customerEntites.moveToNext()){
              customerList.add(getCustomer(customerEntites))
                Logger.error(Throwable("Getting Customer"))
            }

        } catch (throwable: Throwable) {
            Log.e("TAG_ERROR", throwable.toString())
        }



        view.display_recycler_view.adapter = DisplayAdapter(customerList)
        view.display_recycler_view.layoutManager = LinearLayoutManager(view.context)
    }

    private fun getCustomer(customerEntites: Cursor): Customer {
        return  Customer(
            customerEntites.getString(customerEntites.getColumnIndex("customer_name")),
            customerEntites.getString(customerEntites.getColumnIndex("customer_relation"))
        )
    }

    fun updateView(customer: Customer){
        customerList.add(customer)
        view?.display_recycler_view?.adapter?.notifyDataSetChanged()
    }


}