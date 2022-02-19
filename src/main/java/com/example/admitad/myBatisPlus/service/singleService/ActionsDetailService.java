package com.example.admitad.myBatisPlus.service.singleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admitad.myBatisPlus.ActionsDetailMapper;
import com.example.admitad.myBatisPlus.domain.ActionsDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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

    public int batchInsertOrUpdate(List<ActionsDetail> actionsDetailList) {
        return baseMapper.batchInsertOrUpdate(actionsDetailList);
    }
}
