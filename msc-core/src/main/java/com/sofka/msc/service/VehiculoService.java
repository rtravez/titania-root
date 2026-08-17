package com.sofka.msc.service;

import com.sofka.msc.entity.VehiculoEntity;
import com.sofka.msc.exception.ExceptionManager;
import com.sofka.msc.repository.IVehiculoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <b> Servicio para la gestión de vehículos. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
@Service
@Slf4j
public class VehiculoService extends GenericService<VehiculoEntity, Long, IVehiculoRepository> implements IVehiculoService {

    public VehiculoService(IVehiculoRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VehiculoEntity> findByImei(String imei) throws ExceptionManager {
        try {
            return repository.findByImei(imei);
        } catch (ExceptionManager e) {
            log.error("findByImei: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("findByImei: ", e);
            throw new ExceptionManager.FindingException("Error al buscar el vehículo por IMEI");
        }
    }
}
