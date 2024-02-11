package com.example.vaadinproject.repo;

import com.example.vaadinproject.model.RequestAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestAccRepository extends JpaRepository<RequestAcc, Long> {

    List<RequestAcc> findAll();
    List<RequestAcc> findAll2();



    @Query("SELECT acc FROM RequestAcc acc WHERE (:firstName is null or acc.firstName = :firstName) " +
           "OR (:lastName is null or acc.lastName = :lastName)")
    List<RequestAcc> findRequestAccByFilter(@Param("firstName") String firstName,
                                            @Param("lastName") String lastName);
}
