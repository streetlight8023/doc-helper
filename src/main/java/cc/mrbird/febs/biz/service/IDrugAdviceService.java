package cc.mrbird.febs.biz.service;

import cc.mrbird.febs.biz.domain.DrugAdviceDo;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author MrBird
 */
public interface IDrugAdviceService extends IService<DrugAdviceDo> {

    DrugAdviceDo findByName(String name);

    IPage<DrugAdviceDo> findDrugAdvicePage(DrugAdviceDo advice, QueryRequest request);

    List<DrugAdviceDo> findDrugAdviceList(String name);


    void createDrugAdviceDo(DrugAdviceDo advice);

    void deleteAdvices(String[] ids);

    void updateAdvice(DrugAdviceDo advice);
}
