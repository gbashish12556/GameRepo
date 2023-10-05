package com.example.android.architecture.blueprints.machinecodingporoject.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.android.architecture.blueprints.machinecodingporoject.R
import com.nex3z.flowlayout.FlowLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var optionsLayout:FlowLayout
    private lateinit var formedWord:FlowLayout
    private lateinit var gameImage:ImageView

    private var listOfView = mutableListOf<View>()

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

            Glide.with(this)
                .load(game.imgUrl)
                .into(gameImage)

            inflateWords(game.name);
        })

        viewModel.randomisedCharacter.observe(this, Observer {listOfCharacter->

            inflateOptions(listOfCharacter)

        })

        viewModel.selectedTextIndex.observe(this, Observer {index->
            listOfView[index].visibility = View.VISIBLE
        })
    }

    fun inflateWords(gameName:String){

        formedWord.removeAllViews()
        listOfView.clear()
        for (character in gameName) {

            val textView = TextView(this)
            var params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            textView.visibility = View.INVISIBLE
            params.setMargins(5,5,5,5)
            textView.layoutParams = params
            textView.setBackgroundColor(Color.GREEN)
            textView.gravity = Gravity.CENTER
            textView.text = character.toString()
            textView.textSize = 18f
            textView.setPadding(20,10,20,10)
            listOfView.add(textView)
            formedWord.addView(textView)

        }

    }

    fun inflateOptions(listOfCharacter:List<String>){

        optionsLayout.removeAllViews()
        for (char in listOfCharacter) {

            val textView = TextView(this)
            var params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(5,5,5,5)
            textView.layoutParams = params

            textView.gravity = Gravity.CENTER
            textView.setBackgroundColor(Color.YELLOW)
            textView.text = char.toString()
            textView.textSize = 18f
            textView.setPadding(20,10,20,10)
            textView.setOnClickListener {view->
                lifecycleScope.launch {

                    if(viewModel.isCorrectCharcterChosen(char) == true){
                        viewModel.addFormedWord(char);
                    }
                }
            }

            optionsLayout.addView(textView)

        }

    }

}