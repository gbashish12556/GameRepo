package com.example.android.architecture.blueprints.machinecodingporoject.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.android.architecture.blueprints.machinecodingporoject.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var optionsLayout:LinearLayout
    private lateinit var formedWord:LinearLayout
    private lateinit var gameImage:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        optionsLayout = findViewById(R.id.options)
        formedWord = findViewById(R.id.formedWord)
        gameImage = findViewById(R.id.gameImage)


        initLayout()
    }

    fun initLayout(){

        viewModel.currentGame.observe(this, Observer {game->

            Log.d("ASHISHGUPTA1",game.name);
            Log.d("ASHISHGUPTA1",game.imgUrl);

            Glide.with(this)
                .load(game.imgUrl)
                .into(gameImage)

            inflateWords(game.name);
        })

        viewModel.randomisedCharacter.observe(this, Observer {listOfCharacter->
            Log.d("ASHISHGUPTA2",listOfCharacter.toString());
            inflateOptions(listOfCharacter)
        })
    }

    fun inflateWords(gameName:String){

        for (character in gameName) {

            val textView = TextView(this)
            var params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(5,5,5,5)
            textView.layoutParams = params
            textView.setBackgroundColor(Color.GREEN)
            textView.gravity = Gravity.CENTER
            textView.text = character.toString()
            textView.textSize = 18f
            textView.setPadding(20,10,20,10)
            formedWord.addView(textView)

        }

    }

    fun inflateOptions(listOfCharacter:List<String>){

        for (i in 0..listOfCharacter.size-1) {

            val textView = TextView(this)
            var params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(5,5,5,5)
            textView.layoutParams = params

            textView.gravity = Gravity.CENTER
            textView.setBackgroundColor(Color.YELLOW)
            textView.text = listOfCharacter[i].toString()
            textView.textSize = 18f
            textView.setPadding(20,10,20,10)
            textView.setOnClickListener {view->
                var character  = (view as TextView).text.toString()
                if(viewModel.isCorrectCharcterChosen(character) == true){
                    viewModel.addFormedWord(character, i)
                }else{
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT);
                }
            }

            optionsLayout.addView(textView)

        }

    }

}