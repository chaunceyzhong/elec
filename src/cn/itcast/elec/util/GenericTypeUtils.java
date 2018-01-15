package cn.itcast.elec.util;

import java.lang.reflect.ParameterizedType;

/**
 * @description 泛型转换，目的是将对应的泛型，转化成真实的对象类型
 */
public class GenericTypeUtils {

	/**泛型转换*/
	public static Class getGenericSuperClass(Class entity) {
		//此时type表示：BaseAction<cn.itcast.elec.domain.ElecText>
		ParameterizedType type = (ParameterizedType)entity.getGenericSuperclass();
		//entityClass:cn.itcast.elec.domain.ElecText
		Class entityClass = (Class) type.getActualTypeArguments()[0];
		return entityClass;
	}

}
