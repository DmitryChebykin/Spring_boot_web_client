package com.example.admitad.myBatisPlus;

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
@TableName(value = "`program`")
public class Program {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "image_uri")
    private String imageUri;

    @TableField(value = "image")
    private byte[] image;

    @TableField(value = "products_xml_link")
    private String productsXmlLink;

    @TableField(value = "gotoLink")
    private String gotolink;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_IMAGE_URI = "image_uri";

    public static final String COL_IMAGE = "image";

    public static final String COL_PRODUCTS_XML_LINK = "products_xml_link";

    public static final String COL_GOTOLINK = "gotoLink";
}