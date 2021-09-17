package com.liang.common;

/**
 * @author: Mr.Liang
 * @date: 2021/4/20 11:57
 * @Version: 1.0
 */
public class TestS {


    public static void main(String[] args) {
        char[] ch = {'a', 'b', 'c'};
        String str = new String("hahaha");
        new TestS().change(str, ch);
        System.out.println(str);
        System.out.println(ch);
    }

    private void change(String str, char[] ch) {
        str = "xixixi";
        ch[0] = 'G';
        ch[1] = 'K';
        ch[2] = 'D';
        System.out.println(str);
        System.out.println(ch);

    }
}
