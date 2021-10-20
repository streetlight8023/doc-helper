package cc.mrbird.febs.biz.service;

import cc.mrbird.febs.biz.controller.vo.TreeStackVo;
import cc.mrbird.febs.common.entity.BaseTree;
import cc.mrbird.febs.common.entity.MenuTree;
import cc.mrbird.febs.system.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ITreeStackService extends IService<TreeStackVo> {


    /**
     * 查看当前用户拥有的组套
     * @param userId
     * @return
     */
    BaseTree<TreeStackVo> findTreeStacks(Integer userId);

    /**
     * 查看所有的组套（树）
     * @param treeStackVo
     * @return
     */
    BaseTree<TreeStackVo> findTreeStacks(TreeStackVo treeStackVo);

    /**
     * 查看所有的组套（列表）
     * @param treeStackVo
     * @return
     */
    List<TreeStackVo> findStackList(TreeStackVo treeStackVo);

    void createTreeStack(TreeStackVo treeStackVo);

    void updateTreeStack(TreeStackVo treeStackVo);

    void deleteTreeStack(String stackIds);
}
