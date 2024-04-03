package com.example.vaadinproject.service;

import com.example.vaadinproject.model.RequestAcc;
import com.example.vaadinproject.repo.RequestAccRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestAccService {

    private final RequestAccRepository requestAccRepository;
    private final EntityManager entityManager;

    public List<RequestAcc> findAll() {
        return entityManager.createQuery("select re FROM RequestAcc re", RequestAcc.class).getResultList();
    }

    public void create(RequestAcc requestAcc) {
        requestAccRepository.save(requestAcc);
    }

    @Transactional
    public void deleteBatch(Long... ids) {
        int i = entityManager.createQuery("DELETE FROM RequestAcc req WHERE req.id IN (:ids)")
                .setParameter("ids", Arrays.asList(ids))
                .executeUpdate();
        entityManager.flush();
    }
}
