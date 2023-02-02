package fr.univ.projet_pma;



import android.app.Application;

import java.util.List;
import java.util.Map;

public class Configuration extends Application {

    private static final String TAG = Configuration.class.getName();
    private static Configuration _self;

    private ApiResources _APIResources;

    // CPU configuration
    private int _quantityCPU = 2;
    private int _coreUnits = 16;
    private int _tdp = 150;
    private String _architecture = "";

    // RAM configuration
    private int _quantityRAM = 4;
    private int _capacityRAM = 32;
    private String _manufacturerRAM = "";

    // SSD configuration
    private int _quantitySSD = 4;
    private int _capacitySSD = 1000;
    private String _manufacturerSSD = "";

    // Other configuration
    private int _quantityHDD = 2;
    private String _serverType = "";
    private int _quantityPSU = 2;

    // Configuration usage
    private String _localisation = "";
    private int _lifespan = 4;
    private int _avgConsumption = 150;

    @Override
    public final void onCreate() {
        super.onCreate();
        _self = this;
        _APIResources = new ApiResources(this);
    }

    public static Configuration getInstance() {
        return _self;
    }

    public ApiResources get_APIResources() {
        return _APIResources;
    }

    public int get_quantityCPU() {
        return _quantityCPU;
    }

    public void set_quantityCPU(int _quantityCPU) {
        this._quantityCPU = _quantityCPU;
    }

    public int get_coreUnits() {
        return _coreUnits;
    }

    public void set_coreUnits(int _coreUnits) {
        this._coreUnits = _coreUnits;
    }

    public int get_tdp() {
        return _tdp;
    }

    public void set_tdp(int _tdp) {
        this._tdp = _tdp;
    }

    public String get_architecture() {
        return _architecture;
    }

    public void set_architecture(String _architecture) {
        this._architecture = _architecture;
    }

    public int get_quantityRAM() {
        return _quantityRAM;
    }

    public void set_quantityRAM(int _quantityRAM) {
        this._quantityRAM = _quantityRAM;
    }

    public int get_capacityRAM() {
        return _capacityRAM;
    }

    public void set_capacityRAM(int _capacityRAM) {
        this._capacityRAM = _capacityRAM;
    }

    public String get_manufacturerRAM() {
        return _manufacturerRAM;
    }

    public void set_manufacturerRAM(String _manufacturerRAM) {
        this._manufacturerRAM = _manufacturerRAM;
    }

    public int get_quantitySSD() {
        return _quantitySSD;
    }

    public void set_quantitySSD(int _quantitySSD) {
        this._quantitySSD = _quantitySSD;
    }

    public int get_capacitySSD() {
        return _capacitySSD;
    }

    public void set_capacitySSD(int _capacitySSD) {
        this._capacitySSD = _capacitySSD;
    }

    public String get_manufacturerSSD() {
        return _manufacturerSSD;
    }

    public void set_manufacturerSSD(String _manufacturerSSD) {
        this._manufacturerSSD = _manufacturerSSD;
    }

    public int get_quantityHDD() {
        return _quantityHDD;
    }

    public void set_quantityHDD(int _quantityHDD) {
        this._quantityHDD = _quantityHDD;
    }

    public String get_serverType() {
        return _serverType;
    }

    public void set_serverType(String _serverType) {
        this._serverType = _serverType;
    }

    public int get_quantityPSU() {
        return _quantityPSU;
    }

    public void set_quantityPSU(int _quantityPSU) {
        this._quantityPSU = _quantityPSU;
    }

    public String get_localisation() {
        return _localisation;
    }

    public void set_localisation(String _localisation) {
        this._localisation = _localisation;
    }

    public int get_lifespan() {
        return _lifespan;
    }

    public void set_lifespan(int _lifespan) {
        this._lifespan = _lifespan;
    }

    public int get_avgConsumption() {
        return _avgConsumption;
    }

    public void set_avgConsumption(int _avgConsumption) {
        this._avgConsumption = _avgConsumption;
    }
}
