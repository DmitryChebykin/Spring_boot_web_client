package com.example.admitad.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CategoryRepository extends BaseRepository{
    @Override
    default String getName() {
        return "category";
    }

    @Update("CREATE TABLE categories (id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL," + "name VARCHAR(255) NOT NULL," + "language VARCHAR(255) NOT NULL," + "parent_id INTEGER)")
    void createTableIfMissing();

    @Update("DROP TABLE IF EXISTS `categories`")
    void dropTableIfExists();
}