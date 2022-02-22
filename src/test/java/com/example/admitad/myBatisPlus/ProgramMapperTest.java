package com.example.admitad.myBatisPlus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.admitad.myBatisPlus.domain.Program;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.charset.StandardCharsets;

@ExtendWith(SpringExtension.class)
@MybatisPlusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProgramMapperTest {
    @Autowired
    private ProgramMapper programMapper;

    @Container
    private static final MySQLContainer mysql = new MySQLContainer("mysql:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @BeforeAll
    private void initDatabaseProperties() {
        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());
    }


    @Test
    void whenInsertNewProgram_ThenNonZeroIntReturns() {
        Program program = Program.builder()
                .id(1)
                .gotoLink("test_link")
                .image("".getBytes(StandardCharsets.UTF_8))
                .imageUri("image_link")
                .name("test_name")
                .productsXmlLink("test_productsXmlLink").build();

        int insertIdx = programMapper.insertOrUpdate(program);

        Assertions.assertNotEquals(0, insertIdx);
    }

    @Test
    void whenInsertTwoProgram_ReturnCountIs_2() {
        Program program1 = Program.builder()
                .id(1)
                .gotoLink("test_link")
                .image("".getBytes(StandardCharsets.UTF_8))
                .imageUri("image_link")
                .name("test_name")
                .productsXmlLink("test_productsXmlLink").build();

        programMapper.insert(program1);

        Program program2 = Program.builder()
                .id(2)
                .gotoLink("test_linkasdasds")
                .image("sdsda".getBytes(StandardCharsets.UTF_8))
                .imageUri("image_sdsdsdlink")
                .name("test_sdsdsname")
                .productsXmlLink("test_productsdsasdsXmlLink").build();

        programMapper.insertOrUpdate(program2);

        QueryWrapper<Program> programQueryWrapper = Wrappers.query();
        QueryWrapper<Program> select = programQueryWrapper.select("*");

        Long count = programMapper.selectCount(select);

        Assertions.assertEquals(count, 2);
    }


}
