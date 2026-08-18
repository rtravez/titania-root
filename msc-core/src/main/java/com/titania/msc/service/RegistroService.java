package com.titania.msc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.titania.msc.dto.response.RegistroResponse;
import com.titania.msc.dto.response.VehicleResponse;
import com.titania.msc.entity.RegistroEntity;
import com.titania.msc.exception.ExceptionManager;
import com.titania.msc.repository.IRegistroRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * <b> Servicio para la gestión de registros. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
@Service
@Slf4j
public class RegistroService extends GenericService<RegistroEntity, Long, IRegistroRepository>
        implements IRegistroService {

    public RegistroService(IRegistroRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistroResponse> findByVehiculoImei(String imei) throws ExceptionManager {
        try {
            List<RegistroResponse> registroResponses = new ArrayList<>();
            repository.findByVehiculoImei(imei)
                    .forEach(it -> registroResponses.add(RegistroResponse.builder()
                            .vehiculo(VehicleResponse.builder()
                                    .placa(it.getVehiculo().getPlaca())
                                    .chasis(it.getVehiculo().getChasis())
                                    .imei(it.getVehiculo().getImei())
                                    .dealer(it.getVehiculo().getDealer())
                                    .agencia(it.getVehiculo().getAgencia())
                                    .marca(it.getVehiculo().getMarca())
                                    .modelo(it.getVehiculo().getModelo())
                                    .plataforma(it.getVehiculo().getPlataforma())
                                    .build())
                            .fecha(it.getFecha())
                            .latitud(it.getLatitud())
                            .longitud(it.getLongitud())
                            .velocidad(it.getVelocidad())
                            .odometro(it.getOdometro())
                            .ignicion(it.getIgnicion())
                            .build()));
            return registroResponses;
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
    public List<RegistroResponse> findByFechaBetween(Date startDate, Date endDate, String imei)
            throws ExceptionManager {
        try {
            List<RegistroResponse> registroResponses = new ArrayList<>();
            repository.findByFechaBetween(startDate, endDate, imei)
                    .forEach(it -> registroResponses.add(RegistroResponse.builder()
                            .vehiculo(VehicleResponse.builder()
                                    .placa(it.getVehiculo().getPlaca())
                                    .chasis(it.getVehiculo().getChasis())
                                    .imei(it.getVehiculo().getImei())
                                    .dealer(it.getVehiculo().getDealer())
                                    .agencia(it.getVehiculo().getAgencia())
                                    .marca(it.getVehiculo().getMarca())
                                    .modelo(it.getVehiculo().getModelo())
                                    .plataforma(it.getVehiculo().getPlataforma())
                                    .build())
                            .fecha(it.getFecha())
                            .latitud(it.getLatitud())
                            .longitud(it.getLongitud())
                            .velocidad(it.getVelocidad())
                            .odometro(it.getOdometro())
                            .ignicion(it.getIgnicion())
                            .build()));
            return registroResponses;
        } catch (ExceptionManager e) {
            log.error("findByFechaBetween: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("findByFechaBetween: ", e);
            throw new ExceptionManager.FindingException("Error al buscar los registros por rango de fecha");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistroResponse> findRegisterAll() throws ExceptionManager {
        try {
            List<RegistroResponse> registroResponses = new ArrayList<>();
            List<RegistroEntity> registros = repository.findAll();
            registros.forEach(it -> registroResponses.add(RegistroResponse.builder()
                    .vehiculo(VehicleResponse.builder()
                            .placa(it.getVehiculo().getPlaca())
                            .chasis(it.getVehiculo().getChasis())
                            .imei(it.getVehiculo().getImei())
                            .dealer(it.getVehiculo().getDealer())
                            .agencia(it.getVehiculo().getAgencia())
                            .marca(it.getVehiculo().getMarca())
                            .modelo(it.getVehiculo().getModelo())
                            .plataforma(it.getVehiculo().getPlataforma())
                            .build())
                    .fecha(it.getFecha())
                    .latitud(it.getLatitud())
                    .longitud(it.getLongitud())
                    .velocidad(it.getVelocidad())
                    .odometro(it.getOdometro())
                    .ignicion(it.getIgnicion())
                    .build()));
            return registroResponses;
        } catch (ExceptionManager e) {
            log.error("findRegisterAll: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("findRegisterAll: ", e);
            throw new ExceptionManager.FindingException("Error al buscar todos los registros");
        }
    }
}
