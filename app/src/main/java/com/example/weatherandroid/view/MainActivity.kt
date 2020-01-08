package com.example.weatherandroid.view

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.weatherandroid.R
import com.example.weatherandroid.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_edit_text.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        viewModel = ViewModelProviders.of(this)[ListViewModel::class.java]

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


            DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(this, "${dialog}", Toast.LENGTH_LONG).show()
                Log.d(TAG, "${txt_city.text}")
            }

        }

        builder.setNeutralButton("Cancel") { _, _ ->

        }

        val dialog: AlertDialog = builder.create()

        dialog.show()

    }
}
