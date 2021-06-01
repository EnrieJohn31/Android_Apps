package com.example.edemappactivity;

import android.bluetooth.BluetoothAdapter;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;

public class togglefrag extends Fragment {

    ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.togglebtndemo, container, false);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();mBluetoothAdapter.disable();

        Switch Switches = (Switch) v.findViewById(R.id.Switch);

        img = v.findViewById(R.id.imageView);

        Switches.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // If the switch button is on
                    mBluetoothAdapter.enable();
                    Toast.makeText(getActivity(),"Bluetooth Turning ON",Toast.LENGTH_LONG).show();
                    img.setBackgroundResource(R.drawable.ic_bluetooth_24);
                }
                else {
                    // If the switch button is off
                    mBluetoothAdapter.disable();
                    Toast.makeText(getActivity(),"Bluetooth Turning OFF",Toast.LENGTH_LONG).show();
                    img.setBackgroundResource(R.drawable.ic_bluetooth_disabled_24);
                }
            }
        });

        return v;
    }
}
