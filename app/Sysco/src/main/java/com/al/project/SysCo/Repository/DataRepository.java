package com.al.project.SysCo.Repository;

import com.al.project.SysCo.Model.DataTopic;
import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<DataTopic, Long> {
}
