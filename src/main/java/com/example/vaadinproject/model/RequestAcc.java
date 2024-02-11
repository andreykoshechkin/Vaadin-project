package com.example.vaadinproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "request_acc", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "requestList")
@EqualsAndHashCode(of = "id")
public class RequestAcc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long inn;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "data_create")
    private LocalDate date;

    @Builder.Default
    @OneToMany(mappedBy = "requestAcc", fetch = FetchType.EAGER)
    private List<Request> requestList = new ArrayList<>();
}
