package com.example.admitad.myBatisPlus.service.singleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admitad.myBatisPlus.ActionsDetailTariffLinkMapper;
import com.example.admitad.myBatisPlus.domain.link.ActionsDetailTariffLink;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActionsDetailTariffLinkService extends ServiceImpl<ActionsDetailTariffLinkMapper, ActionsDetailTariffLink> {


    public int updateBatch(List<ActionsDetailTariffLink> list) {
        return baseMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<ActionsDetailTariffLink> list) {
        return baseMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<ActionsDetailTariffLink> list) {
        return baseMapper.batchInsert(list);
    }

    public int insertOrUpdate(ActionsDetailTariffLink record) {
        return baseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(ActionsDetailTariffLink record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
