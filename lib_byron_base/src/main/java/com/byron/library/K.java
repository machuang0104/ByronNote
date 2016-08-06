/**
 * Project Name:ParkingPass
 * File Name:Key.java
 * Package Name:cn.com.parkingpass.common
 * Date:2015-7-17下午3:07:53
 * Copyright (c) 2015, machuang0104@126.com All Rights Reserved.
 *
 */

package com.byron.library;

/**

 */
public class K {
	
	public static final class cache{
		public static final String SHARED_NAME = "user_cache";
	}

	/**

	 */
	public static final class share {
	}

	/**

	 */
	public static final class file {
		public static final String ENCODE_TYPE = "UTF-8";
		public static final String ROOT_NAME = "/text";
		public static final String FILE_PATH = ROOT_NAME + "/file/";
		public static final String CACHE = ROOT_NAME + "/images/cache/";
		public static final String CRASHLOG = ROOT_NAME + "/crash/";
		public static final String SCREENSHOTS = ROOT_NAME
				+ "/images/screenshots/";
	}

	public static final class code {
		public static final int TRUE = 1;
		public static final int FALSE = 0;

	}

	public static final class intent {
		public static final String ADD_NEW = "add_new_record";
		public static final String TYPE_ID = "type_id";
		public static final String Detail_Data = "detail_data";
		public static final String Need_Refresh = "need_refresh";

	}

}