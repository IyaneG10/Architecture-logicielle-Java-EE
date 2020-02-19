package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Model.Product;
import com.al.project.SysCo.Repository.DataRepository;
import com.al.project.SysCo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import static com.al.project.SysCo.Service.RabbitMQService.GetRealTimeTopics;


@Service
public class DataService{

    @Autowired
    private  DataRepository dataRepository;



    public  Data saveData(Data data) {


        System.out.println(" entree saveData de DataService");
        Data ret = dataRepository.save(data);
        System.out.println(" sortie saveData de DataService");
        return ret;
    }

    private void getData( int rpiId, String topicName){

        //get data from database
    }

    private void  getRealTimeData( int rpiId, String request){

        GetRealTimeTopics(Integer.toString(rpiId),request);
    }

    private void sendDataToUser(){

        //Send real-time topic to user
    }

}
