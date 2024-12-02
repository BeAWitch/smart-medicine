package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.medicine.dao.ArticleDao;
import top.medicine.entity.Article;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService extends BaseService<Article>{

    @Override
    public List<Article> query(Article o) {
        QueryWrapper<Article> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return articleDao.selectList(wrapper);
    }

    @Override
    public List<Article> all() {
        return query(null);
    }

    @Override
    public Article save(Article o) {
        if (Assert.isEmpty(o.getId())) {
            articleDao.insert(o);
        } else {
            articleDao.updateById(o);
        }
        return articleDao.selectById(o.getId());
    }

    @Override
    public Article get(Serializable id) {
        return articleDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return articleDao.deleteById(id);
    }

    public Map<String, Object> getArticleList(Integer createUser, String title, Integer categoryId, Integer page) {

        List<Article> articles = null;
        Map<String, Object> map = new HashMap<>(4);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (Assert.notEmpty(createUser)) {
            queryWrapper.eq("create_user", createUser);
        }
        if (Assert.notEmpty(title)) {
            queryWrapper.like("title", title).or().like("content", title);
        }
        if (Assert.notEmpty(categoryId)) {
            queryWrapper.eq("category_id", categoryId);
        }
        queryWrapper.last("limit " + (page - 1) * 9 + "," + page * 9);
        articles = articleDao.selectList(queryWrapper);

        map.put("articles", articles);
        map.put("size", articles.size() < 9 ? 1 : articles.size() / 9 + 1);
        return map;
    }
}
