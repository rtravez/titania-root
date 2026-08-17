package com.sofka.msc.service;

import com.sofka.msc.entity.VehiculoEntity;
import com.sofka.msc.exception.ExceptionManager;

import java.util.Optional;

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
    Optional<VehiculoEntity> findByImei(String imei) throws ExceptionManager;
}
