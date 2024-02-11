package com.example.vaadinproject.service;

import com.example.vaadinproject.model.RequestAcc;
import com.example.vaadinproject.repo.RequestAccRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestAccService {

    private final RequestAccRepository requestAccRepository;

    public List<RequestAcc> findAll(){
        return requestAccRepository.findAll();
    }
}
