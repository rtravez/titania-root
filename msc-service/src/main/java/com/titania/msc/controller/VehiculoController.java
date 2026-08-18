package com.titania.msc.controller;

import com.titania.msc.dto.BaseResponseDto;
import com.titania.msc.dto.response.VehicleResponse;
import com.titania.msc.entity.VehiculoEntity;
import com.titania.msc.service.IVehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * VehiculoController
 *
 * @author renetravez
 * @version $1.0$
 */
@RestController
@RequestMapping("/api/vehiculos")
@Validated
@Slf4j
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    /**
     * Find all vehicles
     *
     * @return List of VehiculoEntity wrapped in BaseResponseDto
     */
    @GetMapping
    @Operation(summary = "Find all vehicles")
    public ResponseEntity<BaseResponseDto<Object>> findVehicleAll() {
        List<VehicleResponse> vehiculos = vehiculoService.findVehicleAll();
        if (vehiculos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseDto.builder().code(HttpStatus.OK.value()).message("No existen vehículos").build());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                        .data(vehiculos).message("Vehículos encontrados con éxito").build());
    }   

    /**
     * Find vehicle by IMEI
     *
     * @param imei Vehicle IMEI
     * @return VehiculoEntity wrapped in BaseResponseDto
     */
    @GetMapping(path = "/imei/{imei}")
    @Operation(summary = "Find vehicle by IMEI")
    public ResponseEntity<BaseResponseDto<Object>> findByImei(@PathVariable String imei) {
        Optional<VehicleResponse> vehiculo = vehiculoService.findByImei(imei);
        if (vehiculo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseDto.builder().code(HttpStatus.OK.value()).message("El vehículo con el IMEI especificado no existe").build());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                        .data(vehiculo.get()).message("Vehículo encontrado con éxito").build());
    }

    /**
     * Save vehicle
     *
     * @param vehiculo Vehicle entity
     * @return Created VehiculoEntity wrapped in BaseResponseDto
     */
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    @Operation(summary = "Create vehicle")
    public ResponseEntity<BaseResponseDto<Object>> save(@Valid @RequestBody VehiculoEntity vehiculo) {
        if (vehiculo.getImei() != null && vehiculoService.findByImei(vehiculo.getImei()).isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseDto.builder().code(HttpStatus.OK.value()).message("El vehículo con ese IMEI ya existe").build());
        }

        VehiculoEntity savedVehiculo = vehiculoService.save(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponseDto.builder().code(HttpStatus.CREATED.value())
                        .data(savedVehiculo).message("Vehículo creado con éxito").build());
    }

    /**
     * Update vehicle
     *
     * @param vehiculo Vehicle entity
     * @return Updated VehiculoEntity wrapped in BaseResponseDto
     */
    @Secured({"ROLE_ADMIN"})
    @PutMapping
    @Operation(summary = "Update vehicle")
    public ResponseEntity<BaseResponseDto<Object>> update(@Valid @RequestBody VehiculoEntity vehiculo) {
        if (vehiculo.getImei() == null || vehiculoService.findByImei(vehiculo.getImei()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseDto.builder().code(HttpStatus.OK.value()).message("El vehículo no existe").build());
        }

        VehiculoEntity updatedVehiculo = vehiculoService.update(vehiculo);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                        .data(updatedVehiculo).message("Vehículo actualizado con éxito").build());
    }

    /**
     * Delete vehicle by id
     *
     * @param id Vehicle ID
     * @return BaseResponseDto
     */
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete vehicle")
    public ResponseEntity<BaseResponseDto<Object>> deleteById(@PathVariable Long id) {
        Optional<VehiculoEntity> vehiculo = vehiculoService.findById(id);
        if (vehiculo.isPresent()) {
            vehiculoService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseDto.builder().code(HttpStatus.OK.value()).message("Vehículo eliminado con éxito").build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseDto.builder().code(HttpStatus.OK.value()).message("El vehículo no existe").build());
        }
    }
}
