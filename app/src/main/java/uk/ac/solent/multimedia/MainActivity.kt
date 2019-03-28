package uk.ac.solent.multimedia

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.gesture.GestureLibraries.fromFile
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    val mediaFile = File("${Environment.getExternalStorageDirectory().getAbsolutePath()}/Music/3D_Printer_Slow_Edit_for_Android.mp3")

    override fun onDestroy(){
        super.onDestroy()
        unbindService(serviceConn)
    }

    val player = MediaPlayer()

    lateinit var serviceConn: ServiceConnection

    var service: Service? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startIntent = Intent(this, MusicService::class.java)
        startService(startIntent)
        val bindIntent = Intent(this, MusicService::class.java);
        bindService(bindIntent, serviceConn, Context.BIND_AUTO_CREATE)
        serviceConn = object: ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                service = ((binder as MusicService.MusicServiceBinder).getService())
            }

            override fun onServiceDisconnected(name: ComponentName?) {
            }
        }
        player.setAudioStreamType(AudioManager.STREAM_MUSIC)
        player.setDataSource(applicationContext, Uri.fromFile(mediaFile))

        player.setOnPreparedListener()

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

