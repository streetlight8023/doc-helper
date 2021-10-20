package cc.mrbird.febs.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@TableName("drug_advice")
public class DrugAdviceDo {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("drug_name")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "组套名称")
    private String drugName;

    @TableField("per_dose")
    private Integer perDose;

    @TableField("dose_unit")
    private String doseUnit;

    @TableField("use_way")
    private String useWay;

    @TableField("time_per_day")
    private Integer timePerDay;

    @TableField("days")
    private Integer days;

    @TableField("total_dose")
    private Integer totalDos;

    @TableField("category_id")
    private Integer categoryId;

    @TableField("stack_id")
    private Integer stackId =0;

    @TableField("user_id")
    private Integer userId;

    @TableField("create_time")
    private Date  createTime;


    @TableField("modify_time")
    private Date  modifyTime;

    @TableField("is_delete")
    private Integer isDelete;

}
