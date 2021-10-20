package cc.mrbird.febs.biz.controller.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@TableName("tree_stack")
public class TreeStackVo {

    public static final Integer TOP_NODE = 0;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("stack_name")
    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    @ExcelField(value = "组套名称")
    private String stackName;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("user_id")
    private Integer userId;

    @TableField("icon")
    private String icon;

    @TableField("sort_order")
    private Integer sortOrder;

    @TableField("create_time")
    private Date  createTime;

    @TableField("modify_time")
    private Date  modifyTime;

    @TableField("is_delete")
    private Integer isDelete;

}
