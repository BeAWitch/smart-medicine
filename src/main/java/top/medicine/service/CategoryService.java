package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.medicine.entity.Article;
import top.medicine.entity.Category;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService extends BaseService<Category> {

    @Override
    public List<Category> query(Category o) {
        QueryWrapper<Category> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return categoryDao.selectList(wrapper);
    }

    @Override
    public List<Category> all() {
        return query(null);
    }

    @Override
    public Category save(Category o) {
        if (Assert.isEmpty(o.getId())) {
            categoryDao.insert(o);
        } else {
            categoryDao.updateById(o);
        }
        return categoryDao.selectById(o.getId());
    }

    @Override
    public Category get(Serializable id) {
        return categoryDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return categoryDao.deleteById(id);
    }

    public Map<String, Integer> getNameToIdMap(){
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        List<Category> categories = this.all();
        for (Category category : categories){
            stringIntegerMap.put(category.getCategoryName(), category.getId());
        }
        return stringIntegerMap;
    }

    public Map<Integer, String> getIdToNameMap(){
        Map<Integer, String> integerStringMap = new HashMap<>();
        List<Category> categories = this.all();
        for (Category category : categories){
            integerStringMap.put(category.getId(), category.getCategoryName());
        }
        return integerStringMap;
    }

    public Map<String, Object> getCategoryList(String categoryName, Integer page) {

        List<Category> categories = null;
        Map<String, Object> map = new HashMap<>(4);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (Assert.notEmpty(categoryName)) {
            queryWrapper.like("category_name", categoryName);
        }
        queryWrapper.last("limit " + (page - 1) * 9 + "," + page * 9);
        categories = categoryDao.selectList(queryWrapper);

        map.put("categories", categories);
        map.put("size", categories.size() < 9 ? 1 : categories.size() / 9 + 1);
        return map;
    }
}
