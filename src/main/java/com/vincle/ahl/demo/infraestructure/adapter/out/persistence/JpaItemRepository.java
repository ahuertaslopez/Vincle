package com.vincle.ahl.demo.infraestructure.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vincle.ahl.demo.application.port.out.ItemRepository;

@Repository
public interface JpaItemRepository extends JpaRepository<ItemEntity, Long>, ItemRepository {

}
