package com.al.project.SysCo;

import com.al.project.SysCo.Service.Publisher;
import com.al.project.SysCo.Service.RabbitMQService;
import com.al.project.SysCo.Service.Subscriber;

import java.util.Objects;

public class DataApplication {

    public static void main(String[] args) {
        RabbitMQService rabbitMQService = new RabbitMQService();
        rabbitMQService.SubscribeToTopics();

        //if(demande données)
            //GetRealTimeData()
    }


    private void  GetRealTimeData(RabbitMQService rabbitMQService, int rpiId, String request){

        rabbitMQService.GetRealTimeTopics("RpiTopics","User.RPi.Room."+Integer.toString(rpiId),request);
    }

    private void GetDataBaseData(){

    }

    private void SendDataToUser(){

    }

    private void SendDataToDB(){

    }
}

