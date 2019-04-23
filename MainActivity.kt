package com.example.priyamvad.simple_videoapp

import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.net.URI

class MainActivity : AppCompatActivity() {

    var dbtn:Button?=null
    var tview:TextView?=null
    var tview1:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         dbtn=findViewById(R.id.downloadbtn)
         tview=findViewById(R.id.tview)
         tview1=findViewById(R.id.tview1)

    }
    fun download(view:View)
    {
        var storage = FirebaseStorage.getInstance()
        var  mStorageRef=storage.reference
        var video_ref=mStorageRef.child("Stree.2018.720p.WEBRip.x264-[YTS.AM].mp4")
        var name=video_ref.name

        video_ref.downloadUrl.addOnSuccessListener{
            var downloaded=this@MainActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var uri:Uri=Uri.parse(it.toString())
            var request:DownloadManager.Request=DownloadManager.Request(uri)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalFilesDir(this@MainActivity,DIRECTORY_DOWNLOADS,name)
            downloaded.enqueue(request)
        }
    }
}

