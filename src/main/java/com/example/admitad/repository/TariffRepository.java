package com.example.admitad.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TariffRepository extends BaseRepository{
    @Update("CREATE TABLE IF NOT EXISTS `tariff`( `id` INTEGER NOT NULL AUTO_INCREMENT, `name` VARCHAR(255) NOT NULL, PRIMARY KEY(`id`))")
    void createTableIfMissing();

    @Update("DROP TABLE IF EXISTS `tariff`")
    void dropTableIfExists();

    @Override
    default String getName() {
        return "tariff";
    }
}