package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admitad.myBatisPlus.domain.Program;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProgramMapper extends BaseMapper<Program> {
    int updateBatch(List<Program> list);

    int updateBatchSelective(List<Program> list);

    int batchInsert(@Param("list") List<Program> list);

    int insertOrUpdate(Program record);

    int insertOrUpdateSelective(Program record);

    @Insert({"<script>",
            "insert into program (id, image, products_xml_link, image_uri, goto_link, name)",
            "values",
            "<foreach  collection='list' item='program' separator=','>",
            "(#{program.id}, #{program.image}, #{program.productsXmlLink}, #{program.imageUri}, #{program.gotoLink}, #{program.name})",
            "</foreach>",
            "on duplicate key update ",
            "`name` = values(`name`), " +
                    "goto_link = values(goto_link), " +
                    "id = values(id), " +
                    "image_uri = values(image_uri), " +
                    "image = values(image), " +
                    "products_xml_link = values(products_xml_link);",
            "</script>"})
    int batchInsertOrUpdate(@Param("list") List<Program> list);
}