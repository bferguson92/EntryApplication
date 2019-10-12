package com.example.entrydisplay.fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.entryapplication.R
import com.example.entryapplication.fragment.RelationFragment
import com.example.entryapplication.model.Customer
import kotlinx.android.synthetic.main.entry_fragment_layout.*

class EntryFragment : Fragment() {

    lateinit var enterCustomerListener: EnterCustomerListener

    interface EnterCustomerListener {
        fun addToCustomers(customer: Customer)
        fun setRelation()
    }

    fun setEnterCusomterListener(listener: EnterCustomerListener) {
        enterCustomerListener = listener
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.entry_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        entry_enter_button.setOnClickListener {
            enterCustomerListener.addToCustomers(
                Customer(
                    enter_name_edit_text.text.toString(),
                    enter_relation_text_view.text.toString()
                ))
        }

        enter_relation_text_view.setOnClickListener {
            enterCustomerListener.setRelation()
        }
    }

    fun obtainRelation(relation: String){
        enter_relation_text_view.text = relation
    }


}