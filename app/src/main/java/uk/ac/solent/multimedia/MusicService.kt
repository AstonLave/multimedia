package uk.ac.solent.multimedia

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MusicService: Service(){

    inner class MusicServiceBinder : Binder(){
        fun getService() : MusicService
        {
            return this@MusicService // the outer 'this'
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return MusicServiceBinder()
    }
}