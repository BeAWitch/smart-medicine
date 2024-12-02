package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.medicine.entity.Post;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class PostService extends BaseService<Post>{
    @Override
    public Post save(Post o) {
        if (Assert.isEmpty(o.getId())) {
            postDao.insert(o);
        } else {
            postDao.updateById(o);
        }
        return postDao.selectById(o.getId());
    }

    @Override
    public Post get(Serializable id) {
        return postDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return postDao.deleteById(id);
    }

    @Override
    public List<Post> query(Post o) {
        QueryWrapper<Post> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return postDao.selectList(wrapper);
    }

    @Override
    public List<Post> all() {
        return query(null);
    }
}