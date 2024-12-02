package top.medicine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.medicine.entity.Article;

@Repository
public interface ArticleDao extends BaseMapper<Article> {
}
