package com.sofka.msc.service;

import com.sofka.msc.entity.RegistroEntity;
import com.sofka.msc.exception.ExceptionManager;
import com.sofka.msc.repository.IRegistroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <b> Servicio para la gestión de registros. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
@Service
@Slf4j
public class RegistroService extends GenericService<RegistroEntity, Long, IRegistroRepository> implements IRegistroService {

    public RegistroService(IRegistroRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistroEntity> findByVehiculoImei(String imei) throws ExceptionManager {
        try {
            return repository.findByVehiculoImei(imei);
        } catch (ExceptionManager e) {
            log.error("findByVehiculoImei: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("findByVehiculoImei: ", e);
            throw new ExceptionManager.FindingException("Error al buscar los registros por IMEI de vehículo");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistroEntity> findByFechaBetween(Date startDate, Date endDate, String imei) throws ExceptionManager {
        try {
            return repository.findByFechaBetween(startDate, endDate, imei);
        } catch (ExceptionManager e) {
            log.error("findByFechaBetween: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("findByFechaBetween: ", e);
            throw new ExceptionManager.FindingException("Error al buscar los registros por rango de fecha");
        }
    }
}

