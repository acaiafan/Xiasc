package com.ssm.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.StringUtils;
import com.ssm.bean.CheckRule;
import com.ssm.bean.NotEmpty;

public class BeanUtil {
	/**
	 * 校验格式是否正确，如果@CheckValue() 没有参数，则校验是否为空
	 * 例子：@CheckValue("^[A-Za-z0-9_-]{1,32}$") 校验数字、字母、下划线1到32位 ： @CheckValue()
	 * 校验字段是否为空
	 * 
	 * @param obj
	 * @return 如果返回null表示：校验通过
	 */
	public static String checkValue(Object obj) {
		return parseAnnotation(CheckRule.class, obj, true);
	}

	/**
	 * 校验是否为空
	 * 
	 * @param obj
	 * @return 如果返回null表示：校验通过
	 */
	public static String checkEmpty(Object obj) {
		String s1 = parseAnnotation(NotEmpty.class, obj, true);
		String s2 = parseAnnotation(CheckRule.class, obj, true);
		if(s1==null && s2==null){
			return null;
		}else{
			return s1+s2;
		}
		
	}

	private static String parseAnnotation(Class<? extends Annotation> aClazz, Object obj, boolean hasParent) {
		StringBuilder sb = new StringBuilder();
		boolean flag = false;

		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();

		Field[] bothField = fields;
		if (hasParent) {
			Class<?> superClazz = clazz.getSuperclass();
			Field[] superFields = superClazz.getDeclaredFields();
			// bothField = (Field[]) ArrayUtils.addAll(fields, superFields);
		}

		for (Field field : bothField) {
			Annotation annotation = field.getAnnotation(aClazz);
			System.out.println(field.getName());
			if (annotation == null){
				
				System.out.println("notEmpty");
				continue;
			}
				
			field.setAccessible(true);

			try {
				if (annotation instanceof CheckRule) {
					System.out.println("checkRule");
					CheckRule cv = (CheckRule) annotation;
					String regex = cv.value();
					if (regex == null) {
						// 输入的正则表达式为空，所以不做校验
						// continue;
						// NotEmpty ne = (NotEmpty)annotation;
						Object oValue = field.get(obj);
						if (oValue == null) {
							sb.append("字段" + field.getName() + "不能为null|");
							flag = true;
						} else {
							if (oValue instanceof String) {
								String value = (String) oValue;
								if ("".equals(value.trim())) {
									sb.append("字段" + field.getName() + "不能为空|");
									flag = true;
								}
							} else {
								// logger.info("字段" + field.getName() +
								// "不是字符串，不能判断是否为空");
							}
						}
					} else {
						Pattern pattern = Pattern.compile(regex);
						String value = (String) field.get(obj);
						Matcher m = pattern.matcher(value);
						if (!m.matches()) {
							sb.append("字段" + field.getName() + "格式错误|");
							flag = true;
						}
					}

				} else if (annotation instanceof NotEmpty) {
					Object oValue = field.get(obj);
					if (oValue == null) {
						sb.append("字段" + field.getName() + "不能为null|");
						flag = true;
					} else {
						if (oValue instanceof String) {
							String value = (String) oValue;
							if ("".equals(value.trim())) {
								sb.append("字段" + field.getName() + "不能为空|");
								flag = true;
							}
						} else {
							// logger.info("字段" + field.getName() +
							// "不是字符串，不能判断是否为空");
						}
					}

				}
			} catch (Exception e) {
				sb.append(e.getMessage());
				flag = true;
				// logger.error("解析注解出错：", e);
				// e.printStackTrace();
			}

		}

		if (flag) {
			return sb.toString();
		} else {
			return null;
		}
	}
}
