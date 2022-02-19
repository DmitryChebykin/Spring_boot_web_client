package com.example.admitad.myBatisPlus.service.singleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admitad.myBatisPlus.RatesMapper;
import com.example.admitad.myBatisPlus.domain.Rates;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
