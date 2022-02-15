package com.example.admitad.repository;

import com.example.admitad.jsonModel.AdvertisementProgram;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProgramRepository extends BaseRepository {
    default String getName() {
        return "program";
    }

    @Update("CREATE TABLE  IF NOT EXISTS program (id INTEGER PRIMARY KEY AUTO_INCREMENT, " + "name VARCHAR(255) NOT NULL," + "image_uri VARCHAR(255)," + "image BLOB," + "products_xml_link VARCHAR(255)," + "gotoLink VARCHAR(255))")
    void createTableIfMissing();

    @Update("DROP TABLE IF EXISTS `program`")
    void dropTableIfExists();

    @Insert("INSERT INTO program(name, gotoLink, image_uri, image, products_xml_link, id) " +
            "VALUES(#{name}, #{gotoLink}, #{imageUri}, #{imageBytes}, #{productXmlLink}, #{id})" +
            "ON DUPLICATE KEY UPDATE " +
            "name = #{name}, " +
            "gotoLink = #{gotoLink}, " +
            "image_uri = #{imageUri}, " +
            "image = #{imageBytes}, " +
            "products_xml_link = #{productXmlLink}")
    void insertOrUpdateItem(AdvertisementProgram advertisementProgram);
}