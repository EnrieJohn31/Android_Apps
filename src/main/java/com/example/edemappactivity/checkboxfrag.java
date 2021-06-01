package com.example.edemappactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class checkboxfrag extends Fragment {

    CheckBox chk1, chk2;
    ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.checkboxdemo, container, false);

        chk1 = v.findViewById(R.id.checkBox);
        chk2 = v.findViewById(R.id.checkBox2);
        img = v.findViewById(R.id.resimage);

     //   Toast.makeText(getActivity(), "Please Pick from the two", Toast.LENGTH_SHORT).show();

        Toast.makeText(getActivity(), "Please Pick from the two", Toast.LENGTH_SHORT).show();

        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    img.setBackgroundResource(R.drawable.ice_male_24);
                    chk2.setChecked(false); // disable checkbox
                }
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    img.setBackgroundResource(R.drawable.ic_female_24);
                    chk1.setChecked(false); // disable checkbox
                }
            }
        });

        return v;
    }
}
