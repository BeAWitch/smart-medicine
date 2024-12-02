package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.medicine.entity.PostReply;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class PostReplyService extends BaseService<PostReply>{
    @Override
    public PostReply save(PostReply o) {
        if (Assert.isEmpty(o.getId())) {
            postReplyDao.insert(o);
        } else {
            postReplyDao.updateById(o);
        }
        return postReplyDao.selectById(o.getId());
    }

    @Override
    public PostReply get(Serializable id) {
        return postReplyDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return postReplyDao.deleteById(id);
    }

    @Override
    public List<PostReply> query(PostReply o) {
        QueryWrapper<PostReply> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return postReplyDao.selectList(wrapper);
    }

    @Override
    public List<PostReply> all() {
        return query(null);
    }
}
