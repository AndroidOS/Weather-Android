package com.example.weatherandroid.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherandroid.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            dialogueLocation()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun dialogueLocation() {
        val builder = AlertDialog.Builder(this@MainActivity)

        val view = this.layoutInflater.inflate(R.layout.dialog_edit_text, null)
        builder.setTitle("Enter City")

        builder.setView(view)

        builder.setPositiveButton("O.K") { dialog, which ->
        }

        builder.setNeutralButton("Cancel") { _, _ ->
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()

    }
}
