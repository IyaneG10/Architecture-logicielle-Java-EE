package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;

public class DataService {

    public String toString(Data data) {
        try {
            return String.format("<RPi><Id>%s</Id><Topic><Name>%s</Name><Value>%s</Value></Topic><State>%s</State><Date>%s</Date></RPi>",
                    data.getRpiId(),
                    data.getTopicName(),
                    data.getTopicValue(),
                    data.isState(),
                    data.getDate());
        }
        catch (Exception e){
            return null;
        }
    }

    public void sendTopicToDataBase(String request){                                                            // Send response to the publish we subscribed

        System.out.println(" [x] Received Request: " + request);
    }


    public void sendTopicToUser(String request){                                                            // Send response to the publish we subscribed

        System.out.println(" [x] Received Request: " + request);
    }

    public void getDataFromDataBase(){

    }
}
