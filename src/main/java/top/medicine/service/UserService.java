package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.medicine.entity.User;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService extends BaseService<User> {

    @Override
    public List<User> query(User o) {
        QueryWrapper<User> wrapper = new QueryWrapper();
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
    public List<User> all() {
        return query(null);
    }

    @Override
    public User save(User o) {
        if (o == null) {
            return null;
        }
        if (Assert.isEmpty(o.getId())) {
            userDao.insert(o);
        } else {
            userDao.updateById(o);
        }
        return userDao.selectById(o.getId());
    }

    @Override
    public User get(Serializable id) {
        return userDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return userDao.deleteById(id);
    }

    public Map<Integer, String> getIdToNameMap(){
        Map<Integer, String> integerStringMap = new HashMap<>();
        List<User> users = this.all();
        for (User user : users){
            integerStringMap.put(user.getId(), user.getUserAccount());
        }
        return integerStringMap;
    }

    public Map<String, Object> findUserOne(Integer id) {
        Map<String, Object> map = new HashMap<>(4);

        // 查询单个用户基本信息
        User user = userDao.selectOne(new QueryWrapper<User>().eq("id", id));
        map.put("user", user);

        return map;
    }
}