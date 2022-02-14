package com.example.admitad.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ActionsDetailRepository extends BaseRepository {
    @Override
    default String getName() {
        return "actions_detail";
    }

    @Update("CREATE TABLE actions_detail (id INTEGER PRIMARY KEY AUTO_INCREMENT," + "name VARCHAR(255) NOT NULL," + "type VARCHAR(255) NOT NULL," + "hold_size INTEGER)")
    void createTableIfMissing();

    @Update("DROP TABLE IF EXISTS `actions_detail`")
    void dropTableIfExists();
}