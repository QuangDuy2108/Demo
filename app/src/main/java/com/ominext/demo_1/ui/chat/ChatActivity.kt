package com.ominext.demo_1.ui.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.model.Chat
import com.ominext.demo_1.ui.chat.adapter.ChatAdapter
import com.ominext.demo_1.util.closeKeyboard
import com.ominext.demo_1.util.getUserid
import com.ominext.demo_1.util.getUsername
import com.ominext.demo_1.util.isNetworkConnected
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*
import javax.inject.Inject

class ChatActivity : AppCompatActivity(), ChatContact.View {

    @Inject
    lateinit var presenter: ChatPresenter
    private var chatAdapter = ChatAdapter()
    private var listChat = ArrayList<Chat>()
    private var firebaseDatabase = FirebaseDatabase.getInstance()
    private val root = firebaseDatabase.getReference("ichat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        Realm.init(applicationContext)
        DaggerAppComponent.builder().build().inject(this)
        presenter.attach(this)
        setRcv()


    }

    override fun onResume() {
        super.onResume()
        if (isNetworkConnected(this@ChatActivity)) {
            Toast.makeText(this@ChatActivity, "Firebase", Toast.LENGTH_LONG).show()
            root.addValueEventListener(valueEvent)
            chat_input.setOnEditorActionListener(onEditorAction)
        } else {
            Toast.makeText(this@ChatActivity, "Realm", Toast.LENGTH_LONG).show()
            listChat = Chat.convertFromRealm(presenter.loadChat())
            chatAdapter.setListChat(listChat)
        }

    }

    private val valueEvent = object : ValueEventListener {
        override fun onCancelled(databaseError: DatabaseError) {

        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            listChat.clear()
            presenter.clearChat()
            for (dataSnap: DataSnapshot in dataSnapshot.children) {
                val chat = dataSnap.getValue(Chat::class.java)!!
                listChat.add(chat)
                presenter.addChat(chat)
            }
            chatAdapter.setListChat(listChat)
            rcv_chat.scrollToPosition(listChat.size - 1)
        }
    }

    private val onEditorAction = object : TextView.OnEditorActionListener {
        override fun onEditorAction(textView: TextView?, int: Int, keyEvent: KeyEvent?): Boolean {

            val chat = Chat(
                getUserid(this@ChatActivity)!!,
                getUsername(this@ChatActivity)!!,
                chat_input.text.trim().toString(),
                Date().time.toString()
            )
            root.child(Date().time.toString()).setValue(chat)

            closeKeyboard(this@ChatActivity, findViewById(R.id.chat_input))
            chat_input.setText("")

            return true
        }

    }

    private fun setRcv() {
        rcv_chat.layoutManager = LinearLayoutManager(this)
        rcv_chat.adapter = chatAdapter
        chatAdapter.setOnItemClick(object : ChatAdapter.OnItemClick {
            override fun onItemClickListener(position: Int) {

            }
        })
    }
}
