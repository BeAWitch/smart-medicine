package top.medicine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.medicine.entity.Illness;


@Repository
public interface IllnessDao extends BaseMapper<Illness> {

}
