package com.titania.msc.repository;

import com.titania.msc.entity.RegistroEntity;
import com.titania.msc.exception.ExceptionManager;

import java.util.Date;
import java.util.List;

/**
 * <b> Interfaz de repositorio para RegistroEntity. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
public interface IRegistroRepository extends IGenericRepository<RegistroEntity, Long> {

    /**
     * Obtiene los registros asociados a un IMEI
     *
     * @param imei IMEI del vehículo
     * @return Lista de RegistroEntity
     * @throws ExceptionManager
     */
    List<RegistroEntity> findByVehiculoImei(String imei) throws ExceptionManager;

    /**
     * Busca los registros dentro de un rango de fechas (opcionalmente filtrado por IMEI)
     *
     * @param startDate Fecha de inicio
     * @param endDate Fecha de fin
     * @param imei IMEI opcional del vehículo
     * @return Lista de RegistroEntity
     * @throws ExceptionManager
     */
    List<RegistroEntity> findByFechaBetween(Date startDate, Date endDate, String imei) throws ExceptionManager;
}

