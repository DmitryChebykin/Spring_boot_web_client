package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admitad.myBatisPlus.domain.ActionsDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActionsDetailMapper extends BaseMapper<ActionsDetail> {
    int updateBatch(List<ActionsDetail> list);

    int updateBatchSelective(List<ActionsDetail> list);

    int batchInsert(@Param("list") List<ActionsDetail> list);

    int insertOrUpdate(ActionsDetail record);

    int insertOrUpdateSelective(ActionsDetail record);
}