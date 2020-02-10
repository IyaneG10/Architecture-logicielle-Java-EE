package com.al.project.SysCo.Repository;

import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<Data, Long> {
}
