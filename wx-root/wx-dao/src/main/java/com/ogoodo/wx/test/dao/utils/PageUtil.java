package com.ogoodo.wx.test.dao.utils;

//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PageUtil<T> {

	static public <T> Map<String,Object> Change(List<T> list, String msg) {
        Map<String, Object> map=new HashMap<String, Object>();
		PageBean<T> dataWithPage = new PageBean<T>(list);
		map.put("data", dataWithPage);
        map.put("code", "10001");
        map.put("msg", msg);
        // logger.debug("查找结果" + list);
        return map;
	}

//	public static  List<T> execute(Object object,String methodName, Class<? extends Object> cls) {
//        Class<? extends Object> clazz = object.getClass(); 
//        Method m1;
//		try {
//			Class<?>[] paramTypes = new Class[1];
//			paramTypes[0] = cls; // URoleExample.class;
//			m1 = clazz.getDeclaredMethod(methodName, paramTypes);
//			List<T> list = (List<T>) m1.invoke(object, new Object[]{null} );
//			// PageBean<Object> dataWithPage = (PageBean<Object>) m1.invoke(object, new Object[]{null}));
//			int x = 3;
//			return list;
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//	
//	}

//	static public <T> Map<String,Object> GetResponse( Class cls) {
//        Map<String, Object> map=new HashMap<String, Object>();
//        List<T> list = r1.();
//		PageBean<T> dataWithPage = new PageBean<T>(list);
//		map.put("data", dataWithPage);
//        map.put("code", "10001");
//        map.put("msg", "请求参数校验成功！");
//        // logger.debug("查找结果" + list);
//        return map;
//	}

//	static public Map<String,Object> Change2(List list) {
//        Map<String,Object> map=new HashMap<String,Object>();
//		PageBean dataWithPage = new PageBean(list);
//		map.put("data", dataWithPage);
//        map.put("code", "10001");
//        map.put("msg", "请求参数校验成功！！！");
//        return map;
//	}
}
