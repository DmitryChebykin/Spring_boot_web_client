package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admitad.myBatisPlus.domain.link.RateTariffLink;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RateTariffLinkMapper extends BaseMapper<RateTariffLink> {
    int updateBatch(List<RateTariffLink> list);

    int updateBatchSelective(List<RateTariffLink> list);

    int batchInsert(@Param("list") List<RateTariffLink> list);

    int insertOrUpdate(RateTariffLink record);

    int insertOrUpdateSelective(RateTariffLink record);
}