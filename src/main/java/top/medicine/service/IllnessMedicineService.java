package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.medicine.dao.IllnessMedicineDao;
import top.medicine.entity.IllnessMedicine;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Service
public class IllnessMedicineService extends BaseService<IllnessMedicine> {

    @Autowired
    protected IllnessMedicineDao illnessMedicineDao;

    @Override
    public List<IllnessMedicine> query(IllnessMedicine o) {
        QueryWrapper<IllnessMedicine> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return illnessMedicineDao.selectList(wrapper);
    }

    @Override
    public List<IllnessMedicine> all() {
        return query(null);
    }

    @Override
    public IllnessMedicine save(IllnessMedicine o) {
        if (Assert.isEmpty(o.getId())) {
            illnessMedicineDao.insert(o);
        } else {
            illnessMedicineDao.updateById(o);
        }
        return illnessMedicineDao.selectById(o.getId());
    }

    @Override
    public IllnessMedicine get(Serializable id) {
        return illnessMedicineDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return illnessMedicineDao.deleteById(id);
    }
}