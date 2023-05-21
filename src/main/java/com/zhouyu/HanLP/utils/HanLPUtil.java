package com.zhouyu.HanLP.utils;

import com.hankcs.hanlp.restful.HanLPClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HanLPUtil {
    static HanLPClient HanLP = new HanLPClient("https://www.hanlp.com/api", "填自己的token", "zh",1000);
    //分词
    public static String[] splitText(String text){
        //分词
        List<List<String>> tokenize = new ArrayList<>();
        try{
             tokenize = HanLP.tokenize(text);
        }catch (IOException e){
            e.printStackTrace();
        }
        //统计word数
        int totalWords = 0;
        for (List<String> sentence : tokenize) {
            totalWords += sentence.size();
        }
        //放在一维数组中
        String[] allWords = new String[totalWords];
        int index = 0;
        for(List<String> sentence : tokenize) {
            for(String word : sentence) {
                allWords[index++] = word;
            }
        }
        return allWords;
    }
    //文本风格转换
    public static String styleTransfer(String text) throws IOException {
        String govDoc = HanLP.textStyleTransfer(text,
                "gov_doc");
        System.out.println(govDoc);
        return govDoc;
    }

    public static void main(String[] args) throws IOException {
        styleTransfer("本文主要介绍了一种基于推荐算法的网上购物系统的设计与实现。该系统主要包括了三个模块：商品模块，仓储模块，推" +
                "荐模块和用户模块。用户模块主要负责用户的注册、登录和个人信息的管理；商品模块主要负责商品相关的参数管理，以及商品上架和管理" +
                "功能；");
    }

}
