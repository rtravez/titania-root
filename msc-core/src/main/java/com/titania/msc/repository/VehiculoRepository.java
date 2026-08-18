package com.titania.msc.repository;

import com.querydsl.core.BooleanBuilder;
import com.titania.msc.entity.VehiculoEntity;
import com.titania.msc.exception.ExceptionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.titania.msc.entity.QVehiculoEntity.vehiculoEntity;

@Slf4j
@Repository
public class VehiculoRepository extends GenericRepository<VehiculoEntity, Long> implements IVehiculoRepository {

    public VehiculoRepository() {
        super(VehiculoEntity.class);
    }

    @Override
    public Optional<VehiculoEntity> findByImei(String imei) throws ExceptionManager {
        try {
            BooleanBuilder where = new BooleanBuilder();
            where.and(vehiculoEntity.imei.eq(imei));
            where.and(vehiculoEntity.status.isTrue());

            return Optional.ofNullable(queryFactory.selectFrom(vehiculoEntity)
                    .where(where)
                    .fetchOne());
        } catch (Exception e) {
            log.error("findByImei: ", e);
            throw new ExceptionManager.FindingException("Error al buscar el vehículo por IMEI");
        }
    }
}
