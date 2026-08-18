package com.titania.msc.dto.request;

import java.math.BigDecimal;
import java.util.Date;

import com.titania.msc.dto.response.VehicleResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistroRequest {

    private VehicleResponse vehiculo;

    private Date fecha;

    private BigDecimal latitud;

    private BigDecimal longitud;

    private BigDecimal velocidad;

    private BigDecimal odometro;

    private Boolean ignicion;
}
