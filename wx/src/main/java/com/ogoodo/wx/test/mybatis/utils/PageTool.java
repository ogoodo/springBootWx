package com.ogoodo.wx.test.mybatis.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;

public class PageTool {

	/*
	 *  根据某个对象的名称和方法去执行该方法
	 * 
	 */
	public static <T> List<T> execute(Object object, String methodName, Class<? extends Object> cls) {
        // Class<? extends Object> clazz = object.getClass();
        List<T> list = null;
		try {
			Method m1 = object.getClass().getDeclaredMethod(methodName, cls);
			@SuppressWarnings("unchecked")
			List<T> list2 = (List<T>) m1.invoke(object, new Object[]{null} );
			list = list2;
			System.out.println(": ");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static <T> Map<String, Object> select(Object object, Object example, int pageNum, int pageSize, String msg) {
        List<T> list = null;
        String methodName = "selectByExample";
		try {
			Method m1 = object.getClass().getDeclaredMethod(methodName, example.getClass());
			PageHelper.startPage(pageNum, pageSize);
			@SuppressWarnings("unchecked")
//			List<T> list2 = (List<T>) m1.invoke(object, new Object[]{null} );
			List<T> list2 = (List<T>) m1.invoke(object, new Object[]{example} );
			list = list2;
			System.out.println(": ");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

        Map<String, Object> map = new HashMap<String, Object>();
		PageBean<T> dataWithPage = new PageBean<T>(list);
		map.put("data", dataWithPage);
        map.put("code", "10001");
        map.put("msg", msg);
		return map;
	}


	public static <T> Map<String, Object> select2(Object object, String methodName, Class<? extends Object> cls, Object example, int pageNum, int pageSize, String msg) {
        List<T> list = null;
		try {
			Method m1 = object.getClass().getDeclaredMethod(methodName, cls);
			PageHelper.offsetPage(pageNum, pageSize);
			@SuppressWarnings("unchecked")
//			List<T> list2 = (List<T>) m1.invoke(object, new Object[]{null} );
			List<T> list2 = (List<T>) m1.invoke(object, new Object[]{example} );
			list = list2;
			System.out.println(": ");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

        Map<String, Object> map = new HashMap<String, Object>();
		PageBean<T> dataWithPage = new PageBean<T>(list);
		map.put("data", dataWithPage);
        map.put("code", "10001");
        map.put("msg", msg);
		return map;
	}

//	Class<?>[] paramTypes = new Class[1];
//	paramTypes[0] = cls; // URoleExample.class;
//	m1 = clazz.getDeclaredMethod(methodName, paramTypes);
//	List<URole> list = (List<URole>) m1.invoke(object, new Object[]{null} );
}
