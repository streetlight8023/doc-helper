package cc.mrbird.febs.biz.controller;

import cc.mrbird.febs.biz.controller.vo.TreeStackVo;
import cc.mrbird.febs.biz.service.ITreeStackService;
import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.exception.FebsException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("stack")
public class TreeStackController extends BaseController {

    private final ITreeStackService treeStackService;

    @GetMapping("{username}")
    public FebsResponse findTreeStacks(@NotBlank(message = "{required}") @PathVariable Integer userId) throws FebsException {
        if (!userId.equals(getCurrentUser().getUserId()) ) {
            throw new FebsException("您无权获取别人的组套");
        }
        return new FebsResponse().data(treeStackService.findTreeStacks(userId));
    }

    @GetMapping("tree")
    @ControllerEndpoint(exceptionMessage = "获取组套树失败")
    public FebsResponse findTreeStacks(TreeStackVo vo) {
        return new FebsResponse().success()
                .data(treeStackService.findTreeStacks(vo).getChilds());
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增组套", exceptionMessage = "新增组套")
    public FebsResponse createTreeStack(@Valid TreeStackVo vo) {
        treeStackService.createTreeStack(vo);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{stackIds}")
    @ControllerEndpoint(operation = "删除组套", exceptionMessage = "删除组套失败")
    public FebsResponse deleteTreeStack(@NotBlank(message = "{required}") @PathVariable String stackIds) {
        treeStackService.deleteTreeStack(stackIds);
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改组套", exceptionMessage = "修改组套失败")
    public FebsResponse updateTreeStack(@Valid TreeStackVo vo) {
        treeStackService.updateTreeStack(vo);
        return new FebsResponse().success();
    }




//    private List<TreeStackVo> getTreeStackVos(){
//        List<TreeStackVo> topParentList = Lists.newArrayList();
//        List<TreeStackVo> parentList = Lists.newArrayList();
//        List<TreeStackVo> childList = Lists.newArrayList();
//        TreeStackVo child = new TreeStackVo();
//        child.setId(1);
//        child.setTitle("子目录");
//        childList.add(child);
//
//
//        TreeStackVo parent = new TreeStackVo();
//        parent.setId(2);
//        parent.setTitle("父目录");
//        parent.setChildren(childList);
//        parentList.add(parent);
//
//        TreeStackVo topParent = new TreeStackVo();
//        topParent.setId(3);
//        topParent.setTitle("最高父目录");
//        topParent.setChildren(parentList);
//        topParentList.add(topParent);
//
//        return topParentList;
//    }
}
