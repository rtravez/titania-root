package com.titania.msc.entity;

import com.titania.msc.entity.common.BaseEntity;
import com.titania.msc.entity.common.RoleCustomerEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "customers")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", unique = true, nullable = false)
    private Long customerId;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 20)
    private String username;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<RoleCustomerEntity> roleCustomers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<VehiculoEntity> vehicles;

}