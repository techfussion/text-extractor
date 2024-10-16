package com.example.textextractor

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.google.android.material.imageview.ShapeableImageView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : ComponentActivity() {
    lateinit var result: TextView // EditText // Stores text result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        actionBar?.hide()

        val camera = findViewById<ShapeableImageView>(R.id.imageView2)
        val erase = findViewById<ShapeableImageView>(R.id.imageView3)
        val copy = findViewById<ShapeableImageView>(R.id.imageView4)


        result = findViewById<TextView>(R.id.textView3)
        camera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(intent.resolveActivity(packageManager) != null){
                // resolve image
                startActivityForResult(intent, 123)
            } else {
                Toast.makeText(this, "Oops something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        erase.setOnClickListener {
            result.text = ""
        }

        copy.setOnClickListener {
            val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", result.text.toString())
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to ClipBoard", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 123 && resultCode == RESULT_OK){
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap

            detectTextUsingML(bitmap)
        } else {
            Toast.makeText(this, "Oops fail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun detectTextUsingML(bitmap: Bitmap){
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val image = InputImage.fromBitmap(bitmap, 0)

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                // ...
                result.text = visionText.text.toString()
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
                Toast.makeText(this, "Oops something went wrong", Toast.LENGTH_SHORT).show()
            }
    }
}
