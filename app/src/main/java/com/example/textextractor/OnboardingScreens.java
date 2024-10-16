package com.example.textextractor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class OnboardingScreens extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        addFragment(new Step.Builder().setTitle("Take a Picture")
                .setContent("Simply take a picture of the text you would like to extract, it could be a note, a snippet, or text.")
                .setBackgroundColor(Color.parseColor("#00B0FF")) // int background color
                .setDrawable(R.drawable.undraw_convert) // int top drawable
//                .setSummary("This is summary")
                .build());

        addFragment(new Step.Builder().setTitle("ML Scan")
                .setContent("Our ML Model would scan through your picture and extract the text within it.")
                .setBackgroundColor(Color.parseColor("#00B0FF")) // int background color
                .setDrawable(R.drawable.undraw_photocopy) // int top drawable
//                .setSummary("This is summary")
                .build());

        addFragment(new Step.Builder().setTitle("Copy to ClipBoard")
                .setContent("We have provided a quick and easily accessible copy icon to help you copy the text to your clipboard.")
                .setBackgroundColor(Color.parseColor("#00B0FF")) // int background color
                .setDrawable(R.drawable.undraw_convert_re_l0y1) // int top drawable
//                .setSummary("This is summary")
                .build());
    }

    @Override
    public void finishTutorial() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}