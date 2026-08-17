package com.sofka.msc.service;

import com.sofka.msc.entity.RegistroEntity;
import com.sofka.msc.exception.ExceptionManager;

import java.util.Date;
import java.util.List;

/**
 * <b> Interfaz de servicio para RegistroEntity. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
public interface IRegistroService extends IGenericService<RegistroEntity, Long> {

    /**
     * Busca la lista de registros asociados a un IMEI de vehículo
     *
     * @param imei IMEI del vehículo
     * @return Lista de RegistroEntity
     * @throws ExceptionManager
     */
    List<RegistroEntity> findByVehiculoImei(String imei) throws ExceptionManager;

    /**
     * Busca la lista de registros dentro de un rango de fechas (opcionalmente filtrado por IMEI)
     *
     * @param startDate Fecha inicio
     * @param endDate Fecha fin
     * @param imei (Opcional) IMEI del vehículo
     * @return Lista de RegistroEntity
     * @throws ExceptionManager
     */
    List<RegistroEntity> findByFechaBetween(Date startDate, Date endDate, String imei) throws ExceptionManager;
}

