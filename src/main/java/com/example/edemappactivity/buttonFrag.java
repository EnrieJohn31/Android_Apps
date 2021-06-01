package com.example.edemappactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class buttonFrag extends Fragment {
    private EditText name;
    private Button sub;
    private TextView result;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.buttondemo,container,false);
        name = v.findViewById(R.id.names);
        sub = v.findViewById(R.id.submit);
        result = v.findViewById(R.id.results);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = name.getText().toString();

                if(ans.isEmpty()){
                    StyleableToast.makeText(getActivity(),"Insert Text in the Field!!",R.style.Toaster).show();
                    result.setText(null);
                }else {
                    result.setText(ans);
                }
            }
        });
        return v;
    }
}

