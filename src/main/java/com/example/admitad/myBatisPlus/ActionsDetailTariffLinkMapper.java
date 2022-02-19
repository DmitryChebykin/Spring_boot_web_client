package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admitad.myBatisPlus.domain.link.ActionsDetailTariffLink;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ActionsDetailTariffLinkMapper extends BaseMapper<ActionsDetailTariffLink> {
    int updateBatch(List<ActionsDetailTariffLink> list);

    int updateBatchSelective(List<ActionsDetailTariffLink> list);

    int batchInsert(@Param("list") List<ActionsDetailTariffLink> list);

    int insertOrUpdate(ActionsDetailTariffLink record);

    int insertOrUpdateSelective(ActionsDetailTariffLink record);
}