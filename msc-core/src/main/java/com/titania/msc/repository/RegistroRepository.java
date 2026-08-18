package com.titania.msc.repository;

import com.querydsl.core.BooleanBuilder;
import com.titania.msc.entity.RegistroEntity;
import com.titania.msc.exception.ExceptionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.titania.msc.entity.QRegistroEntity.registroEntity;

@Slf4j
@Repository
public class RegistroRepository extends GenericRepository<RegistroEntity, Long> implements IRegistroRepository {

    public RegistroRepository() {
        super(RegistroEntity.class);
    }

    @Override
    public List<RegistroEntity> findByVehiculoImei(String imei) throws ExceptionManager {
        try {
            BooleanBuilder where = new BooleanBuilder();
            where.and(registroEntity.vehiculo.imei.eq(imei));
            where.and(registroEntity.status.isTrue());

            return queryFactory.selectFrom(registroEntity)
                    .where(where)
                    .fetch();
        } catch (Exception e) {
            log.error("findByVehiculoImei: ", e);
            throw new ExceptionManager.FindingException("Error al buscar los registros por IMEI de vehículo");
        }
    }

    @Override
    public List<RegistroEntity> findByFechaBetween(Date startDate, Date endDate, String imei) throws ExceptionManager {
        try {
            BooleanBuilder where = new BooleanBuilder();
            where.and(registroEntity.fecha.between(startDate, endDate));
            where.and(registroEntity.status.isTrue());
            if (StringUtils.isNotBlank(imei)) {
                where.and(registroEntity.vehiculo.imei.eq(imei));
            }

            return queryFactory.selectFrom(registroEntity)
                    .where(where)
                    .orderBy(registroEntity.fecha.asc())
                    .fetch();
        } catch (Exception e) {
            log.error("findByFechaBetween: ", e);
            throw new ExceptionManager.FindingException("Error al buscar los registros por rango de fecha");
        }
    }
}

