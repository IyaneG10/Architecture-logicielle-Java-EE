package com.al.project.SysCo.Service;

public class DataService {

        private void  GetRealTimeData(RabbitMQService rabbitMQService, int rpiId, String request){

        rabbitMQService.GetRealTimeTopics("RpiTopics","User.RPi.Room."+Integer.toString(rpiId),request);
    }




    private void SendDataToUser(){

    }

    private void SendDataToDB(){

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
