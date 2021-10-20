package cc.mrbird.febs.biz.mapper;

import cc.mrbird.febs.biz.controller.vo.TreeStackVo;
import cc.mrbird.febs.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author MrBird
 */
public interface TreeStackMapper extends BaseMapper<TreeStackVo> {


    /**
     * 查找用户组套
     *
     * @param userId 用户名id
     * @return 用户菜单集合
     */
    List<TreeStackVo> findUserTreeStack(Integer userId);
}