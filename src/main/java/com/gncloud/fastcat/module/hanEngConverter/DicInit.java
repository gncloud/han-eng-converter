package com.gncloud.fastcat.module.hanEngConverter;

import net.sf.extjwnl.dictionary.Dictionary;

import java.io.FileInputStream;

public class DicInit {
    private static DicInit instance;
    private static final String DEFAULT_DIC_CONF = "./dic/file_properties.xml";
    public static FileInputStream dicInputStream;
    public static Dictionary dictionary;

    private DicInit() {
        try {
            dicInputStream = new FileInputStream(DEFAULT_DIC_CONF);
            dictionary = Dictionary.getInstance(dicInputStream);
        } catch (Exception ignore) {}
    }

    public static synchronized DicInit getInstance() {
        if (instance == null)
            instance = new DicInit();
        return instance;
    }
}

