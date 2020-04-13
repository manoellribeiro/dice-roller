package com.example.diceroller

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var diceImage: ImageView
    lateinit var resultListView: ListView
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()
            showToast()
            customAdapter.notifyDataSetChanged()
        }
        diceImage = findViewById(R.id.dice_image)
        resultListView = findViewById(R.id.results_list)
        customAdapter = CustomAdapter(this, lastResultsImageList)
        resultListView.adapter = customAdapter
    }

    class CustomAdapter(context: Context, private var values: List<Int>) :
            ArrayAdapter<Int>(context, R.layout.last_result_list_item, values){

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val rowView: LinearLayout = LayoutInflater.from(context).inflate(
                R.layout.last_result_list_item,
                parent,
                false) as LinearLayout
            rowView.findViewById<ImageView>(R.id.last_result_item).setImageResource(values[position])
            rowView.findViewById<TextView>(R.id.item_number).text = (values.size - position).toString()
            return rowView
        }
    }

    var lastResultsImageList: ArrayList<Int> = ArrayList<Int>()
    fun rollDice() {
        val diceDrawableResource = when (Random().nextInt(6) + 1){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(diceDrawableResource)
        lastResultsImageList.add(0, diceDrawableResource)
    }

    fun showToast(){
        if (lastResultsImageList.size > 1){
            if (lastResultsImageList[0] == lastResultsImageList[1]){
                val toast = Toast.makeText(applicationContext, "You got twice in a roll", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 160, 200)
                toast.show()
            }
        }
    }

}
