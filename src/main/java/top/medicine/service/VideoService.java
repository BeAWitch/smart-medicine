package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.medicine.entity.Video;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class VideoService extends BaseService<Video>{

    @Override
    public Video save(Video o) {
        if (o == null) {
            return null;
        }
        if (Assert.isEmpty(o.getId())) {
            videoDao.insert(o);
        } else {
            videoDao.updateById(o);
        }
        return videoDao.selectById(o.getId());
    }

    @Override
    public Video get(Serializable id) {
        return videoDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return videoDao.deleteById(id);
    }

    @Override
    public List<Video> query(Video o) {
        QueryWrapper<Video> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return videoDao.selectList(wrapper);
    }

    @Override
    public List<Video> all() {
        return query(null);
    }
}
