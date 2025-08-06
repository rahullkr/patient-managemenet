package com.pm.patient_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.patient_management.dto.PatientRequestDTO;
import com.pm.patient_management.dto.PatientResponseDTO;
import com.pm.patient_management.mapper.PatientMapper;
import com.pm.patient_management.model.Patient;
import com.pm.patient_management.repository.PatientRepository;
// service layer convert domain entity model into a response dto
@Service
public class PatientService {
    private PatientRepository patientRepository; 
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients (){
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTO = patients.stream().map(PatientMapper::toDTO).toList();
        return patientResponseDTO;

        //another way to return the patientResponseDTO is 
        //return patients.stream().map(PaitentMapper::toDTO).toList();
        
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        Patient newPatient  = 
        patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDTO(newPatient);
    }
}
