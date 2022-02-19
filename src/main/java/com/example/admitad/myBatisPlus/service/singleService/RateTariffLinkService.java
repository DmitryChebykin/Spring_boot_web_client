package com.example.admitad.myBatisPlus.service.singleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admitad.myBatisPlus.RateTariffLinkMapper;
import com.example.admitad.myBatisPlus.domain.link.RateTariffLink;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RateTariffLinkService extends ServiceImpl<RateTariffLinkMapper, RateTariffLink> {

    
    public int updateBatch(List<RateTariffLink> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<RateTariffLink> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<RateTariffLink> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(RateTariffLink record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(RateTariffLink record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
