package com.example.calismadeneme

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.support.v4.os.IResultReceiver2.Default
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatViewInflater
import com.example.calismadeneme.databinding.ActivityMainBinding
import kotlinx.coroutines.handleCoroutineException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
 var score=0
    var runnable=  Runnable{   }
    var hanler=Handler(Looper.getMainLooper())

        var imageArray=ArrayList<ImageView>()
    private lateinit var imageView: ImageView
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        hideImages()
//ImageArray


        //countDownTimer
        object : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {
                binding.timeText.text="Süre:${p0/1000}"
            }

            override fun onFinish() {
binding.timeText.text="Süre = 0"
                hanler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                //Alert Dialog Kullanıcı Tekrar Oynamak İsterse
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Oyun Sonu")
                alert.setMessage("Tekrar Denemek İster Misiniz?")
                alert.setPositiveButton("Evet"){dialogInterface,i->

//restart
                    val intentFormMain=intent
                    finish()
                    startActivity(intentFormMain)

                }
                alert.setNegativeButton("Hayır",DialogInterface.OnClickListener{dialog: DialogInterface?, which: Int ->
                    Toast.makeText(this@MainActivity,"Game Over!",Toast.LENGTH_LONG).show()
                })
                alert.show()
            }

        }.start()
    }

    fun hideImages(){
        runnable=object:Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random= java.util.Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE
                hanler.postDelayed(runnable,500)//oyundaki resimlerin hızı
            }

        }
hanler.post(runnable)
//binding.imageView1.visibility=View.GONE
//        binding.imageView2.visibility=View.INVISIBLE
//        binding.imageView3.visibility=View.INVISIBLE
//        binding.imageView4.visibility=View.INVISIBLE
//        binding.imageView5.visibility=View.INVISIBLE
//        binding.imageView6.visibility=View.INVISIBLE
//        binding.imageView7.visibility=View.INVISIBLE
//        binding.imageView8.visibility=View.INVISIBLE
//        binding.imageView9.visibility=View.INVISIBLE

        //view.gone ve view.ınvısıble görünmez yapıyor. View.Visible de görünür yapıyor.
        //gone yaptığımızda tamamı ile gider yerine başka bir şey varsa o gelir text vs.

    }
    fun increaseScore(view:View){
        score=score+1
  binding.ScoreText.text="Score:${score}"
    }
}