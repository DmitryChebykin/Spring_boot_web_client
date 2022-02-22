package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admitad.myBatisPlus.domain.Tariff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TariffMapper extends BaseMapper<Tariff> {
    int updateBatch(List<Tariff> list);

    int updateBatchSelective(List<Tariff> list);

    int batchInsert(@Param("list") List<Tariff> list);

    int insertOrUpdate(Tariff tariff);

    int insertOrUpdateSelective(Tariff tariff);

    @Insert({"<script>",
            "insert into tariff (id, name)",
            "values",
            "<foreach  collection='list' item='tariff' separator=','>",
            "(#{tariff.id}, #{tariff.name})",
            "</foreach>",
            "on duplicate key update ",
            "`name` = values(`name`), " +
                    "id = values(id);",
            "</script>"})
    int batchInsertOrUpdate(@Param("list") List<Tariff> tariffList);


}