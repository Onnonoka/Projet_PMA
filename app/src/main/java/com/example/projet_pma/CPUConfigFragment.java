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


public class CPUConfigFragment extends Fragment {

    private static final String TAG = CPUConfigFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate onCreate
        View v = inflater.inflate(R.layout.fragment_cpu_config, container, false);

        // Set the array elements to the dropdown
        AutoCompleteTextView dropdown = v.findViewById(R.id.archAutoCompleteTextView);

        // Bind the element to an arrayAdapter and the dropdown
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.dropdown_item, Configuration.getInstance().get_APIResources().get_CPUFamilyList());
        dropdown.setAdapter(adapter);

        // Set default input text value
        TextInputLayout quantityInput = v.findViewById(R.id.quantityTextField);
        TextInputLayout coreUnitsInput = v.findViewById(R.id.coreUnitsTextField);
        TextInputLayout tdpInput = v.findViewById(R.id.tdpTextField);

        if (Configuration.getInstance().get_quantityCPU() != -1) {
            quantityInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_quantityCPU()));
        }
        if (Configuration.getInstance().get_coreUnits() != -1) {
            coreUnitsInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_coreUnits()));
        }
        if (Configuration.getInstance().get_tdp() != -1) {
            tdpInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_tdp()));
        }
        if (!Configuration.getInstance().get_architecture().equals("")) {
            dropdown.setText(Configuration.getInstance().get_architecture());
        }

        // Set the onChange listener
        quantityInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    Configuration.getInstance().set_quantityCPU(Integer.parseInt(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
        coreUnitsInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    Configuration.getInstance().set_coreUnits(Integer.parseInt(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
        tdpInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    Configuration.getInstance().set_tdp(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Configuration.getInstance().set_architecture(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        return v;
    }


}
