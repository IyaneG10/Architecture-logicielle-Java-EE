package com.al.project.sysco.Model;


import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Entity
@Table(name = "data") // si le nom de la table n'est pas précisée, celui de la classe sera pris par défau
public class Data {


    public Integer getRpiId() {
        return rpiId;
    }

    public boolean isState() {
        return state;
    }

    public String getMeasureName() {
        return measureName;
    }

    public double getMeasureValue() {
        return measureValue;
    }

    public String getDate() {
        return date;
    }

    public void setRpiId(Integer rpiId) {
        this.rpiId = rpiId;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

    public void setMeasureValue(double measureValue) {
        this.measureValue = measureValue;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rpiId;
    private boolean state;
    private String measureName;
    private double measureValue;
    private String date;

    public Data(){

    }

    public Data( String dataString)throws JSONException, ParseException {

        JSONObject jsonObject = new JSONObject(dataString);

        this.setDate(recurseKeys(jsonObject,"Date"));
        this.setRpiId(Integer.parseInt(recurseKeys(jsonObject,"Id")));
        this.setState(Boolean.parseBoolean(recurseKeys(jsonObject,"State")));
        this.setMeasureName(recurseKeys(jsonObject,"Name"));
        this.setMeasureValue(Double.parseDouble(recurseKeys(jsonObject,"Value")));
    }

    public static String recurseKeys(JSONObject jObj, String findKey) throws JSONException {
        String finalValue = "";
        if (jObj == null) {
            return "";
        }

        Iterator<String> keyItr = jObj.keys();
        Map<String, String> map = new HashMap<>();

        while(keyItr.hasNext()) {
            String key = keyItr.next();
            map.put(key, jObj.getString(key));
        }

        for (Map.Entry<String, String> e : (map).entrySet()) {
            String key = e.getKey();
            if (key.equalsIgnoreCase(findKey)) {
                return jObj.getString(key);
            }

            // read value
            Object value = jObj.get(key);

            if (value instanceof JSONObject) {
                finalValue = recurseKeys((JSONObject)value, findKey);
            }
        }

        return finalValue;
    }


    public String dataToJsonstring() {
        try {
            String st1 = Integer.toString(this.getRpiId());
            String st2 = this.getMeasureName();
            String st3 = Double.toString(this.getMeasureValue());
            String st4 = Boolean.toString(this.isState());
            String st5 = this.getDate();

            return "{" +
                "\"Rpi\":" +
                "{" +
                "\"Id\":"+ st1+"," +
                "\"Measure\":" +
                "{" +
                "\"Name\":\""+ st2+"\"," +
                "\"Value\":"+ st3 +
                "}," +
                "\"State\":"+ st4+ "," +
                "\"Date\":\""+st5 +
                "\"}" +
                "}";
        }
        catch (Exception e){
            return null;
        }
    }
}