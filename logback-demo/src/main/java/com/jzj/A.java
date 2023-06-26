package com.jzj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class A {
    private String name;
    private String address;

    public static void main(String[] args) {
        System.out.println(new A("张三", "成都").toString());

    }
}
