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

import java.util.Arrays;
import java.util.List;

public class RAMConfigFragment extends Fragment {

    private static final String TAG = RAMConfigFragment.class.getName();
    private ArrayAdapter<String> _adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ram_config, container, false);
        _adapter = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Configuration.getInstance().get_APIResources().get_RAMManufacturerList());
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        AutoCompleteTextView dropdown = getView().findViewById(R.id.manufacturerAutoCompleteTextView);

        dropdown.setAdapter(_adapter);
        if (!Configuration.getInstance().get_manufacturerRAM().equals("")) {
            dropdown.setText(_adapter.getItem(_adapter.getPosition(Configuration.getInstance().get_manufacturerRAM())), false);
        } else {
            Configuration.getInstance().set_manufacturerRAM(_adapter.getItem(0));
            dropdown.setText(_adapter.getItem(0), false);
        }

        TextInputLayout quantityInput = getView().findViewById(R.id.quantityTextField);
        TextInputLayout capacityInput = getView().findViewById(R.id.capacityTextField);

        quantityInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_quantityRAM()));
        capacityInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_capacityRAM()));

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
        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Configuration.getInstance().set_manufacturerRAM(parent.getItemAtPosition(position).toString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
    }
}
