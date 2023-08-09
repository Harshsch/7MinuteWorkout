package com.example.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.Log.w
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.databinding.ActivityExerciseBinding
import com.example.a7minuteworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding:ActivityExerciseBinding? =null
    private var restTimer : CountDownTimer?=null
    private var restProgress=0
    private var restTimerDuration:Long=1
    private var ExerciseTimer : CountDownTimer?=null
    private var ExerciseProgress=0
    private var excerciseTimerDuration:Long=1
    private var exerciseList:ArrayList<ExcerciseModel>?=null
    private var currentExercisePosition =-1
    private var tts : TextToSpeech?=null
    private var mplayer:MediaPlayer?=null
    private var exerciseAdapter:ExerciseStatusAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)
        if (supportActionBar!=null)
        {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener{
            customDialogForBackButton()
        }
        exerciseList= Constants.defaultExerciseList()
        tts= TextToSpeech(this,this  )


        setuprestView()
        setpExerciseStatusRecyclerView()

    }

    override fun onBackPressed() {
        customDialogForBackButton()

    }

    private fun customDialogForBackButton() {
        val customDialog = Dialog(this)
        val dialogBinding =DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnyes.setOnClickListener{
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.btnno.setOnClickListener{
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun setpExerciseStatusRecyclerView(){
        binding?.rvexerciseStatus?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter =ExerciseStatusAdapter(exerciseList!!)
        binding?.rvexerciseStatus?.adapter=exerciseAdapter


    }

    private  fun setuprestView()
    {
        try {
            val soundURI = Uri.parse(
                "android.resource://com.example.a7minuteworkout/"+R.raw.app_src_main_res_raw_press_start)
            mplayer=MediaPlayer.create(applicationContext,soundURI)
            mplayer?.isLooping=false
            mplayer?.start()
        }catch (e:Exception)
        {
            e.printStackTrace()
        }
        binding?.flRestView?.visibility=View.VISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.upcomingexercise?.visibility=View.VISIBLE
        binding?.upComingExercoiseName?.visibility=View.VISIBLE
        binding?.tvExerciseName?.visibility=View.INVISIBLE
        binding?.flExerciseView?.visibility=View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE
        if (restTimer!=null)
        {
            restTimer!!.cancel()
            restProgress =0
        }
        speaktext("take rest for 10 seconds")
        binding?.upComingExercoiseName?.text=exerciseList!![currentExercisePosition+1].getname()
        setRestProgressBar()
    }
    private  fun setupExerciseView()
    {
        binding?.flRestView?.visibility=View.INVISIBLE
        binding?.tvTitle?.visibility=View.INVISIBLE
        binding?.upcomingexercise?.visibility=View.INVISIBLE
        binding?.upComingExercoiseName?.visibility=View.INVISIBLE
        binding?.tvExerciseName?.visibility=View.VISIBLE
        binding?.flExerciseView?.visibility=View.VISIBLE
        binding?.ivImage?.visibility=View.VISIBLE

        if (ExerciseTimer!=null)
        {
            ExerciseTimer!!.cancel()
            ExerciseProgress =0
        }
        speaktext(exerciseList!![currentExercisePosition].getname())
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getimage())
        binding?.tvExerciseName?.text=exerciseList!![currentExercisePosition].getname()
        setExerciseProgressBar()
    }

    private fun setRestProgressBar()
    {

        binding?.progressbar?.progress =restProgress
        restTimer=object :CountDownTimer(restTimerDuration*10000,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressbar?.progress=10-restProgress
                binding?.tvTimer?.text =(10-restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setisSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                exerciseList!![currentExercisePosition].setisCompleted(false )
                exerciseAdapter!!.notifyDataSetChanged()

                setupExerciseView()
            }
        }.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (restTimer!=null)
        {
            restTimer!!.cancel()
            restProgress =0
        }
        if (ExerciseTimer!=null)
        {
            ExerciseTimer!!.cancel()
            ExerciseProgress =0
        }
        if (tts!=null)
        {
            tts?.stop()
            tts?.shutdown()
        }
        if (mplayer!=null)
        {
            mplayer?.stop()

        }
        binding=null
    }

    private fun setExerciseProgressBar()
    {
        binding?.progressBarExercise?.progress =ExerciseProgress
        ExerciseTimer=object :CountDownTimer(excerciseTimerDuration*30000,1000)
        {
            override fun onTick(millisUntilFinished: Long) {

                ExerciseProgress++

                binding?.progressBarExercise?.progress=30-ExerciseProgress
                binding?.tvTimerExercise?.text =(30-ExerciseProgress).toString()
            }

            override fun onFinish() {
                exerciseList!![currentExercisePosition].setisCompleted(true )
                exerciseAdapter!!.notifyDataSetChanged()
                exerciseList!![currentExercisePosition].setisSelected(false)

                exerciseAdapter!!.notifyDataSetChanged()
               if (currentExercisePosition<exerciseList?.size!!-1)
               {
                   setuprestView()
               }
                else
                   {
                       speaktext("Congratulations! You have completed the 7minutes workout")
                       finish()
                       val intent =Intent(this@ExerciseActivity,FinishActivity::class.java)
                       startActivity(intent)

                   }

            }
        }.start()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

        }
        if (status == TextToSpeech.LANG_MISSING_DATA || status == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.e("TTS", "The Language specified is not supported!")
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }
    private fun speaktext(text:String) {
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")

    }


}