package cc.mrbird.febs.biz.controller;

import cc.mrbird.febs.biz.domain.DrugAdviceDo;
import cc.mrbird.febs.biz.service.IDrugAdviceService;
import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.Md5Util;
import cc.mrbird.febs.system.entity.User;
import cc.mrbird.febs.system.service.IUserService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author MrBird
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("advice")
public class DrugAdviceController extends BaseController {

    private final IDrugAdviceService drugAdviceService;

    @GetMapping("{name}")
    public DrugAdviceDo findByName(@NotBlank(message = "{required}") @PathVariable String name) {
        return drugAdviceService.findByName(name);
    }


    @GetMapping("list")
    public FebsResponse userList(DrugAdviceDo advice, QueryRequest request) {
        advice.setUserId(getCurrentUser().getUserId().intValue());
        return new FebsResponse().success()
                .data(getDataTable(drugAdviceService.findDrugAdvicePage(advice, request)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增医嘱", exceptionMessage = "新增医嘱失败")
    public FebsResponse createDrugAdviceDo(@Valid DrugAdviceDo advice) {
        advice.setUserId(getCurrentUser().getUserId().intValue());
        advice.setTotalDos(advice.getPerDose()*advice.getTimePerDay()*advice.getDays());
        drugAdviceService.createDrugAdviceDo(advice);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{ids}")
    @ControllerEndpoint(operation = "删除医嘱", exceptionMessage = "删除医嘱失败")
    public FebsResponse deleteAdvices(@NotBlank(message = "{required}") @PathVariable String ids) {
        drugAdviceService.deleteAdvices(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改医嘱", exceptionMessage = "修改医嘱失败")
    public FebsResponse updateAdvice(@Valid DrugAdviceDo advice) {
        if (advice.getId() == null) {
            throw new FebsException("ID为空");
        }
        drugAdviceService.updateAdvice(advice);
        return new FebsResponse().success();
    }

}
