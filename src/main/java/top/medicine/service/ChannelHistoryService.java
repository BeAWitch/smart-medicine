package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.medicine.entity.ChannelHistory;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class ChannelHistoryService extends BaseService<ChannelHistory> {

    @Override
    public ChannelHistory save(ChannelHistory o) {
        if (Assert.isEmpty(o.getId())) {
            channelHistoryDao.insert(o);
        } else {
            channelHistoryDao.updateById(o);
        }
        return channelHistoryDao.selectById(o.getId());
    }

    @Override
    public ChannelHistory get(Serializable id) {
        return channelHistoryDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return channelHistoryDao.deleteById(id);
    }

    @Override
    public List<ChannelHistory> query(ChannelHistory o) {
        QueryWrapper<ChannelHistory> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return channelHistoryDao.selectList(wrapper);
    }

    @Override
    public List<ChannelHistory> all() {
        return query(null);
    }
}
