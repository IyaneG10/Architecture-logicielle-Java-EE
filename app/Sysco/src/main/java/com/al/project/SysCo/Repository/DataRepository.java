package com.al.project.SysCo.Repository;

import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Model.DataTopic;
import com.al.project.SysCo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

public interface DataRepository extends JpaRepository<Data, Long> {

    //public void save(DataTopic data) ;

}
