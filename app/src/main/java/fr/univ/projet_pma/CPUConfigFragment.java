package fr.univ.projet_pma;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.lang.reflect.Array;
import java.util.ArrayList;


public class CPUConfigFragment extends Fragment {

    private static final String TAG = CPUConfigFragment.class.getName();
    private ArrayAdapter<String> _adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate onCreate
        View v = inflater.inflate(R.layout.fragment_cpu_config, container, false);
        _adapter = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Configuration.getInstance().get_APIResources().get_CPUFamilyList());

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Set the array elements to the dropdown
        AutoCompleteTextView dropdown = getView().findViewById(R.id.archAutoCompleteTextView);

        // Bind the element to an arrayAdapter and the dropdown
        dropdown.setAdapter(_adapter);
        if (!Configuration.getInstance().get_architecture().equals("")) {
            dropdown.setText(_adapter.getItem(_adapter.getPosition(Configuration.getInstance().get_architecture())), false);
        } else {
            Configuration.getInstance().set_architecture(_adapter.getItem(0));
            dropdown.setText(_adapter.getItem(0), false);
        }

        // Set default input text value
        TextInputLayout quantityInput = getView().findViewById(R.id.quantityTextField);
        TextInputLayout coreUnitsInput = getView().findViewById(R.id.coreUnitsTextField);
        TextInputLayout tdpInput = getView().findViewById(R.id.tdpTextField);

        if (Configuration.getInstance().get_quantityCPU() != -1) {
            quantityInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_quantityCPU()));
        }
        if (Configuration.getInstance().get_coreUnits() != -1) {
            coreUnitsInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_coreUnits()));
        }
        if (Configuration.getInstance().get_tdp() != -1) {
            tdpInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_tdp()));
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
        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Configuration.getInstance().set_architecture(parent.getItemAtPosition(position).toString());
            }
        });

    }
}
