package com.tao.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Douglass
 * Date: 2016/3/28
 * E-mail: xuetao_ni@163.com
 */
public class ParserMap {
    private static Map<Integer, Parser> parserMap = new HashMap<>();

    public static void put(int key, Parser value){
        parserMap.put(key, value);
    }

    public static Parser get(int key){
        return parserMap.get(key);
    }

    public static void remove(int key){
        parserMap.remove(key);
    }
}
