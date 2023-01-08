package com.example.projet_pma;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ApiResources {

    private static final String TAG = ApiResources.class.getName();

    private Context _ctx;

    // api resources
    private List<String> _CPUFamilyList;
    private List<String> _RAMManufacturerList;
    private List<String> _SSDManufacturerList;
    private List<String> _caseTypeList;
    private List<String> _methodList;
    private Map<String, String> _countryCodeList;

    public ApiResources(Context ctx) {
        _ctx = ctx;

        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeAsync(new GetResourcesTask("/cpu_family"), new TaskRunner.Callback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                set_CPUFamilyList(result);
            }
        });
        taskRunner.executeAsync(new GetResourcesTask("/ram_manufacturer"), new TaskRunner.Callback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                set_RAMManufacturerList(result);
            }
        });
        taskRunner.executeAsync(new GetResourcesTask("/ssd_manufacturer"), new TaskRunner.Callback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                set_SSDManufacturerList(result);
            }
        });
        taskRunner.executeAsync(new GetResourcesTask("/case_type"), new TaskRunner.Callback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                set_caseTypeList(result);
            }
        });
        String[] methodItem = _ctx.getResources().getStringArray(R.array.method);
        set_methodList(Arrays.asList(methodItem));
        taskRunner.executeAsync(new GetCountryTask(), new TaskRunner.Callback<Map<String, String>>() {
            @Override
            public void onComplete(Map<String, String> result) {
                set_countryCodeList(result);
            }
        });
    }

    public List<String> get_CPUFamilyList() {
        return _CPUFamilyList;
    }

    public void set_CPUFamilyList(List<String> _CPUFamilyList) {
        this._CPUFamilyList = _CPUFamilyList;
    }

    public List<String> get_RAMManufacturerList() {
        return _RAMManufacturerList;
    }

    public void set_RAMManufacturerList(List<String> _RAMManufacturerList) {
        this._RAMManufacturerList = _RAMManufacturerList;
    }

    public List<String> get_SSDManufacturerList() {
        return _SSDManufacturerList;
    }

    public void set_SSDManufacturerList(List<String> _SSDManufacturerList) {
        this._SSDManufacturerList = _SSDManufacturerList;
    }

    public List<String> get_caseTypeList() {
        return _caseTypeList;
    }

    public void set_caseTypeList(List<String> _caseTypeList) {
        this._caseTypeList = _caseTypeList;
    }

    public List<String> get_methodList() {
        return _methodList;
    }

    public void set_methodList(List<String> _methodList) {
        this._methodList = _methodList;
    }

    public Map<String, String> get_countryCodeList() {
        return _countryCodeList;
    }

    public void set_countryCodeList(Map<String, String> _countryCodeList) {
        this._countryCodeList = _countryCodeList;
    }
}
