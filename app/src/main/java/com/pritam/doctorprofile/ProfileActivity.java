package com.pritam.doctorprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import static com.pritam.doctorprofile.ListActivity.EXTRA_HOSPITAL;
import static com.pritam.doctorprofile.ListActivity.EXTRA_NAME;
import static com.pritam.doctorprofile.ListActivity.EXTRA_RATING;
import static com.pritam.doctorprofile.ListActivity.EXTRA_URL;
import static com.pritam.doctorprofile.ListActivity.EXTRA_VISIT;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String image = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        String hospital = intent.getStringExtra(EXTRA_HOSPITAL);
        Double rating = intent.getDoubleExtra(EXTRA_RATING, 0.0);
        Double visit = intent.getDoubleExtra(EXTRA_VISIT, 0.0);

        ImageView imageView = findViewById(R.id.doctorImage);
        TextView nameView = findViewById(R.id.doctorName);
        TextView hospitalView = findViewById(R.id.doctorHospital);
        TextView ratingView = findViewById(R.id.doctorRating);
        TextView visitView = findViewById(R.id.doctorVisit);
        Picasso.get().load(image).fit().centerInside().into(imageView);
        nameView.setText(name);
        hospitalView.setText(hospital);
        ratingView.setText(rating.toString());
        visitView.setText(visit.toString());

    }
}
