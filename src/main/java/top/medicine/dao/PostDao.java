package top.medicine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.medicine.entity.Post;

@Repository
public interface PostDao extends BaseMapper<Post> {
}
