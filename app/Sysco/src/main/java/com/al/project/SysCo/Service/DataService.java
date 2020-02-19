package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Model.DataTopic;
import com.al.project.SysCo.Model.Product;
import com.al.project.SysCo.Repository.DataRepository;
import com.al.project.SysCo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DataService {




    private  DataRepository dataRepository;


    @Autowired
    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Data save(Data data) {
        return dataRepository.save(data);
    }


    ///////////////////////////////////////////////



        private void  GetRealTimeData(RabbitMQService rabbitMQService, int rpiId, String request){

        rabbitMQService.GetRealTimeTopics("RpiTopics","User.RPi.Room."+Integer.toString(rpiId),request);
    }




    private void SendDataToUser(){

    }

    private void SendDataToDB(){

    }


    public void sendTopicToDataBase(DataTopic data){                                                            // Send response to the publish we subscribed

        //
    }


    public void sendTopicToUser(String request){                                                            // Send response to the publish we subscribed

        System.out.println(" [x] Received Request: " + request);
    }

    public void getDataFromDataBase(){

    }


}
