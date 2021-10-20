package cc.mrbird.febs.biz.service.impl;

import cc.mrbird.febs.biz.controller.vo.TreeStackVo;
import cc.mrbird.febs.biz.mapper.TreeStackMapper;
import cc.mrbird.febs.biz.service.ITreeStackService;
import cc.mrbird.febs.common.entity.BaseTree;
import cc.mrbird.febs.common.entity.MenuTree;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.event.UserAuthenticationUpdatedEventPublisher;
import cc.mrbird.febs.common.utils.BaseTreeUtil;
import cc.mrbird.febs.common.utils.TreeUtil;
import cc.mrbird.febs.system.entity.Menu;
import cc.mrbird.febs.system.service.IRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author MrBird
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TreeStackServiceImpl extends ServiceImpl<TreeStackMapper, TreeStackVo> implements ITreeStackService {

    @Override
    public BaseTree<TreeStackVo> findTreeStacks(Integer userId) {
        List<TreeStackVo> treeStackVos = baseMapper.findUserTreeStack(userId);
        List<BaseTree<TreeStackVo>>  trees = convertTreeStacks(treeStackVos);
        return BaseTreeUtil.buildBaseTree(trees);
    }

    @Override
    public BaseTree<TreeStackVo> findTreeStacks(TreeStackVo treeStackVo) {
        QueryWrapper<TreeStackVo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(treeStackVo.getStackName())) {
            queryWrapper.lambda().like(TreeStackVo::getStackName, treeStackVo.getStackName());
        }
        queryWrapper.lambda().orderByAsc(TreeStackVo::getSortOrder);
        List<TreeStackVo> menus = baseMapper.selectList(queryWrapper);
        List<BaseTree<TreeStackVo>>  trees = convertTreeStacks(menus);
        return BaseTreeUtil.buildBaseTree(trees);
    }

    @Override
    public List<TreeStackVo> findStackList(TreeStackVo treeStackVo) {
        QueryWrapper<TreeStackVo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(treeStackVo.getStackName())) {
            queryWrapper.lambda().like(TreeStackVo::getStackName, treeStackVo.getStackName());
        }
        queryWrapper.lambda().orderByAsc(TreeStackVo::getId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void createTreeStack(TreeStackVo treeStackVo) {
        treeStackVo.setCreateTime(new Date());
        setTreeStack(treeStackVo);
        baseMapper.insert(treeStackVo);
    }

    @Override
    public void updateTreeStack(TreeStackVo treeStackVo) {
        treeStackVo.setModifyTime(new Date());
        setTreeStack(treeStackVo);
        baseMapper.updateById(treeStackVo);
    }

    @Override
    public void deleteTreeStack(String stackIdStr) {
        List<String> stackIds = Arrays.asList(stackIdStr.split(Strings.COMMA));
        delete(stackIds);
    }

    private List<BaseTree<TreeStackVo>> convertTreeStacks(List<TreeStackVo> treeStackVos) {
        List<BaseTree<TreeStackVo>> trees = new ArrayList<>();
        treeStackVos.forEach(treeStack -> {
            BaseTree<TreeStackVo> tree = new BaseTree<>();
            tree.setId(String.valueOf(treeStack.getId()));
            tree.setParentId(String.valueOf(treeStack.getParentId()));
            tree.setTitle(treeStack.getStackName());
            tree.setIcon(treeStack.getIcon());
            tree.setData(treeStack);
            trees.add(tree);
        });
        return trees;
    }

    private void setTreeStack(TreeStackVo treeStackVo) {
        if (treeStackVo.getParentId() == null) {
            treeStackVo.setParentId(TreeStackVo.TOP_NODE);
        }
    }

    private void delete(List<String> stackIds) {
        List<String> list = new ArrayList<>(stackIds);
        removeByIds(stackIds);

        LambdaQueryWrapper<TreeStackVo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TreeStackVo::getParentId, stackIds);
        List<TreeStackVo> treeStackVos = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(treeStackVos)) {
            List<String> menuIdList = new ArrayList<>();
            treeStackVos.forEach(m -> menuIdList.add(String.valueOf(m.getId())));
            list.addAll(menuIdList);
            delete(menuIdList);
        }
    }


}
