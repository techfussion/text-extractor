package com.example.textextractor

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.android.SdkConstants.Intent
import com.example.textextractor.databinding.ActivityMainBinding // view binding
import org.gradle.internal.impldep.org.eclipse.jgit.lib.BitmapIndex.Bitmap

class MainActivity : ComponentActivity() {
    lateinit var result: EditText // Stores text result
    private lateinit var binding: ActivityMainBinding // view binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater) // view binding
//        setContentView(R.layout.activity_main)
        setContentView(binding.root) // view binding
        supportAcionBar?.hide()

        val camera = binding.imageView2
        val erase = binding.imageView3
        val copy = binding.imageView4

        result = binding.textView3
        camera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(intent.resolveActivity(packageManager) != null){
                // resolve image
                startActivityForResult(intent, 123)
            } else {
                Toast.makeText(this, "Oops something went wrong", Toast.LENGTH_SHORT)show()
            }
        }

        erase.setOnClickListener {
            result.setText("")
        }

        copy.setOnClickListener {
            val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", result.text.toString())
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to ClipBoard", Toast.LENGTH_SHORT)show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 123 && resultCode == RESULT_OK){
            val extras = data?.extras
            val bitmap = extras.get("data") as Bitmap

            detectTextUsingML(bitmap)
        }
    }

    private fun detectTextUsingML(bitmap: Bitmap){
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val image = InputImage.fromBitmap(bitmap, 0)

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                // ...
                result.setText(visionText.text.toString())
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
                Toast.makeText(this, "Oops something went wrong", Toast.LENGTH_SHORT)show()
            }
    }
}












//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.textextractor.ui.theme.TextExtractorTheme


//        setContent {
//            TextExtractorTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Vene",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TextExtractorTheme {
//        Greeting("Android")
//    }
//}