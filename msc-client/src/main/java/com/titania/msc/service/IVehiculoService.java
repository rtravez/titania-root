package com.titania.msc.service;

import java.util.List;
import java.util.Optional;

import com.titania.msc.dto.response.VehicleResponse;
import com.titania.msc.entity.VehiculoEntity;
import com.titania.msc.exception.ExceptionManager;

/**
 * <b> Interfaz de servicio para VehiculoEntity. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
public interface IVehiculoService extends IGenericService<VehiculoEntity, Long> {

    /**
     * Busca un vehículo por su IMEI
     *
     * @param imei IMEI del vehículo
     * @return Optional con VehiculoEntity
     * @throws ExceptionManager
     */
    Optional<VehicleResponse> findByImei(String imei) throws ExceptionManager;

    List<VehicleResponse> findVehicleAll() throws ExceptionManager;
}
