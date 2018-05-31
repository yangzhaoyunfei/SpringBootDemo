package com.dudu.util;

import org.apache.commons.httpclient.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class AjaxObject extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        AjaxObject ajaxObj = new AjaxObject();
        ajaxObj.put("key1", "value1");
        System.out.println(ajaxObj);
    }

    /**
     * 请求处理成功,默认code
     */
	public AjaxObject() {
		put("code", 0);
	}

    /**
     * 请求处理失败,手动指定:500状态,默认消息
     *
     * @return
     */
	public static AjaxObject error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    /**
     * 请求处理失败,手动指定:500状态,msg
     *
     * @param msg
     * @return
     */
	public static AjaxObject error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    /**
     * 请求处理失败,手动指定code,msg
     *
     * @param code
     * @param msg
     * @return
     */
    public static AjaxObject error(int code, String msg) {
        AjaxObject r = new AjaxObject();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * 请求处理成功,手动指定msg
     *
     * @param msg
     * @return
     */
    public static AjaxObject ok(String msg) {
        AjaxObject r = new AjaxObject();
        r.put("msg", msg);
        return r;
    }


    /**
     * 请求处理成功,手动指定map
     *
     * @param map
     * @return
     */
	public static AjaxObject ok(Map<String, Object> map) {
		AjaxObject r = new AjaxObject();
		r.putAll(map);
        return r;
    }


    /**
     * 请求处理成功
     *
     * @return
     */
    public static AjaxObject ok() {
        return new AjaxObject();
    }

    public static AjaxObject apiError(String msg) {
        return error(1, msg);
    }


    /**
     * 请求处理成功,手动指定key-value对
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public AjaxObject put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 请求处理成功,手动指定data-value对
     *
     * @param data
     * @return
     */
    public AjaxObject data(Object data) {
        super.put("data", data);
		return this;
	}
}
