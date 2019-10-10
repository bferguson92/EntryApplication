package com.example.entryapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.entryapplication.R
import kotlinx.android.synthetic.main.relation_fragment_layout.*

class RelationFragment : Fragment() {
    private lateinit var relationFragmentListener: RelationFragmentListener

    interface RelationFragmentListener{
        fun sendRelation(relation: String)
    }

    fun setRelationFragmentListener(listener: RelationFragmentListener){
        relationFragmentListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.relation_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        friend_button.setOnClickListener{
            relationFragmentListener.sendRelation(friend_button.text.toString())
        }
    }
}