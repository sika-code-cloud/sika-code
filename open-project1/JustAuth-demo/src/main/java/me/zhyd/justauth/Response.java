package me.zhyd.justauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/6/28 0:35
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private int code;
    private String msg;

    public static Response error(String msg) {
        return new Response(500, msg);
    }

    public static Response success(String msg) {
        return new Response(200, msg);
    }
}
