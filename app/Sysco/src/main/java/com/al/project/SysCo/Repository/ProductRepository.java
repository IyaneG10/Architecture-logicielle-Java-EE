package com.al.project.SysCo.Repository;

import com.al.project.SysCo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
