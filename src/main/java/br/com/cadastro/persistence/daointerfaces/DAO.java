package br.com.cadastro.persistence.daointerfaces;

import br.com.cadastro.persistence.model.enums.Condition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface DAO<T> extends Serializable {

    T save(T entity);

    T update(T entity);

    T findByID(Serializable id);

    void remove(T entity);

    List<T> listAll();

    List<T> findByHQLQuery(String queryId, int maxResults);

    List<T> findByHQLQuery(String queryId, List<Object> params, int maxResults);

    List<T> findByAttributes(Map<String, Object> mapAttributesValue, List<Condition> conditions);
}
