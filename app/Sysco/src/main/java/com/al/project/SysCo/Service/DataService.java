package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import static com.al.project.SysCo.RabbitMQService.GetRealTimeTopics;


@Service
@Configurable
public class DataService {

    @Autowired
    private  DataRepository dataRepository;


    public  Data saveData(Data data) {


        System.out.println(" entree saveData de DataService");
        Data ret = dataRepository.save(data);
        System.out.println(" sortie saveData de DataService");
        return ret;
    }

    public void sendDataToUser(){

        //Send real-time topic to user
    }

}
