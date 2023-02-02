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
import java.util.Set;

public class UsageConfigFragment extends Fragment {

    private static final String TAG = UsageConfigFragment.class.getName();
    private ArrayAdapter<String> _adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usage_config, container, false);
        _adapter = new ArrayAdapter(getContext(), R.layout.dropdown_item, Configuration.getInstance()
                .get_APIResources().get_countryFullList());

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        AutoCompleteTextView dropdownLocalisation = getView().findViewById(R.id.localisationAutoCompleteTextView);
        dropdownLocalisation.setAdapter(_adapter);
        if (!Configuration.getInstance().get_localisation().equals("")) {
            dropdownLocalisation.setText(_adapter.getItem(_adapter.getPosition(Configuration.getInstance().get_localisation())), false);
        } else {
            Configuration.getInstance().set_localisation(_adapter.getItem(0));
            dropdownLocalisation.setText(_adapter.getItem(0), false);
        }

        TextInputLayout lifespanInput = getView().findViewById(R.id.lifespanTextField);
        TextInputLayout avgConsInput = getView().findViewById(R.id.avgConsTextField);

        lifespanInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_lifespan()));
        avgConsInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_avgConsumption()));

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
    }
}
