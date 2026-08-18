package com.titania.msc.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.titania.msc.dto.BaseResponseDto;
import com.titania.msc.dto.response.RegistroResponse;
import com.titania.msc.entity.RegistroEntity;
import com.titania.msc.service.IRegistroService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * RegistroController
 *
 * @author renetravez
 * @version $1.0$
 */
@RestController
@RequestMapping("/api/registros")
@Validated
@Slf4j
public class RegistroController {

        @Autowired
        private IRegistroService registroService;

        /**
         * Find all registros
         *
         * @return List of RegistroEntity wrapped in BaseResponseDto
         */
        @GetMapping
        @Operation(summary = "Find all registros")
        public ResponseEntity<BaseResponseDto<Object>> findAll() {
                List<RegistroResponse> registros = registroService.findRegisterAll();
                if (registros.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                                                        .message("No existen registros").build());
                }

                return ResponseEntity.status(HttpStatus.OK)
                                .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                                                .data(registros).message("Registros encontrados con éxito").build());
        }

        /**
         * Find registros by vehicle IMEI
         *
         * @param imei Vehicle IMEI
         * @return List of RegistroEntity wrapped in BaseResponseDto
         */
        @GetMapping(path = "/imei/{imei}")
        @Operation(summary = "Find registros by vehicle IMEI")
        public ResponseEntity<BaseResponseDto<Object>> findByVehiculoImei(@PathVariable String imei) {
                List<RegistroResponse> registros = registroService.findByVehiculoImei(imei);
                if (registros.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                                                        .message("No existen registros para el IMEI especificado")
                                                        .build());
                }

                return ResponseEntity.status(HttpStatus.OK)
                                .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                                                .data(registros).message("Registros encontrados con éxito").build());
        }

        /**
         * Find registros by date range and optional IMEI
         *
         * @param startDate Start date (ISO format)
         * @param endDate   End date (ISO format)
         * @param imei      Optional vehicle IMEI
         * @return List of RegistroEntity wrapped in BaseResponseDto
         */
        @GetMapping(path = "/range")
        @Operation(summary = "Find registros by date range")
        public ResponseEntity<BaseResponseDto<Object>> findByDateRange(
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate,
                        @RequestParam(required = false) String imei) {
                List<RegistroResponse> registros = registroService.findByFechaBetween(startDate, endDate, imei);
                if (registros.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body(BaseResponseDto.builder().code(HttpStatus.OK.value()).message(
                                                        "No existen registros en el rango de fechas especificado")
                                                        .build());
                }

                return ResponseEntity.status(HttpStatus.OK)
                                .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                                                .data(registros).message("Registros encontrados con éxito").build());
        }

        /**
         * Delete registro by id
         *
         * @param id Registro ID
         * @return BaseResponseDto
         */
        @Secured({ "ROLE_ADMIN" })
        @DeleteMapping(path = "/{id}")
        @Operation(summary = "Delete registro")
        public ResponseEntity<BaseResponseDto<Object>> deleteById(@PathVariable Long id) {
                Optional<RegistroEntity> registro = registroService.findById(id);
                if (registro.isPresent()) {
                        registroService.deleteById(id);
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                                                        .message("Registro eliminado con éxito").build());
                } else {
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body(BaseResponseDto.builder().code(HttpStatus.OK.value())
                                                        .message("El registro no existe").build());
                }
        }
}
