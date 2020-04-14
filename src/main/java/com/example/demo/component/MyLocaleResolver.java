package com.example.demo.component;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 可以在连接上携带区域信息   这里是创建一个自己的区域信息解析器（在Controller中进行使用）
 */
@Configuration
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {  //解析区域信息
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault();  //如果没有用系统默认的
        if(!StringUtils.isEmpty(l)){
            //如果有就自己拼出来一个
            String[] split = l.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
