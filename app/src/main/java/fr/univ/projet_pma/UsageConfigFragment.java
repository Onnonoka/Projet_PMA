package fr.univ.projet_pma;


import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
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
    private ArrayAdapter<String> _adapterLocation;
    private ArrayAdapter<String> _adapterMethode;
    private View _loadAdvancedViewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usage_config, container, false);
        _adapterLocation = new ArrayAdapter(getContext(), R.layout.dropdown_item, Configuration.getInstance()
                .get_APIResources().get_countryFullList());

        _adapterMethode = new ArrayAdapter(getContext(), R.layout.dropdown_item, Configuration.getInstance()
                .get_APIResources().get_methodeList());
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        AutoCompleteTextView dropdownLocalisation = getView().findViewById(R.id.localisationAutoCompleteTextView);
        dropdownLocalisation.setAdapter(_adapterLocation);
        if (!Configuration.getInstance().get_localisation().equals("")) {
            dropdownLocalisation.setText(_adapterLocation.getItem(_adapterLocation.getPosition(Configuration.getInstance().get_localisation())), false);
        } else {
            Configuration.getInstance().set_localisation(_adapterLocation.getItem(0));
            dropdownLocalisation.setText(_adapterLocation.getItem(0), false);
        }

        AutoCompleteTextView dropdownMethode = getView().findViewById(R.id.methodeAutoCompleteTextView);
        dropdownMethode.setAdapter(_adapterMethode);
        if (!Configuration.getInstance().get_methode().equals("")) {
            dropdownMethode.setText(_adapterMethode.getItem(_adapterMethode.getPosition(Configuration.getInstance().get_methode())), false);
        } else {
            Configuration.getInstance().set_methode(_adapterMethode.getItem(0));
            dropdownMethode.setText(_adapterMethode.getItem(0), false);
        }

        _loadAdvancedViewGroup = getView().findViewById(R.id.loadAdvenceConfig);

        TextInputLayout lifespanInput = getView().findViewById(R.id.lifespanTextField);
        TextInputLayout avgConsInput = getView().findViewById(R.id.avgConsTextField);
        TextInputLayout time1 = getView().findViewById(R.id.Time1);
        TextInputLayout time2 = getView().findViewById(R.id.Time2);
        TextInputLayout time3 = getView().findViewById(R.id.Time3);
        TextInputLayout load1 = getView().findViewById(R.id.Load1);
        TextInputLayout load2 = getView().findViewById(R.id.Load2);
        TextInputLayout load3 = getView().findViewById(R.id.Load3);


        lifespanInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_lifespan()));
        avgConsInput.getEditText().setText(String.valueOf(Configuration.getInstance().get_avgConsumption()));
        time1.getEditText().setText(String.valueOf(Configuration.getInstance().get_1Time()));
        time2.getEditText().setText(String.valueOf(Configuration.getInstance().get_2Time()));
        time3.getEditText().setText(String.valueOf(Configuration.getInstance().get_3Time()));
        load1.getEditText().setText(String.valueOf(Configuration.getInstance().get_1Load()));
        load2.getEditText().setText(String.valueOf(Configuration.getInstance().get_2Load()));
        load3.getEditText().setText(String.valueOf(Configuration.getInstance().get_3Load()));

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
        dropdownMethode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Configuration.getInstance().set_methode(parent.getItemAtPosition(position).toString());
                if (parent.getItemAtPosition(position).toString().equals(getResources().getStringArray(R.array.method)[2])) {
                    setLoadAdvancedConfigVisibility(View.VISIBLE);
                } else {
                    setLoadAdvancedConfigVisibility(View.GONE);
                }
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
        time1.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Configuration.getInstance().set_1Time(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        time2.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Configuration.getInstance().set_2Time(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        time3.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Configuration.getInstance().set_3Time(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        time1.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Configuration.getInstance().set_1Time(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        load1.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Configuration.getInstance().set_1Load(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        load2.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Configuration.getInstance().set_2Load(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        load3.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Configuration.getInstance().set_3Load(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setLoadAdvancedConfigVisibility(int visibility) {
        _loadAdvancedViewGroup.setVisibility(visibility);
    }
}
