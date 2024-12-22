package top.medicine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.medicine.entity.Medicine;

import java.util.List;
import java.util.Map;


@Repository
public interface MedicineDao extends BaseMapper<Medicine> {
}
