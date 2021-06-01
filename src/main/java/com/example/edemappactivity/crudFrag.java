package com.example.edemappactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class crudFrag extends Fragment {

    ListView listViewNames;
    EditText name;
    Button addButton, updateButton , clearButton;
    ArrayList<String> nameList = new ArrayList<String>();
    ArrayAdapter ListAdapter;
    Integer indexValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.crud_demo, container, false);

        listViewNames = v.findViewById(R.id.listNames);
        addButton = v.findViewById(R.id.add);
        updateButton = v.findViewById(R.id.update);
        clearButton = v.findViewById(R.id.clear);
        name = v.findViewById(R.id.inputname);

        ListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameList);
        listViewNames.setAdapter(ListAdapter);

        clearButton.setEnabled(false);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = name.getText().toString();
                if(value.isEmpty()){
                    Toast.makeText(getActivity(),"Please Enter Text!!!",Toast.LENGTH_SHORT).show();
                }else{
                    nameList.add(value);
                    ListAdapter.notifyDataSetChanged();
                    name.setText("");
                }
            }
        });

        listViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addButton.setEnabled(false);
                clearButton.setEnabled(false);
                name.setText(listViewNames.getItemAtPosition(position).toString());
                indexValue = position;
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = name.getText().toString();
                try{
                    if(value.isEmpty()){
                        Toast.makeText(getActivity(),"Please Enter Text!!!",Toast.LENGTH_SHORT).show();
                    } else {
                        nameList.set(indexValue, value);
                        ListAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(),"Record Updated",Toast.LENGTH_SHORT).show();
                        addButton.setEnabled(true);
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(),"Update not Required",Toast.LENGTH_SHORT).show();
                }
            }
        });

        listViewNames.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder((Context) getActivity())
                        .setIcon(R.drawable.delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this item?")
                        //yes button
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name.setText(null);
                                addButton.setEnabled(true);
                                nameList.remove(position);
                                ListAdapter.notifyDataSetChanged();
                            }
                        })
                        //no button
                        .setNegativeButton("NO", null)
                        .show();
                return true;
            }
        });

        ListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (listViewNames.getAdapter().isEmpty()){
                    clearButton.setEnabled(false);
                }
                else{
                    clearButton.setEnabled(true);
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder((Context) getActivity())
                        .setIcon(R.drawable.delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to Clear List?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name.setText("");
                                ListAdapter.clear();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
            }
        });
        return v;
    }
}
