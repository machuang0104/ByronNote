package com.byron.library.anno;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.byron.library.log.LogUtil;

/**
 * @author  machuang
 *
 */
public class Injector {
	private final static String TAG = "Injector";
	private static final String METHOD_SET_CONTENTVIEW = "setContentView";

	public static void injectLayout(FragmentActivity ac) {
		Class<?> classType = ac.getClass();
		InjectLayout layout = classType.getAnnotation(InjectLayout.class);
		if (layout == null) {
			LogUtil.e(TAG, "layout is null -->"
					+ classType.getClass().getSimpleName());
		}
		if (layout != null) {
			int layoutId = layout.id();
			try {
				Method method = classType.getMethod(METHOD_SET_CONTENTVIEW,
						int.class);
				method.setAccessible(true);
				method.invoke(ac, layoutId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void injectViews(FragmentActivity ac) {
		Class<?> classType = ac.getClass();
		Field[] fields = classType.getDeclaredFields();
		int fl = fields.length;
		Field field;
		int id;
		for (int i = 0; i < fl; i++) {
			field = fields[i];
			if (field.isAnnotationPresent(InjectView.class)) {
				InjectView anno = field.getAnnotation(InjectView.class);
				id = anno.id();
				if (id < 0) {
					LogUtil.e(TAG, "anno.id()<0");
					continue;
				}
				View v = ac.findViewById(id);
				if (v != null) {
					field.setAccessible(true);
					try {
						field.set(ac, v);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				} else {
					LogUtil.e(TAG, "anno failure Activity name : "
							+ ac.getClass().getSimpleName());
				}
			}
		}
	}

}
