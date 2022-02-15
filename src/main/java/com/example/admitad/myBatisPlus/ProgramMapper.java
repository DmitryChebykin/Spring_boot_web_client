package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProgramMapper extends BaseMapper<Program> {
    int updateBatch(List<Program> list);

    int updateBatchSelective(List<Program> list);

    int batchInsert(@Param("list") List<Program> list);

    int insertOrUpdate(Program record);

    int insertOrUpdateSelective(Program record);
}