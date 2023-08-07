package com.hero.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hero.dto.entity.Supe;
import com.hero.model.dao.SupeDao;

@Service
public class SupeServiceImpl implements SupeService {

    @Autowired
    private SupeDao supeDao;

    @Override
    public Supe createSupe(Supe supe) {
        return supeDao.createSupe(supe);
    }

    @Override
    public Supe getSupeById(int supeId) {
        return supeDao.getSupeById(supeId);
    }

    @Override
    public Supe updateSupe(int supeId, String newSupeName) {
        Supe existingSupe = supeDao.getSupeById(supeId);
        if (existingSupe != null) {
            existingSupe.setSupeName(newSupeName);
            int affectedRows = supeDao.updateSupe(existingSupe);
            if (affectedRows > 0) {
                return existingSupe;
            }
        }
        return null;
    }


    @Override
    public boolean deleteSupe(int supeId) {
        return supeDao.deleteSupe(supeId);
    }

    @Override
    public List<Supe> getAllSupes() {
        return supeDao.getAllSupes();
    }
    
    @Override
    public List<String> getSuperheroesAtLocation(String locationName) {
        return supeDao.getSuperheroesAtLocation(locationName);
    }
    
}
