package com.hero.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hero.dto.entity.Supe;
import com.hero.dto.entity.compositeobjects.SightingSupeLocation;

public interface SupeDao {
	
	Supe createSupe(Supe supe);
	Supe getSupeById(int supeId);
	int updateSupe(Supe supe);
	boolean deleteSupe(int supeId);
	List<Supe> getAllSupes();
	List<String> getSuperheroesAtLocation(String locationName);
//    Function already in Organisation:
//	List<Supe> getSupesByOrganisation(int organisationId);

}
