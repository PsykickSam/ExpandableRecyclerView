package com.mdsadman.project.expandablerv;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mdsadman.project.expandablerv.databinding.ActivityExpnadableRecyclerViewBinding;
import com.mdsadman.project.expandablerv.test.Test;

public class ExpandableRecyclerViewActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityExpnadableRecyclerViewBinding activity = ActivityExpnadableRecyclerViewBinding
                .inflate(LayoutInflater.from(this), null, false);
        setContentView(activity.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Test.usual(this, activity.rvParent);
    }
}