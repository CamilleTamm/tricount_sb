package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Transfert;

public interface TransfertRepository extends JpaRepository<Transfert, Long> {

}
