package com.example.admitad.myBatisPlus.service.singleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admitad.myBatisPlus.TariffMapper;
import com.example.admitad.myBatisPlus.domain.Tariff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService extends ServiceImpl<TariffMapper, Tariff> {


    public int updateBatch(List<Tariff> list) {
        return baseMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<Tariff> list) {
        return baseMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<Tariff> list) {
        return baseMapper.batchInsert(list);
    }

    public int insertOrUpdate(Tariff record) {
        return baseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(Tariff record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
