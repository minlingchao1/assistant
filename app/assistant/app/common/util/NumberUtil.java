package assistant.app.common.util;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberUtil {

    public static final Logger log = LoggerFactory.getLogger(NumberUtil.class);

    public static final String TAG = "NumberUtil";

    public static final Long DEFAULT_LONG = 0L;

    public static final Double DEFAULT_DOUBLE = 0d;

    public static final Integer DEFAULT_ZERO = 0;

    public static final Integer DEFAULT_ONE = 1;

    public static final Integer NEVER_START = Integer.MIN_VALUE;

    public static final Integer NONE_EXIST = -64;

    public static final Long DEFAULT_LONG_ONE = 1L;

    public static final NumberFormat percentFormatter = NumberFormat.getPercentInstance();

    public static final NumberFormat doubleFormatter = new DecimalFormat("######0.00");

    public static final NumberFormat double4Formatter = new DecimalFormat("######0.0000");

    public static final Long[] EMPTY_LONG_ARRAY = new Long[] {};

    public static final String[] EMPTY_STRING_ARRAY = new String[] {};

    public static String getConversionFormat(double conversion) {
        percentFormatter.setMinimumFractionDigits(2);
        return percentFormatter.format(conversion);
    }

    public static double quotaIntToDouble(Integer src) {
        if (src == null) {
            return -1d;
        }
        return src.doubleValue() / 100d;
    }

    public static Integer quotaIntToDouble(double src) {
        if (src == -1d) {
            return -1;
        }

        return Integer.valueOf(((int) (src * 100d)));
    }

    public static int getIntFromPrice(String src) {
        return (int) (parserDouble(src, 0d) * 100d);

    }

    public static double scoreFormat(double score) {
        return (double) (Math.round(score * 5 * 10) / 10.0);
    }

    public static double doubleFormatter(double data) {
        return (double) (Math.round(data * 100) / 100.0);
    }

    public static double double4Formatter(double data) {
        return (double) (Math.round(data * 10000) / 10000.0);
    }

    public static long hashStringToLong(String src) {
        if (StringUtils.isEmpty(src)) {
            return 0L;
        }

        long h = 0L;
        int len = src.length();

        for (int i = 0; i < len; i++) {
            h = 31 * h + src.charAt(i);
        }
        return h;

    }

    public static void add(Object a, Object b, Field[] fields) {
        try {
            for (Field field : fields) {
                Object obj = field.get(a);
                if (!(obj instanceof Number)) {
                    continue;
                }
                if (obj instanceof Integer) {
                    Integer numA = (Integer) obj;
                    Integer numB = (Integer) field.get(b);
                    field.set(a, numA + numB);
                }
                if (obj instanceof Long) {
                    Long numA = (Long) obj;
                    Long numB = (Long) field.get(b);
                    field.set(a, numA + numB);
                }
                if (obj instanceof Double) {
                    Double numA = (Double) obj;
                    Double numB = (Double) field.get(b);
                    field.set(a, numA + numB);
                }
                if (obj instanceof Float) {
                    Float numA = (Float) obj;
                    Float numB = (Float) field.get(b);
                    field.set(a, numA + numB);
                }
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    public static @interface NoAdd {

    }

    public static <T> void addAll(Addable<T> a1, T a2) {
        a1.add(a2);
    }

    public static <T> T first(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    public static <T> List<T> first(List<T> list, int max) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() > max) {
            return list.subList(0, max);
        } else {
            return list;
        }
    }

    public static <T extends Addable> T sum(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        int num = list.size();
        if (num == 1) {
            return list.get(0);
        }

        T first = list.get(0);
        T res = null;
        try {
            res = (T) first.clone();
            for (int i = 1; i < num; i++) {
                res.add(list.get(i));
            }
        } catch (CloneNotSupportedException e) {
            log.warn(e.getMessage(), e);
        }

        return res;
    }

    public static interface Addable<T> extends Cloneable {
        public void add(T t);

        public Addable<T> clone() throws CloneNotSupportedException;

        public int getSumNum();

        public int sumNumPlus();
    }

    public static List<List<Long>> splitToSubList(Collection<Long> coll, int subListSize) {
        if (CollectionUtils.isEmpty(coll)) {
            return ListUtils.EMPTY_LIST;
        }

        List<List<Long>> finalResult = new ArrayList<List<Long>>();
        List<Long> currList = new ArrayList<Long>();
        finalResult.add(currList);
        for (Long l : coll) {
            currList.add(l);
            if (currList.size() < subListSize) {

            } else {
                currList = new ArrayList<Long>();
                finalResult.add(currList);
            }

        }

        return finalResult;
    }

    public static final int parserInt(Object obj, int defaultValue) {
        try {
            if (obj == null) {
                return defaultValue;
            }

            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            if (obj instanceof Number) {
                return ((Number) obj).intValue();
            }
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static final Long parserLong(String obj, Long defaultValue) {
        try {
            return Long.valueOf(obj);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean isNullOrZero(Long id) {
        return id == null || id == 0L;
    }

    public static final double parserDouble(String obj, double defaultValue) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @SuppressWarnings("unchecked")
    public static Set<Long> splitLongSet(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return SetUtils.EMPTY_SET;
        }

        Set<Long> res = new HashSet<Long>();
        String[] split = ids.split(",");
        for (String string : split) {
            res.add(new Long(string));
        }
        return res;
    }

    static Random rand = new Random();

}
