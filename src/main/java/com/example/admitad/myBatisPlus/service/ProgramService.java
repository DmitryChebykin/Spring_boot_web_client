package com.example.admitad.myBatisPlus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admitad.myBatisPlus.ProgramMapper;
import com.example.admitad.myBatisPlus.domain.Program;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProgramService extends ServiceImpl<ProgramMapper, Program> {

    
    public int updateBatch(List<Program> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<Program> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<Program> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(Program record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(Program record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
