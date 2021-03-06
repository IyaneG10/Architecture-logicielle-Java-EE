package com.al.project.SysCo.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DataTopic {


    public int getRpiId() {
        return rpiId;
    }

    public boolean isState() {
        return state;
    }

    public String getTopicName() {
        return topicName;
    }

    public double getTopicValue() {
        return topicValue;
    }

    public String getDate() {
        return date;
    }

    public void setRpiId(int rpiId) {
        this.rpiId = rpiId;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTopicValue(double topicValue) {
        this.topicValue = topicValue;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private int rpiId;
    private boolean state;
    private String topicName;
    private double topicValue;
    private String date;


    public DataTopic jsonstringToData(String dataString) throws JSONException, ParseException {

        DataTopic newDataObj = new DataTopic();

        JSONObject jsonObject = new JSONObject(dataString);

        //newDataObj.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(jsonObject.getString("Date")));
        //String str = recurseKeys(jsonObject,"Date");
        //Date currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(str);

        newDataObj.setDate(recurseKeys(jsonObject,"Date"));
        newDataObj.setRpiId(Integer.parseInt(recurseKeys(jsonObject,"Id")));
        newDataObj.setState(Boolean.parseBoolean(recurseKeys(jsonObject,"State")));
        newDataObj.setTopicName(recurseKeys(jsonObject,"Name"));
        newDataObj.setTopicValue(Double.parseDouble(recurseKeys(jsonObject,"Value")));

        return newDataObj;
        /*
        */
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

        // key is not found
        return finalValue;
    }

    public String dataToJsonstring() {
        try {
            String st1 = Integer.toString(this.getRpiId());
            String st2 = this.getTopicName().toString();
            String st3 = Double.toString(this.getTopicValue());
            String st4 = Boolean.toString(this.isState());
            String st5 = this.getDate().toString();


            return "{" +
                                "\"Rpi\":" +
                                    "{" +
                                        "\"Id\":"+ st1+"," +
                                        "\"Topic\":" +
                                            "{" +
                                                "\"Name\":\""+ st2+"\"," +
                                                "\"Value\":"+ st3 +
                                            "}," +
                                        "\"State\":"+ st4+ "," +
                                        "\"Date\":\""+st5 +
                                    "\"}" +
                                "}";
                   /* this.getRpiId(),
                    this.getTopicName(),
                    this.getTopicValue(),
                    this.isState(),
                    this.getDate());*/
        }
        catch (Exception e){
            return null;
        }
    }
}
