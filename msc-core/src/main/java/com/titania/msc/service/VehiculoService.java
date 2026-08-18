package com.titania.msc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.titania.msc.dto.response.VehicleResponse;
import com.titania.msc.entity.VehiculoEntity;
import com.titania.msc.exception.ExceptionManager;
import com.titania.msc.repository.IVehiculoRepository;

import lombok.extern.slf4j.Slf4j;

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
    public Optional<VehicleResponse> findByImei(String imei) throws ExceptionManager {
        try {
            return repository.findByImei(imei).map(it -> VehicleResponse.builder()
                    .placa(it.getPlaca())
                    .chasis(it.getChasis())
                    .imei(it.getImei())
                    .dealer(it.getDealer())
                    .agencia(it.getAgencia())
                    .marca(it.getMarca())
                    .modelo(it.getModelo())
                    .plataforma(it.getPlataforma())
                    .build());
        } catch (ExceptionManager e) {
            log.error("findByImei: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("findByImei: ", e);
            throw new ExceptionManager.FindingException("Error al buscar el vehículo por IMEI");
        }
    }

    @Override
    public List<VehicleResponse> findVehicleAll() throws ExceptionManager {
        try {
            List<VehicleResponse> vehicleResponses = new ArrayList<>();
            List<VehiculoEntity> vehicles = repository.findAll();
            vehicles.forEach(it -> vehicleResponses.add(VehicleResponse.builder()
                    .placa(it.getPlaca())
                    .chasis(it.getChasis())
                    .imei(it.getImei())
                    .dealer(it.getDealer())
                    .agencia(it.getAgencia())
                    .marca(it.getMarca())
                    .modelo(it.getModelo())
                    .plataforma(it.getPlataforma())
                    .build()));
            return vehicleResponses;
        } catch (Exception e) {
            log.error("findVehicleAll: ", e);
            throw new ExceptionManager.FindingException("Error al buscar los registros");
        }
    }
}
