package com.example.vaadinproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "request")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "requestAcc")
@EqualsAndHashCode(of = "id")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_acc_id")
    private RequestAcc requestAcc;

}
