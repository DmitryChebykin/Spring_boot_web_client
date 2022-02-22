package com.example.admitad.repository;

import com.example.admitad.jsonModel.JsonAdvertisementProgram;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProgramRepository extends BaseRepository {
    default String getName() {
        return "program";
    }

    @Update("CREATE TABLE  IF NOT EXISTS program (id INTEGER PRIMARY KEY, " + "name VARCHAR(255) NOT NULL," + "image_uri VARCHAR(255)," + "image BLOB," + "products_xml_link VARCHAR(255)," + "goto_link VARCHAR(255))")
    void createTableIfMissing();

    @Update("DROP TABLE IF EXISTS `program`")
    void dropTableIfExists();

    @Insert("INSERT INTO program(name, goto_link, image_uri, image, products_xml_link, id) " +
            "VALUES(#{name}, #{gotoLink}, #{imageUri}, #{image}, #{productXmlLink}, #{id})" +
            "ON DUPLICATE KEY UPDATE " +
            "name = #{name}, " +
            "goto_link = #{gotoLink}, " +
            "image_uri = #{imageUri}, " +
            "image = #{image}, " +
            "products_xml_link = #{productXmlLink}")
    void insertOrUpdateItem(JsonAdvertisementProgram jsonAdvertisementProgram);
}