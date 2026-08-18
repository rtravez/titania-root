package com.titania.msc.dto.response;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistroResponse {

    private VehicleResponse vehiculo;

    private Date fecha;

    private BigDecimal latitud;

    private BigDecimal longitud;

    private BigDecimal velocidad;

    private BigDecimal odometro;

    private Boolean ignicion;
}
