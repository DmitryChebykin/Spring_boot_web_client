package com.example.admitad.myBatisPlus.service.singleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admitad.myBatisPlus.CategoriesMapper;
import com.example.admitad.myBatisPlus.domain.Categories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService extends ServiceImpl<CategoriesMapper, Categories> {


    public int updateBatch(List<Categories> list) {
        return baseMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<Categories> list) {
        return baseMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<Categories> list) {
        return baseMapper.batchInsert(list);
    }

    public int insertOrUpdate(Categories record) {
        return baseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(Categories record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
