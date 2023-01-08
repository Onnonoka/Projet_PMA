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

public class UsageConfigFragment extends Fragment {

    private static final String TAG = UsageConfigFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usage_config, container, false);

        AutoCompleteTextView dropdownLocalisation = v.findViewById(R.id.localisationAutoCompleteTextView);
        AutoCompleteTextView dropdownMethod = v.findViewById(R.id.methodAutoCompleteTextView);

        ArrayAdapter localisationAdapter = new ArrayAdapter(getContext(), R.layout.dropdown_item, Configuration.getInstance()
                .get_APIResources().get_countryCodeList().keySet().toArray());
        ArrayAdapter methodAdapter = new ArrayAdapter(getContext(), R.layout.dropdown_item, Configuration.getInstance()
                .get_APIResources().get_methodList());

        dropdownLocalisation.setAdapter(localisationAdapter);
        dropdownMethod.setAdapter(methodAdapter);

        TextInputLayout lifespanInput = v.findViewById(R.id.lifespanTextField);
        TextInputLayout avgConsInput = v.findViewById(R.id.avgConsTextField);

        if (!Configuration.getInstance().get_localisation().equals("")) {
            dropdownLocalisation.setText(Configuration.getInstance().get_serverType());
        }
        if (Configuration.getInstance().get_lifespan() != -1) {
            lifespanInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_lifespan()));
        }
        if (!Configuration.getInstance().get_methode().equals("")) {
            dropdownLocalisation.setText(Configuration.getInstance().get_methode());
        }
        if (Configuration.getInstance().get_avgConsumption() != -1) {
            avgConsInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_avgConsumption()));
        }


        lifespanInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    Configuration.getInstance().set_lifespan(Integer.parseInt(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
        dropdownLocalisation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Configuration.getInstance().set_localisation(parent.getItemAtPosition(position).toString());
            }
        });
        avgConsInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    Configuration.getInstance().set_avgConsumption(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });

        dropdownMethod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Configuration.getInstance().set_methode(parent.getItemAtPosition(position).toString());
            }
        });

        return v;
    }
}
