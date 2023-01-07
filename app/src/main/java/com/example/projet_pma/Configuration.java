package com.example.projet_pma;


public class Configuration {

    private static final String TAG = "Configuration";

    // CPU configuration
    private int _quantityCPU = 0;
    private int _coreUnits = 0;
    private int _tdp = 0;
    private String _architecture = "";

    // RAM configuration
    private int _quantityRAM = 0;
    private int _capacityRAM = 0;
    private String _manufacturerRAM = "";

    // SSD configuration
    private int _quantitySSD = 0;
    private int _capacitySSD = 0;
    private String _manufacturerSSD = "";

    // Other configuration
    private int _quantityHDD = 0;
    private String _serverType = "";
    private int _quantityPSU = 0;

    // Configuration usage
    private String _localisation = "";
    private int _lifespan = 0;
    private String _methode = "";
    private int _avgConsumption = 0;

    public Configuration() {

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

    public String get_methode() {
        return _methode;
    }

    public void set_methode(String _methode) {
        this._methode = _methode;
    }

    public int get_avgConsumption() {
        return _avgConsumption;
    }

    public void set_avgConsumption(int _avgConsumption) {
        this._avgConsumption = _avgConsumption;
    }
}
