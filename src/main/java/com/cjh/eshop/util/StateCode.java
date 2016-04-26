package com.cjh.eshop.util;

/**
 * API接口返回结果的状态码
 * @author 陈建杭
 *
 */
public class StateCode {
	/** 操作成功 */
	public static final int SUCCESS = 0;
	/** 操作失败 */
	public static final int ERROR = 1;
	/** 没有权限 */
	public static final int CODE_NO_AUTHORITY = 1001;
	public static final int CODE_ID_NOT_EXIT = 1002;
	public static final int CODE_PARAM_ERROR = 1003; // 参数错误
	// TODO 其他错误码

	public static String getMessage(int stateCode) {
		switch (stateCode) {
		case SUCCESS:
			return "operation success";
		case ERROR:
			return "operation failure";
		case CODE_NO_AUTHORITY:
			return "you do not have permission";
		case CODE_ID_NOT_EXIT:
			return "id is no exit不存在";
		default:
			return "state code is no found";
		}
	}
}
