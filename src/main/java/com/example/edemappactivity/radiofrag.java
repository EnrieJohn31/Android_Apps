package com.example.edemappactivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.logging.Logger;

public class radiofrag extends Fragment {

    RadioButton r1,r2,r3;
    ImageView ind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.radiobuttondemo,container,false);

        r1 = v.findViewById(R.id.radioButton);
      //  r2 = v.findViewById(R.id.radioButton2);
        r3 = v.findViewById(R.id.radioButton3);
        ind = v.findViewById(R.id.indicator);

        AudioManager am;
       // am= (AudioManager) getActivity().getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Normal mode
                    am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    ind.setBackgroundResource(R.drawable.ic_ring_volume_24);
                    StyleableToast.makeText(getActivity(),"Normal Mode",R.style.Toaster).show();
                }
            }
        });
      /*  r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(r2.isChecked()){
                    // Silent mode
                    try{
                        am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        ind.setBackgroundResource(R.drawable.ic_silence_24);
                        StyleableToast.makeText(getActivity(),"Ringer Mode",R.style.Toaster).show();
                    }catch (Exception e){
                        Logger.getAnonymousLogger();
                    }
                }
            }
        });*/
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(r3.isChecked()){
                    // Vibrate mode
                    am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    ind.setBackgroundResource(R.drawable.ic_vibration_24);
                    StyleableToast.makeText(getActivity(),"Vibrate Mode",R.style.Toaster).show();
                }
            }
        });
        return v;
    }

}
