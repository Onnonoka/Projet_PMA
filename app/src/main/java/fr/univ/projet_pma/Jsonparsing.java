package fr.univ.projet_pma;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Jsonparsing {

    private JSONObject FullData = new JSONObject();
    private String Usage;
    private String Manufacturing ;
    private String ManufacturingRAM;
    private String ManufacturingCPU;
    private String ManufacturingSSD;
    private String ManufacturingHDD;
    private String ManufacturingOther;

    public Jsonparsing(JSONObject file){
        super();
        FullData = file;
    }

    private String ConcatenationD (String s1, String s2){
        float result;
        if (s2.equals("-1") || s1.equals("-1")){
            return ("0.0");
        } else {
            result = Float.valueOf(s1) * Float.valueOf(s2);
        }
        return (Float.toString(result));
    }

    private double ConcatenationDouble (double d1, double d2){
        if (d1 < 0 || d2 < 0){
            return(0.0);
        } else {
            return (d1 * d2);
        }
    }

    public void RetrieveValueJson(String t) throws JSONException {

        JSONObject impact = FullData.getJSONObject("impacts");
        JSONObject type = impact.getJSONObject(t);
        Usage = type.getString("use");
        Manufacturing = type.getString("manufacture");

        JSONObject verbose = FullData.getJSONObject("verbose");
        JSONObject CPU = verbose.getJSONObject("CPU-1");
        JSONObject Manufacture_impact = CPU.getJSONObject("manufacture_impacts");
        type = Manufacture_impact.getJSONObject(t);
        ManufacturingCPU = ConcatenationD(CPU.getString("units"), type.getString("value"));

        JSONObject RAM = verbose.getJSONObject("RAM-1");
        Manufacture_impact = RAM.getJSONObject("manufacture_impacts");
        type = Manufacture_impact.getJSONObject(t);
        ManufacturingRAM = ConcatenationD(RAM.getString("units"), type.getString("value"));

        JSONObject SSD = verbose.getJSONObject("SSD-1");
        Manufacture_impact = SSD.getJSONObject("manufacture_impacts");
        type = Manufacture_impact.getJSONObject(t);
        ManufacturingSSD = ConcatenationD(SSD.getString("units"), type.getString("value"));

        if (verbose.has("HDD-1")){
            JSONObject HDD = verbose.getJSONObject("HDD-1");
            Manufacture_impact = HDD.getJSONObject("manufacture_impacts");
            type = Manufacture_impact.getJSONObject(t);
            ManufacturingHDD = ConcatenationD(HDD.getString("units"), type.getString("value"));
        } else {
            ManufacturingHDD = "0.0";
        }

        double tamp;
        JSONObject CASE = verbose.getJSONObject("CASE-1");
        JSONObject Manufacture_impact_CASE = CASE.getJSONObject("manufacture_impacts");
        JSONObject type_CASE = Manufacture_impact_CASE.getJSONObject(t);
        tamp = ConcatenationDouble(CASE.getDouble("units"), type_CASE.getDouble("value"));

        JSONObject MOTHERBOARD = verbose.getJSONObject("MOTHERBOARD-1");
        JSONObject Manufacture_impact_MOTHERBOARD = MOTHERBOARD.getJSONObject("manufacture_impacts");
        JSONObject type_MOTHERBOARD = Manufacture_impact_MOTHERBOARD.getJSONObject(t);
        tamp += ConcatenationDouble(MOTHERBOARD.getDouble("units"), type_MOTHERBOARD.getDouble("value"));

        JSONObject POWER = verbose.getJSONObject("POWER_SUPPLY-1");
        JSONObject Manufacture_impact_POWER = POWER.getJSONObject("manufacture_impacts");
        JSONObject type_POWER = Manufacture_impact_POWER.getJSONObject(t);
        tamp +=  ConcatenationDouble(POWER.getDouble("units"), type_POWER.getDouble("value"));

        JSONObject ASSEMBLY = verbose.getJSONObject("ASSEMBLY-1");
        JSONObject Manufacture_impact_ASSEMBLY = ASSEMBLY.getJSONObject("manufacture_impacts");
        JSONObject type_ASSEMBLY = Manufacture_impact_ASSEMBLY.getJSONObject(t);
        tamp += ConcatenationDouble(ASSEMBLY.getDouble("units"), type_ASSEMBLY.getDouble("value"));

        ManufacturingOther = Double.toString(tamp);
    }

    public String getUsage() {
        return Usage;
    }
    public String getManufacturing() {
        return Manufacturing;
    }
    public String getManufacturingCPU() {
        return ManufacturingCPU;
    }
    public String getManufacturingRAM() {
        return ManufacturingRAM;
    }
    public String getManufacturingSSD() {
        return ManufacturingSSD;
    }
    public String getManufacturingHDD() {
        return ManufacturingHDD;
    }
    public String getManufacturingOther() {
        return ManufacturingOther;
    }
}
