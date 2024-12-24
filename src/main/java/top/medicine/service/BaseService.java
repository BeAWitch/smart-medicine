package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.medicine.dao.*;
import top.medicine.entity.Category;
import top.medicine.entity.IllnessKind;
import top.medicine.utils.Assert;
import top.medicine.utils.BeanUtil;
import top.medicine.utils.VariableNameUtils;

import java.util.List;
import java.util.Map;

@Service
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected HistoryDao historyDao;

    @Autowired
    protected IllnessDao illnessDao;

    @Autowired
    protected IllnessKindDao illnessKindDao;

    @Autowired
    protected IllnessMedicineDao illnessMedicineDao;

    @Autowired
    protected MedicineDao medicineDao;

    @Autowired
    protected PageviewDao pageviewDao;

    @Autowired
    protected VideoDao videoDao;

    @Autowired
    protected ArticleDao articleDao;

    @Autowired
    protected CategoryDao categoryDao;
}
