package com.example.pdfview

import android.R
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pdfview.databinding.ActivityMain2Binding
import java.io.File
import java.io.IOException


class MainActivity2 : AppCompatActivity() {

    var filePath = ""
    var pdfView: ImageView? = null
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        pdfView = binding.pdfview
        val filePath = intent.getStringExtra("filePath")
        val file = filePath?.let { File(it) }
        try {
            openPDF(file)
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

    }

    @Throws(IOException::class)
    fun openPDF(file: File?) {
        // File file = new File(filePath);
        var fileDescriptor: ParcelFileDescriptor? = null
        fileDescriptor = ParcelFileDescriptor.open(
            file, ParcelFileDescriptor.MODE_READ_ONLY
        )

        //min. API Level 21
        var pdfRenderer: PdfRenderer? = null
        pdfRenderer = PdfRenderer(fileDescriptor)
        val pageCount = pdfRenderer.pageCount
        Toast.makeText(
            this,
            "pageCount = $pageCount",
            Toast.LENGTH_LONG
        ).show()

        //Display page 0
        val rendererPage = pdfRenderer.openPage(0)
        val rendererPageWidth = rendererPage.width
        val rendererPageHeight = rendererPage.height
        val bitmap = Bitmap.createBitmap(
            rendererPageWidth,
            rendererPageHeight,
            Bitmap.Config.ARGB_8888
        )
        rendererPage.render(
            bitmap, null, null,
            PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
        )
        pdfView!!.setImageBitmap(bitmap)
        rendererPage.close()
        pdfRenderer.close()
        fileDescriptor.close()
    }
}