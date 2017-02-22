package com.gncloud.fastcat.module.hanEngConverter;

import net.sf.extjwnl.dictionary.Dictionary;

import java.io.FileInputStream;

/**
 * Created by gncloud on 2017-02-22.
 *
 */
/**
 * initialization on demand holder idiom 기법의 싱글톤
 * jvm 의 classLoader의 매커니즘과 class Load 시점을 이용하여 내부 클래스를 생성함으로서 thread간의 동기화 문제 해결
 */
public class DicInit { // 싱글톤 사전 초기화 클래스
    private static final String DEFAULT_DIC_CONF = "./dic/file_properties.xml";
    public static FileInputStream dicInputStream;
    public static Dictionary dictionary;

    private DicInit(){}
    private static class SingleTon {
        private static final DicInit instance = new DicInit();
    }
    public static DicInit getInstance(){
        try{
            dicInputStream = new FileInputStream(DEFAULT_DIC_CONF);
            dictionary = Dictionary.getInstance(dicInputStream);
        } catch (Exception ignore){}
        return SingleTon.instance;
    }
}
