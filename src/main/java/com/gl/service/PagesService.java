package com.gl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.entity.Pages;
import com.gl.repository.PagesRepository;

@Service
public class PagesService {

    @Autowired
    private PagesRepository pagesRepository;

    public Pages getPagesByConference(Integer conferenceId) {
        return pagesRepository.findByUser(conferenceId);
    }

    public Pages getPagesById(Integer id) {
        return pagesRepository.findById(id).orElse(null);
    }

    public Pages savePages(Pages pages) {
        return pagesRepository.save(pages);
    }
}