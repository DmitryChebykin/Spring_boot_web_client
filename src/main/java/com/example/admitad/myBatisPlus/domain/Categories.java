package com.example.admitad.myBatisPlus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "categories")
public class Categories {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "language")
    private String language;

    @TableField(value = "parent_id")
    private Integer parentId;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_LANGUAGE = "language";

    public static final String COL_PARENT_ID = "parent_id";
}