package com.titania.msc.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VehicleRequest {

    private String placa;

    private String chasis;

    private String imei;

    private String dealer;

    private String agencia;

    private String marca;

    private String modelo;

    private String plataforma;

}
