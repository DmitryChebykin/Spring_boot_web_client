package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admitad.myBatisPlus.domain.Categories;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoriesMapper extends BaseMapper<Categories> {
    int updateBatch(List<Categories> list);

    int updateBatchSelective(List<Categories> list);

    int batchInsert(@Param("list") List<Categories> list);

    int insertOrUpdate(Categories record);

    int insertOrUpdateSelective(Categories record);
}