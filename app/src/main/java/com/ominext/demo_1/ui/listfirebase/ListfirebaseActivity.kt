package com.ominext.demo_1.ui.listfirebase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.model.Member
import com.ominext.demo_1.ui.listfirebase.adapter.MemberAdapter
import kotlinx.android.synthetic.main.activity_listfirebase.*
import javax.inject.Inject

class ListfirebaseActivity : AppCompatActivity(), ListfirebaseContact.View {
    @Inject
    lateinit var presenter: ListfirebasePresenter
    private var member = Member()
    private var memberAdapter = MemberAdapter()
    private var listMember = ArrayList<Member>()
    private var firebaseDatabase = FirebaseDatabase.getInstance()
    private val root = firebaseDatabase.getReference("members")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listfirebase)
        DaggerAppComponent.builder().build().inject(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        presenter.attach(this)
        setRcv()

        root.addValueEventListener(valuesEvent)
        btnSubmit.setOnClickListener { presenter.onSubmit() }
        btnAdd.setOnClickListener { presenter.onAdd() }
        btnUpdate.setOnClickListener { presenter.onUpdate() }
    }

    override fun onSubmit() {

        if (edtName.text.trim().isEmpty() || edtAddress.text.trim().isEmpty()) {
            Toast.makeText(this, "Null values", Toast.LENGTH_LONG).show()
        } else {
            listMember.add(Member(listMember.size, edtName.text.toString(), edtAddress.text.toString()))

            for (item in listMember) {
                root.child(item.id.toString()).setValue(item)
            }
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
        }
    }

    override fun onAdd() {
        for (item in listMember) {
            root.child(item.id.toString()).setValue(item)
        }
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
    }

    override fun onUpdate() {
    }
    private val valuesEvent = object : ValueEventListener {
        override fun onCancelled(databaseError: DatabaseError) {

        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            listMember.clear()
            for (dataSnap: DataSnapshot in dataSnapshot.children) {
                val member = dataSnap.getValue(Member::class.java)!!
                listMember.add(member)
            }
            memberAdapter.setListMember(listMember)
        }

    }



    private fun setRcv() {
        memberAdapter.setListMember(listMember)
        rcv_firebase.layoutManager = LinearLayoutManager(this)
        rcv_firebase.adapter = memberAdapter
        memberAdapter.setOnItemClick(object : MemberAdapter.OnItemClick {
            override fun onItemClickListener(position: Int) {
                member = listMember[position]
                edtAddress.setText(member.address)
                edtName.setText(member.name)
            }

        })
    }
}
