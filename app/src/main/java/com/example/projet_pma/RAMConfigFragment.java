package com.example.projet_pma;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

public class RAMConfigFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ram_config, container, false);

        AutoCompleteTextView dropdown = v.findViewById(R.id.manufacturerAutoCompleteTextView);

        String[] manufacturerItem = getResources().getStringArray(R.array.RAMManufacturer);
        List<String> manufacturerList = Arrays.asList(manufacturerItem);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.dropdown_item, manufacturerList);
        dropdown.setAdapter(adapter);

        TextInputLayout quantityInput = v.findViewById(R.id.quantityTextField);
        TextInputLayout capacityInput = v.findViewById(R.id.capacityTextField);

        if (Configuration.getInstance().get_quantityRAM() != -1) {
            quantityInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_quantityRAM()));
        }
        if (Configuration.getInstance().get_coreUnits() != -1) {
            capacityInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_capacityRAM()));
        }
        if (!Configuration.getInstance().get_manufacturerRAM().equals("")) {
            dropdown.setText(Configuration.getInstance().get_manufacturerRAM());
        }

        quantityInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    Configuration.getInstance().set_quantityRAM(Integer.parseInt(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
        capacityInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    Configuration.getInstance().set_capacityRAM(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Configuration.getInstance().set_manufacturerRAM(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        return v;
    }
}