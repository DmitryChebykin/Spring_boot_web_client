package com.example.admitad.repository;

import com.example.admitad.jsonModel.JsonCategory;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("INSERT INTO categories(language, name, parent_id, id) " +
            "VALUES(#{language}, #{name}, #{parent.id}, #{id})" +
            " ON DUPLICATE KEY UPDATE " +
            "language = #{language}, " +
            "name = #{name}, " +
            "parent_id = #{parent.id}")
    void insertOrUpdateItem(JsonCategory jsonCategory);
}