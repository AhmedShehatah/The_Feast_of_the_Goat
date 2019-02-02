package com.example.questions

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class second : AppCompatActivity() {

    private var val_que: Array<String>? = null
    private var val_ans: Array<String>? = null
    var index: Int? = null
    var TtoS: TextToSpeech? = null
    var res: Int? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        TtoS = TextToSpeech(this, TextToSpeech.OnInitListener { res = TtoS!!.setLanguage(Locale.ENGLISH) })

        val_que = resources.getStringArray(R.array.ques)
        val_ans = resources.getStringArray(R.array.ans)
        index = 0
        tv_question.text = (val_que as Array<String>)[index!!]
        tv_xx.text = (index!! + 1).toString()
        tv_yy.text = "/${val_que!!.size}"


    }

    fun Onclick(v: View?) {
        try {
            when (v!!.id) {
                R.id.btn_next -> {
                    if (index!! < 9) {
                        index = index!! + 1
                        tv_question.text = (val_que as Array<String>)[index!!]
                        tv_xx.text = (index!! + 1).toString()
                        tv_answer.text = resources.getString(R.string.press_button_a_to_get_the_answer)
                    } else index = 9
                }
                R.id.btn_pre -> {
                    if (index!! > 0) {
                        index = index!! - 1
                        tv_question.text = (val_que as Array<String>)[index!!]
                        tv_xx.text = (index!! + 1).toString()
                        tv_answer.text = resources.getString(R.string.press_button_a_to_get_the_answer)
                    } else index = 0
                }
                R.id.btn_answer -> {

                    tv_answer.text = (val_ans as Array<String>)[index!!]
                }
                R.id.btn_voice -> {

                    if (res == TextToSpeech.LANG_NOT_SUPPORTED || res == TextToSpeech.LANG_MISSING_DATA) {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    } else {
                        TtoS!!.speak(tv_question.text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }

                }
            }
        } catch (ex: Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        }
    }
}


