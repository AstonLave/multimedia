package uk.ac.solent.multimedia

import android.gesture.GestureLibraries.fromFile
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    val mediaFile = File("${Environment.getExternalStorageDirectory().getAbsolutePath()}/Music/3D_Printer_Slow_Edit_for_Android.mp3")

    val player = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player.setAudioStreamType(AudioManager.STREAM_MUSIC)
        player.setDataSource(applicationContext, Uri.fromFile(mediaFile))

        player.setOnPreparedListener{ mp -> mp.start()
        }

        player.prepareAsync()

        playbut.setOnClickListener{
            player.start()
        }
        pausebut.setOnClickListener{
            player.pause()
        }
        rewindbut.setOnClickListener{
            player.seekTo(0)
        }
    }

}
