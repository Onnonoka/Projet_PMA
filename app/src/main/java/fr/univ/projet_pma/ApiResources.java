package fr.univ.projet_pma;


import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApiResources {

    private static final String TAG = ApiResources.class.getName();

    private enum RequestStatus {
        PENDING, COMPLETE, ERROR
    }

    private Context _ctx;

    // api resources
    private List<String> _CPUFamilyList;
    private List<String> _RAMManufacturerList;
    private List<String> _SSDManufacturerList;
    private List<String> _caseTypeList;
    private Map<String, String> _countryCodeList;

    private RequestStatus _dataStatus = RequestStatus.PENDING;

    private RequestStatus _cpuFamily = RequestStatus.PENDING;
    private RequestStatus _ramManufacturer = RequestStatus.PENDING;
    private RequestStatus _ssdManufacturer = RequestStatus.PENDING;
    private RequestStatus _case_type = RequestStatus.PENDING;
    private RequestStatus _countryCode = RequestStatus.PENDING;

    public ApiResources(Context ctx) {
        _ctx = ctx;
    }

    public void update() {

        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeAsync(new GETResourcesConnexionTask("/utils/cpu_family"), new TaskRunner.Callback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                Collections.sort(result);
                set_CPUFamilyList(result);
                _cpuFamily = RequestStatus.COMPLETE;
                if (taskCompleted())
                    _dataStatus = RequestStatus.COMPLETE;
            }
        });
        taskRunner.executeAsync(new GETResourcesConnexionTask("/utils/ram_manufacturer"), new TaskRunner.Callback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                Collections.sort(result);
                set_RAMManufacturerList(result);
                _ramManufacturer = RequestStatus.COMPLETE;
                if (taskCompleted())
                    _dataStatus = RequestStatus.COMPLETE;
            }
        });
        taskRunner.executeAsync(new GETResourcesConnexionTask("/utils/ssd_manufacturer"), new TaskRunner.Callback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                Collections.sort(result);
                set_SSDManufacturerList(result);
                _ssdManufacturer = RequestStatus.COMPLETE;
                if (taskCompleted())
                    _dataStatus = RequestStatus.COMPLETE;
            }
        });
        taskRunner.executeAsync(new GETResourcesConnexionTask("/utils/case_type"), new TaskRunner.Callback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                Collections.sort(result);
                set_caseTypeList(result);
                _case_type = RequestStatus.COMPLETE;
                if (taskCompleted())
                    _dataStatus = RequestStatus.COMPLETE;
            }
        });
        taskRunner.executeAsync(new GETCountryConnexionTask(), new TaskRunner.Callback<Map<String, String>>() {
            @Override
            public void onComplete(Map<String, String> result) {
                set_countryCodeList(result);
                _countryCode = RequestStatus.COMPLETE;
                if (taskCompleted())
                    _dataStatus = RequestStatus.COMPLETE;
            }
        });
    }

    private boolean taskCompleted() {
        return _cpuFamily == RequestStatus.COMPLETE && _ramManufacturer == RequestStatus.COMPLETE &&
                _ssdManufacturer == RequestStatus.COMPLETE && _case_type == RequestStatus.COMPLETE &&
                _countryCode == RequestStatus.COMPLETE;
    }

    public boolean isInitComplete() {
        return _dataStatus == RequestStatus.COMPLETE;
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

    public List<String> get_countryFullList() {
        Set countryCodeSet = _countryCodeList.keySet();
        String[] countryCodeList = (String[]) countryCodeSet.toArray(new String[countryCodeSet.size()]);
        Arrays.sort(countryCodeList);
        return Arrays.asList(countryCodeList);
    }

    public List<String> get_countryCodeList() {
        ArrayList<String> countryFullList = new ArrayList<>(_countryCodeList.values());
        Collections.sort(countryFullList);
        return countryFullList;
    }

    public Map<String, String> get_countryMap() {
        return _countryCodeList;
    }

    public void set_countryCodeList(Map<String, String> _countryCodeList) {
        this._countryCodeList = _countryCodeList;
    }
}
