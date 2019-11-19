package com.example.gesturapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gesture_status.setOnLongClickListener(){
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener {item ->
                when (item.itemId) {
                    R.id.menu_share -> {
                        val intent = Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com"))
                        startActivity(intent)
                        true
                    }
                    R.id.menu_info -> {
                        Toast.makeText(this, "Ada Toast!", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.main_main)

            try {
                val fieldMpopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMpopup.isAccessible = true
                val mPopup = fieldMpopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setFroceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                popupMenu.show()
            }
            true
        }
    }
}
