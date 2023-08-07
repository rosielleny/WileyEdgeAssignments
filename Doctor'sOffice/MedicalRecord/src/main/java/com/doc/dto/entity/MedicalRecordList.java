package com.doc.dto.entity;

import java.util.List;

import com.doc.dto.entity.MedicalRecord;
import com.doc.dto.entity.MedicalRecordList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordList {

	List<MedicalRecord> medicalRecord;
}
