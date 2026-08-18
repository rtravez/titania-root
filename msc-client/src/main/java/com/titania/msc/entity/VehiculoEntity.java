package com.titania.msc.entity;

import com.titania.msc.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "vehiculos")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoEntity extends BaseEntity {

    @Id
    @Column(name = "imei", unique = true, nullable = false, length = 20)
    private String imei;

    @Column(name = "placa", nullable = false, length = 20)
    private String placa;

    @Column(name = "chasis", length = 50)
    private String chasis;

    @Column(name = "dealer", length = 100)
    private String dealer;

    @Column(name = "agencia", length = 100)
    private String agencia;

    @Column(name = "marca", length = 50)
    private String marca;

    @Column(name = "modelo", length = 50)
    private String modelo;

    @Column(name = "plataforma", length = 50)
    private String plataforma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;
}
