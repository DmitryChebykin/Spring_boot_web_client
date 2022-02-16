package com.example.admitad.myBatisPlus.service;

import com.example.admitad.myBatisPlus.RatesMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admitad.myBatisPlus.domain.Rates;
@Service
public class RatesService extends ServiceImpl<RatesMapper, Rates> {

    
    public int updateBatch(List<Rates> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<Rates> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<Rates> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(Rates record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(Rates record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
