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
@TableName(value = "actions_detail_tariff_link")
public class ActionsDetailTariffLink {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "actions_detail_id")
    private Integer actionsDetailId;

    @TableField(value = "tariff_id")
    private Integer tariffId;

    public static final String COL_ID = "id";

    public static final String COL_ACTIONS_DETAIL_ID = "actions_detail_id";

    public static final String COL_TARIFF_ID = "tariff_id";
}