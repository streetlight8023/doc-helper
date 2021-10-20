package cc.mrbird.febs.biz.service.impl;

import cc.mrbird.febs.biz.domain.DrugAdviceDo;
import cc.mrbird.febs.biz.mapper.DrugAdviceMapper;
import cc.mrbird.febs.biz.service.IDrugAdviceService;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.common.event.UserAuthenticationUpdatedEventPublisher;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.utils.Md5Util;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.system.entity.*;
import cc.mrbird.febs.system.mapper.UserMapper;
import cc.mrbird.febs.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MrBird
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DrugAdviceServiceImpl extends ServiceImpl<DrugAdviceMapper, DrugAdviceDo> implements IDrugAdviceService {


    @Override
    public DrugAdviceDo findByName(String name) {
        return baseMapper.findByName(name);
    }

    @Override
    public IPage<DrugAdviceDo> findDrugAdvicePage(DrugAdviceDo advice, QueryRequest request) {
        Page<User> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countDrugAdvices(advice));
        SortUtil.handlePageSort(request, page, "id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findDrugAdvicePage(page, advice);
    }

    @Override
    public List<DrugAdviceDo> findDrugAdviceList(String name) {
        DrugAdviceDo param = new DrugAdviceDo();
        param.setDrugName(name);
        return  baseMapper.findDrugAdviceDos(param);
    }

    @Override
    public void createDrugAdviceDo(DrugAdviceDo advice) {
        advice.setCreateTime(new Date());
        save(advice);

    }

    @Override
    public void deleteAdvices(String[] ids) {
        List<String> list = Arrays.asList(ids);
        removeByIds(list);
    }

    @Override
    public void updateAdvice(DrugAdviceDo advice) {
        advice.setModifyTime(new Date());
        updateById(advice);
    }
}
