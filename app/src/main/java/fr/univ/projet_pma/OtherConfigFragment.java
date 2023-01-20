package fr.univ.projet_pma;


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

public class OtherConfigFragment extends Fragment {

    private static final String TAG = OtherConfigFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_other_config, container, false);

        AutoCompleteTextView dropdown = v.findViewById(R.id.serverTypeAutoCompleteTextView);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.dropdown_item, Configuration.getInstance().get_APIResources().get_caseTypeList());
        dropdown.setAdapter(adapter);

        TextInputLayout hddQuantityInput = v.findViewById(R.id.hddQuantityTextField);
        TextInputLayout psuQuantityInput = v.findViewById(R.id.psuQuantityTextField);

        if (Configuration.getInstance().get_quantityHDD() != -1) {
            hddQuantityInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_quantityHDD()));
        }
        if (!Configuration.getInstance().get_serverType().equals("")) {
            dropdown.setText(Configuration.getInstance().get_serverType());
        }
        if (Configuration.getInstance().get_quantityPSU() != -1) {
            psuQuantityInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_quantityPSU()));
        }

        hddQuantityInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    Configuration.getInstance().set_quantityHDD(Integer.parseInt(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Configuration.getInstance().set_serverType(parent.getItemAtPosition(position).toString());
            }
        });
        psuQuantityInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    Configuration.getInstance().set_quantityPSU(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });

        return v;
    }
}
