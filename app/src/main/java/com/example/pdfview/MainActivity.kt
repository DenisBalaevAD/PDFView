package com.example.pdfview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pdfview.databinding.ActivityMainBinding
import com.github.barteksc.pdfviewer.listener.*
import com.google.android.material.internal.ContextUtils.getActivity
import java.io.File


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pdfv.apply {
            useBestQuality(true)
            fitToWidth()
            fromAsset("instruction2.pdf")
                .swipeHorizontal(true)
                .load()
        }
        /*val filePath = intent.getStringExtra("filePath")
        val file = filePath?.let { File(it) }
        startActivity(
            Intent(this, MainActivity2::class.java).putExtra(
                "filePath",
                file!!.absolutePath
            )
        )*/
    }

    fun setPdfView() {

    }

    fun g(){

    }
}