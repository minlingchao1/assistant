/**
 * @author mlc
 * @date 2015年3月24日 下午11:15:03
 * @version 1.0
 */
package controllers.custom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import assistant.app.common.util.DateUtils;

public class test {

    public static void main(String[] args) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        String strdate = "20150302";
        try {
            Date date = sf.parse(strdate);
            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(DateUtils.formatLongToDate(20150302));
    }

}
