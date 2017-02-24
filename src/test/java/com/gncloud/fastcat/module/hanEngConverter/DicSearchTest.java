package com.gncloud.fastcat.module.hanEngConverter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by gncloud on 2017-02-22.
 */
public class DicSearchTest  {
    @Test
    public void searchDic() throws Exception{

        WordnetSearcher ds = new WordnetSearcher();
//        assertEquals(true , ds.search("chanel"));
        assertEquals(true , ds.search("apple"));
        assertEquals(false, ds.search("asdfasdf"));
    }
}
