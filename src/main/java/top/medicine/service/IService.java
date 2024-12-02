package top.medicine.service;

import top.medicine.entity.IllnessKind;

import java.io.Serializable;
import java.util.List;


public interface IService<T> {

    
    T save(T t);

    
    T get(Serializable id);

    
    int delete(Serializable id);

    
    List<T> query(T o);

    
    List<T> all();

}
