package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.medicine.dao.FeedbackDao;
import top.medicine.entity.Feedback;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Service
public class FeedbackService extends BaseService<Feedback> {

    @Autowired
    protected FeedbackDao userDao;

    @Override
    public List<Feedback> query(Feedback o) {
        QueryWrapper<Feedback> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return userDao.selectList(wrapper);
    }

    @Override
    public List<Feedback> all() {
        return query(null);
    }

    @Override
    public Feedback save(Feedback o) {
        if (Assert.isEmpty(o.getId())) {
            userDao.insert(o);
        } else {
            userDao.updateById(o);
        }
        return userDao.selectById(o.getId());
    }

    @Override
    public Feedback get(Serializable id) {
        return userDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return userDao.deleteById(id);
    }
}