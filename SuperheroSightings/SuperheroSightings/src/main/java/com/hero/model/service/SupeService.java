package com.hero.model.service;

import java.util.List;

import com.hero.dto.entity.Supe;

public interface SupeService {
	
	Supe createSupe(Supe supe);
    Supe getSupeById(int supeId);
    Supe updateSupe(int supeId, String newSupeName);
    boolean deleteSupe(int supeId);
    List<Supe> getAllSupes();
    List<String> getSuperheroesAtLocation(String locationName);

}
