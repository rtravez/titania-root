package com.titania.msc.repository;

import com.titania.msc.entity.VehiculoEntity;
import com.titania.msc.exception.ExceptionManager;

import java.util.Optional;

/**
 * <b> Interfaz de repositorio para VehiculoEntity. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
public interface IVehiculoRepository extends IGenericRepository<VehiculoEntity, Long> {

    /**
     * Busca un vehículo por su IMEI
     *
     * @param imei IMEI del vehículo
     * @return Optional con VehiculoEntity
     * @throws ExceptionManager
     */
    Optional<VehiculoEntity> findByImei(String imei) throws ExceptionManager;
}
