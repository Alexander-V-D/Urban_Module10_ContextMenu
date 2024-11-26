package com.example.contextmenu

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var markET: EditText
    private lateinit var randomNumberBTN: Button
    private lateinit var randomNumberTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        markET = findViewById(R.id.markET)
        registerForContextMenu(markET)

        randomNumberBTN = findViewById(R.id.randomNumberBTN)
        randomNumberTV = findViewById(R.id.randomNumberTV)
        registerForContextMenu(randomNumberTV)
        randomNumberBTN.setOnClickListener {
            randomNumberTV.text = String.format((1..50).random().toString())
            randomNumberTV.setTextColor(Color.BLACK)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        when(v?.id) {
            R.id.randomNumberTV -> menuInflater.inflate(R.menu.context_menu_random, menu)
            R.id.markET -> menuInflater.inflate(R.menu.context_menu, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.colorQuality -> {
                markET.setBackgroundColor(
                    when (markET.text.toString()) {
                        "1" -> Color.parseColor("#ffa500")
                        "2" -> Color.YELLOW
                        "3" -> Color.GREEN
                        "4" -> Color.BLUE
                        "5" -> Color.RED
                        else -> Color.TRANSPARENT
                    }
                )
            }
            R.id.exit -> finish()
            R.id.colorQualityRandom -> {
                randomNumberTV.setTextColor(
                    when (randomNumberTV.text.toString().toInt()) {
                        in 1..10 -> Color.RED
                        in 11..20 -> Color.parseColor("#ffa500")
                        in 21..30 -> Color.YELLOW
                        in 31..40 -> Color.GREEN
                        in 41..50 -> Color.BLUE
                        else -> Color.TRANSPARENT
                    }
                )
            }
        }
        return super.onContextItemSelected(item)
    }
}