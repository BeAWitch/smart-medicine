package top.medicine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.medicine.dao.*;

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
}
