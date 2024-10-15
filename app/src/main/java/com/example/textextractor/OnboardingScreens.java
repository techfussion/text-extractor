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

        addFragment(new Step.Builder().setTitle("Search Your Location")
                .setContent("You can search any place nearby or with-in the specified city. We will display specific or all related shops to match your search.")
                .setBackgroundColor(Color.parseColor("#00B0FF")) // int background color
                .setDrawable(R.drawable.undraw_convert) // int top drawable
//                .setSummary("This is summary")
                .build());

        addFragment(new Step.Builder().setTitle("Make A Call")
                .setContent("We provide almost all the numbers of all departments and shops registered with us. You can perform bookings as well.")
                .setBackgroundColor(Color.parseColor("#00B0FF")) // int background color
                .setDrawable(R.drawable.undraw_photocopy) // int top drawable
//                .setSummary("This is summary")
                .build());

        addFragment(new Step.Builder().setTitle("Add Missing Place")
                .setContent("If you have a shop or somethings and want to be a part of our growing industry then add your place by following simple steps.")
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