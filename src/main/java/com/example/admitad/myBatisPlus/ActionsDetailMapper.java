package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admitad.myBatisPlus.domain.ActionsDetail;
import org.apache.ibatis.annotations.Insert;
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

    @Insert({"<script>",
            "insert into actions_detail (id, type, hold_size, name)",
            "values",
            "<foreach  collection='list' item='actions_detail' separator=','>",
            "(#{actions_detail.id}, #{actions_detail.type}, #{actions_detail.holdSize}, #{actions_detail.name})",
            "</foreach>",
            "on duplicate key update ",
            "`name` = values(`name`), " +
                    "type = values(type), " +
                    "id = values(id), " +
                    "hold_size = values(hold_size);",
            "</script>"})
    int batchInsertOrUpdate(@Param("list") List<ActionsDetail> actionsDetailList);
}