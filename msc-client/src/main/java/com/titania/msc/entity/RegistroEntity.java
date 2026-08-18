package com.titania.msc.entity;

import com.titania.msc.entity.common.BaseEntity;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "registros")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_id", unique = true, nullable = false)
    private Long registroId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imei", referencedColumnName = "imei", nullable = false)
    private VehiculoEntity vehiculo;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "latitud", precision = 10, scale = 8)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 11, scale = 8)
    private BigDecimal longitud;

    @Column(name = "velocidad", precision = 6, scale = 2)
    private BigDecimal velocidad;

    @Column(name = "odometro", precision = 12, scale = 2)
    private BigDecimal odometro;

    @Column(name = "ignicion")
    private Boolean ignicion;
}
