package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admitad.myBatisPlus.domain.Rates;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RatesMapper extends BaseMapper<Rates> {
    int updateBatch(List<Rates> list);

    int updateBatchSelective(List<Rates> list);

    int batchInsert(@Param("list") List<Rates> list);

    int insertOrUpdate(Rates record);

    int insertOrUpdateSelective(Rates record);
}