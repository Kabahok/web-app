package org.egoravdeev.constants;

public class StringConsts {
//  methods
    public static String METHOD_GET = "GET";
    public static String METHOD_POST = "POST";
    public static String METHOD_DELETE = "DELETE";

//  content type
    public static String APLICATION_JSON = "aplication/json";

//  map api
    public static String API_POSTS = "/api/posts";
    public static String API_POSTS_ID = "/api/posts/\\d+";

//  messages
    public static String SUCCESS_DELETE_POST = "Пост успешно удален";
    public static String NOT_FOUND_POST = "Пост не найден";
    public static String NOT_DELETE_POST = "Пост не найден, поэтому не может быть удален";
    public static String NOT_FOUND_POST_WITH_ID = "Пост с таким id не найден";


}
