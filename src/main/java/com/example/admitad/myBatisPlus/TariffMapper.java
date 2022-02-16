package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admitad.myBatisPlus.domain.Tariff;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TariffMapper extends BaseMapper<Tariff> {
    int updateBatch(List<Tariff> list);

    int updateBatchSelective(List<Tariff> list);

    int batchInsert(@Param("list") List<Tariff> list);

    int insertOrUpdate(Tariff record);

    int insertOrUpdateSelective(Tariff record);
}