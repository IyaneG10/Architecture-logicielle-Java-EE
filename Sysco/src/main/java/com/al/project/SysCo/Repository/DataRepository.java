package com.al.project.SysCo.Repository;

import com.al.project.SysCo.Model.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {

    Data save(Data data) ;
    //public  Data saveData(Data data);

}
