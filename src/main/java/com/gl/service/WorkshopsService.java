package com.gl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.entity.Workshops;
import com.gl.repository.WorkshopsRepository;

@Service
public class WorkshopsService {

    @Autowired
    private WorkshopsRepository workshopsRepository;

    public List<Workshops> getAllWorkshops() {
        return workshopsRepository.findAll();
    }

    public Workshops getWorkshopById(Integer id) {
        return workshopsRepository.findById(id).orElse(null);
    }

    public List<Workshops> getWorkshopsByUser(Integer user) {
        return workshopsRepository.findByUser(user);
    }
}