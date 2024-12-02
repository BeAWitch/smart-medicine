package top.medicine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.medicine.entity.User;


@Repository
public interface UserDao extends BaseMapper<User> {

}
