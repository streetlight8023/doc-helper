package cc.mrbird.febs.biz.mapper;

import cc.mrbird.febs.biz.domain.DrugAdviceDo;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DrugAdviceMapper extends BaseMapper<DrugAdviceDo> {

    DrugAdviceDo findByName(String name);

    <T> IPage<DrugAdviceDo> findDrugAdvicePage(Page<T> page, @Param("drugAdviceDo") DrugAdviceDo drugAdviceDo);

    long countDrugAdvices(@Param("drugAdviceDo") DrugAdviceDo advice);

    List<DrugAdviceDo> findDrugAdviceDos(@Param("drugAdviceDo") DrugAdviceDo advice);

}
