package assistant.app.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
    public static Logger log = LoggerFactory.getLogger(CommonUtils.class);

    /**
	 * String转化为double
	 * 
	 * @param str
	 * @return 如果返回为-1，则表示传入的str参数有问题
	 */
	public static double String2Double(String str) {

		try {
			return ((str == null) || (str.length() < 1)) ? 0 : Double
					.valueOf(str);
		} catch (Exception e) {
			return -1;
		}
	}

	public static int String2Int(String str) {

		try {
			return ((str == null) || (str.length() < 1)) ? 0 : Integer
					.valueOf(str);
		} catch (Exception e) {
			return -1;
		}
	}

    /**
	 * String转化为Long
	 * 
	 * @param str
	 * @return
	 */
    public static Long String2Long(String str) {
    	try{
    		 return ((str == null) || (str.length() < 1)) ? null : Long.valueOf(str);
    	}catch(Exception e){
    		return -1l;
    	}
       
    }

    public static long String2long(String str) {
        // return (str == null || str.length() < 1) ? null : Long.valueOf(str);
        if (StringUtils.isEmpty(str)) {
            return -1L;
        }

        try {
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    public static long Long2long(Long l) {
        if (l == null) {
            return -1L;
        }

        return l.longValue();
    }

    public static long Date2long(Date date) {
        if (date == null) {
            return -1L;
        }

        return date.getTime();
    }

    /**
	 * String转化为Date，"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param dateStr
	 * @return
	 */
    public static Date String2Date(String dateStr) {
        Date get = null;

        try {
            get = ((dateStr == null) || (dateStr.length() < 1)) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(dateStr);
        } catch (ParseException e) {
            get = null;
			log.warn("String2Date", "日期解析出错:" + dateStr);
            e.printStackTrace();
        }

        return get;
    }

    /**
     * calculate the page count
     * 
     * @param totalNum
     * @param numOfPage
     * @return
     */
    public static long calculatePageCount(long totalNum, int numOfPage) {
        return (long) Math.ceil((double) totalNum / numOfPage);
    }

    public static long calculatePageCount(long totalNum, long numOfPage) {
        return (long) Math.ceil((double) totalNum / numOfPage);
    }

    public static String generateErrorMessage(Throwable t) {
        StringBuilder sb = new StringBuilder();

        if (t.getCause() != null) {
            sb.append(t.getCause().toString());

            if ((t.getCause().getStackTrace() != null) && (t.getCause().getStackTrace().length > 0)) {
                for (StackTraceElement stackTraceElement : t.getCause().getStackTrace()) {
                    sb.append(stackTraceElement.toString());
                }
            }
        }

        if (t.getStackTrace() != null) {
            for (StackTraceElement stackTraceElement : t.getStackTrace()) {
                sb.append(stackTraceElement.toString());
            }
        }

        return sb.toString();
    }

    public static final void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    public static String join(Collection<? extends Object> c,String seg){
    	if(c==null || c.size() == 0)
    		return null;
    	StringBuffer sb = new StringBuffer();
    	for(Object s:c){
    		sb.append(s).append(seg);
    	}

    	return sb.substring(0, sb.length()-seg.length());
    }
    public static boolean isNumber(String s){
    	try{
    		Long.parseLong(s);
    	}catch(Exception e){
    		return false;
    	}
    	return true;
    }
    public static boolean isEmpty(Collection<? extends Object> coll){
    	return coll == null || coll.size() == 0;
    }
    public static List<String> split(String src,String seg){
    	if(src == null)
    		return null;
    	List<String>  list = new ArrayList<String>();
    	String ss[] = src.split(seg);
    	for(String s: ss){
    		if(s != null && !s.trim().equals("")){
    			list.add(s);
    		}
    	}
    	return list;
    }

	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType)) {
			fileExt = ".jpg";
		} else if ("audio/mpeg".equals(contentType)) {
			fileExt = ".mp3";
		} else if ("audio/amr".equals(contentType)) {
			fileExt = ".amr";
		} else if ("video/mp4".equals(contentType)) {
			fileExt = ".mp4";
		} else if ("audio/mpeg4".equals(contentType)) {
			fileExt = ".mpeg4";
		}
		return fileExt;
	}

	/**
	 * URL编码
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
