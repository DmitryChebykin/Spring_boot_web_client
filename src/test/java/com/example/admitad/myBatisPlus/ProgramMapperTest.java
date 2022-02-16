package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.admitad.myBatisPlus.domain.Program;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;

@ExtendWith(SpringExtension.class)
@MybatisPlusTest
public class ProgramMapperTest {
    @Autowired
    private ProgramMapper programMapper;

    @Test
    void whenInsertNewProgram_ThenNonZeroIntReturns() {
        Program program = Program.builder()
                .gotolink("test_link")
                .image("".getBytes(StandardCharsets.UTF_8))
                .imageUri("image_link")
                .name("test_name")
                .productsXmlLink("test_productsXmlLink").build();

        int insertIdx = programMapper.insert(program);

        Assertions.assertNotEquals(0, insertIdx);
    }

    @Test
    void whenInsertTwoProgram_ReturnCountIs_2() {
        Program program1 = Program.builder()
                .gotolink("test_link")
                .image("".getBytes(StandardCharsets.UTF_8))
                .imageUri("image_link")
                .name("test_name")
                .productsXmlLink("test_productsXmlLink").build();

        programMapper.insert(program1);

        Program program2 = Program.builder()
                .gotolink("test_linkasdasds")
                .image("sdsda".getBytes(StandardCharsets.UTF_8))
                .imageUri("image_sdsdsdlink")
                .name("test_sdsdsname")
                .productsXmlLink("test_productsdsasdsXmlLink").build();

        programMapper.insert(program2);

        QueryWrapper<Program> programQueryWrapper = Wrappers.query();
        QueryWrapper<Program> select = programQueryWrapper.select("*");

        Long count = programMapper.selectCount(select);

        Assertions.assertEquals(count, 2);
    }


}
