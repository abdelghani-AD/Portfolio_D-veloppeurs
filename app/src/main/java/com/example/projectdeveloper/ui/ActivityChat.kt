package com.example.projectdeveloper.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import com.example.projectdeveloper.R

class ActivityChat : AppCompatActivity() {
    var chatMsg = ArrayList<String>()
    lateinit var adapter:ArrayAdapter<String>
    lateinit var listChat:ListView
    lateinit var msgChatText:EditText
    lateinit var iconSend:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        msgChatText = findViewById(R.id.messageEditText)
        listChat = findViewById(R.id.chatListView)
        iconSend = findViewById(R.id.send)

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,chatMsg)
        listChat.adapter = adapter

        iconSend.setOnClickListener{
            var msg = msgChatText.text.toString().trim()
            if (msg.isNotEmpty()){
                addMessage("You : ${msg}")
                msgChatText.text.clear()
            }
        }
        var retour:ImageView = findViewById(R.id.retoureH)
        retour.setOnClickListener{
            var intent = Intent(this, PageHome::class.java)
            startActivity(intent)
        }
    }
    fun addMessage(msg:String){
        chatMsg.add(msg)
        adapter.notifyDataSetChanged()
    }
}