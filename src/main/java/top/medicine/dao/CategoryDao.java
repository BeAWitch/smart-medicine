package top.medicine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.medicine.entity.Category;

@Repository
public interface CategoryDao extends BaseMapper<Category> {
}
