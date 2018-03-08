package com.tao.parser;

import net.sf.json.JSONObject;

/**
 * Author: Douglass
 * Date: 2016/3/28
 * E-mail: xuetao_ni@163.com
 */
public interface Parser<T> {
    public T build(JSONObject object);
}
