package cn.itcast.elec.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**@description 通用操作接口
 * @author chauncey
 * @param <T>
 */
public interface ICommonDao<T> {

	/**
	 * 保存
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 更新
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 根据指定id查找记录
	 * @param id
	 * @return
	 */
	T findObjectByID(Serializable id);

	/**
	 * 批量删除
	 * @param ids
	 */
	void deleteBojectByIDs(Serializable... ids);

	/**
	 * 批量删除
	 * @param list
	 */
	void deleteObjectByCollection(List<T> list);

	/**
	 * 根据指定条件、参数查找并指定排序结果(不分页)
	 * @param condition
	 * @param params
	 * @param orderby
	 * @return 返回List集合
	 */
	List<T> findCollectionByConditionNoPage(String condition,
			Object[] params, Map<String, String> orderby);

}
