package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.medicine.entity.Channel;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class ChannelService extends BaseService<Channel>{
    @Override
    public Channel save(Channel o) {
        if (Assert.isEmpty(o.getId())) {
            channelDao.insert(o);
        } else {
            channelDao.updateById(o);
        }
        return channelDao.selectById(o.getId());
    }

    @Override
    public Channel get(Serializable id) {
        return channelDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return channelDao.deleteById(id);
    }

    @Override
    public List<Channel> query(Channel o) {
        QueryWrapper<Channel> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return channelDao.selectList(wrapper);
    }

    @Override
    public List<Channel> all() {
        return query(null);
    }
}
