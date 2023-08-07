package com.doc.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doc.dto.entity.MedicalRecord;

@Repository
public interface MedicalRecordDao extends JpaRepository<MedicalRecord, Integer>{

}
