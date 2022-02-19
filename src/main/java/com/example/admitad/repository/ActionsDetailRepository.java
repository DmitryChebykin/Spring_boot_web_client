package com.example.admitad.repository;

import com.example.admitad.jsonModel.JsonActionDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ActionsDetailRepository extends BaseRepository {
    @Override
    default String getName() {
        return "actions_detail";
    }

    @Update("CREATE TABLE actions_detail (id INTEGER PRIMARY KEY," + "name VARCHAR(255)," + "type VARCHAR(255)," + "hold_size INTEGER)")
    void createTableIfMissing();

    @Update("DROP TABLE IF EXISTS `actions_detail`")
    void dropTableIfExists();

    @Insert("INSERT INTO actions_detail(hold_size, name, type, id) " +
            "VALUES(#{holdSize}, #{name}, #{type}, #{id})" +
            " ON DUPLICATE KEY UPDATE " +
            "hold_size = #{holdSize}, " +
            "name = #{name}, " +
            "type = #{type}")
    void insertOrUpdateItem(JsonActionDetail jsonActionDetail);

}