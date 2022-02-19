package com.example.admitad.myBatisPlus.domain.link;

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
@TableName(value = "rate_tariff_link")
public class RateTariffLink {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "rate_id")
    private Integer rateId;

    @TableField(value = "tariff_id")
    private Integer tariffId;

    public static final String COL_ID = "id";

    public static final String COL_RATE_ID = "rate_id";

    public static final String COL_TARIFF_ID = "tariff_id";
}