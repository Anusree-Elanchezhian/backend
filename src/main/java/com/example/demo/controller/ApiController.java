package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Attendence;
import com.example.demo.repository.AttenRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")

public class ApiController {

	@Autowired
	private AttenRepository AttendenceRepository;
	
	// get all records
	@GetMapping("/employees")
	public List<Attendence> getAllRecords(){
		return AttendenceRepository.findAll();
	}		
	
	// create record
	@PostMapping("/employees")
	public Attendence createRecord(@RequestBody Attendence record) {
		return AttendenceRepository.save(record);
	}
	
	// get record by id 
	@GetMapping("/employees/{id}")
	public ResponseEntity<Attendence> getRecordById(@PathVariable int id) {
		Attendence record = AttendenceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));
		return ResponseEntity.ok(record);
	}
	
	// update record
	@PutMapping("/employees/{id}")
	public ResponseEntity<Attendence> updateRecord(@PathVariable int id, @RequestBody Attendence medicalDetails){
		Attendence record = AttendenceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));
		
		record.setName(medicalDetails.getName());
		record.setEmailId(medicalDetails.getEmailId());
		record.setGender(medicalDetails.getGender());
		record.setAge(medicalDetails.getAge());
		
		Attendence updatedRecord = AttendenceRepository.save(record);
		return ResponseEntity.ok(updatedRecord);
	}
	
	// delete record
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteRecord(@PathVariable int id){
		Attendence record =AttendenceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));
		
		AttendenceRepository.delete(record);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}