package com.nhnacademy.edu.springframework.project.service;

import org.springframework.stereotype.Service;

@Service("dataLoadService")
public interface DataLoadService {
    void loadAndMerge();
}
