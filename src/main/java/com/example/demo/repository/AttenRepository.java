package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Attendence;

public interface AttenRepository extends JpaRepository<Attendence,Integer> {

}
