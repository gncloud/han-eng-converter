package com.gncloud.fastcat.module.hanEngConverter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by gncloud on 2017-02-23.
 */
public class CustomDicUpdateTest {
    @Test
    public void keywordInsertTest(){
        CustomDicUpdate cdu = new CustomDicUpdate("./dic/custom.noun.txt");
        String keyword = "Malta’s largest internet company has announced the opening of applications for internships aimed at university students.\n" +
                "Thirteen paid positions are up for grabs as part of the Betsson Group's Betsson Academy, which is now in its third year. \n" +
                "This opportunity will allow the preferred candidates to spend a summer learning and working within a multinational company with a view to securing a place on the graduate scheme.\n" +
                "Betsson Group’s operational headquarters in Malta is home to almost 1,000 employees from 43 nationalities and it’s here that interns get the chance to apply academic theory to real-life Betsson challenges and make contributions.\n" +
                "Applications opened on February 13 and will close on March 13. After this date, all applications will be assessed and successful candidates will be invited to Betsson Group offices for further assessment on March 22 and 29.\n" +
                "Please apply at www.betssongroup.com where all internship positions will appear in the jobs page under the Betsson Academy icon.";
        cdu.insertKeyword(keyword);
    }
}
