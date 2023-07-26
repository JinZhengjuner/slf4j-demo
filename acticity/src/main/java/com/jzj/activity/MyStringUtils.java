package com.jzj.activity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MyStringUtils {
    public boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public String trim(String str) {
        return str == null ? null : str.trim();
    }
}
