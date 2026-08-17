package com.sofka.msc.entity;

import com.sofka.msc.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "vehiculos")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehiculo_id", unique = true, nullable = false)
    private Long vehiculoId;

    @Column(name = "placa", nullable = false, length = 20)
    private String placa;

    @Column(name = "chasis", length = 50)
    private String chasis;

    @Column(name = "imei", unique = true, nullable = false, length = 50)
    private String imei;

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

    /*@OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<RegistroEntity> registros;*/
}
