package com.example.admitad.myBatisPlus.service;

import com.example.admitad.myBatisPlus.ActionsDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admitad.myBatisPlus.domain.ActionsDetail;

@Service
public class ActionsDetailService extends ServiceImpl<ActionsDetailMapper, ActionsDetail> {

    
    public int updateBatch(List<ActionsDetail> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<ActionsDetail> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<ActionsDetail> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(ActionsDetail record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(ActionsDetail record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
