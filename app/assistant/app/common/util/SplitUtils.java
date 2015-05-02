
package assistant.app.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;

public class SplitUtils {

    public static List<List<Long>> splitToSubLongList(Collection<Long> coll, int subListSize) {
        if (CollectionUtils.isEmpty(coll)) {
            return ListUtils.EMPTY_LIST;
        }

        List<List<Long>> finalResult = new ArrayList<List<Long>>();
        List<Long> currList = new ArrayList<Long>();
        finalResult.add(currList);

        int count = coll.size();

        for (Long l : coll) {
            currList.add(l);
            count--;

            if (currList.size() >= subListSize && count > 0) {
                currList = new ArrayList<Long>();
                finalResult.add(currList);
            }
        }

        return finalResult;

    }

    public static List<List<String>> splitToSubStrList(Collection<String> coll, int subListSize) {
        if (CollectionUtils.isEmpty(coll)) {
            return ListUtils.EMPTY_LIST;
        }

        List<List<String>> finalResult = new ArrayList<List<String>>();
        List<String> currList = new ArrayList<String>();
        finalResult.add(currList);
        int count = coll.size();

        for (String l : coll) {
            currList.add(l);
            count--;

            if (currList.size() >= subListSize && count > 0) {
                currList = new ArrayList<String>();
                finalResult.add(currList);
            }

        }

        return finalResult;
    }

    public static <T> List<List<T>> splitToSubList(Collection<T> coll, int subListSize) {
        if (CollectionUtils.isEmpty(coll)) {
            return ListUtils.EMPTY_LIST;
        }

        List<List<T>> finalResult = new ArrayList<List<T>>();
        List<T> currList = new ArrayList<T>();
        finalResult.add(currList);
        int count = coll.size();

        for (T l : coll) {
            currList.add(l);
            count--;

            if (currList.size() >= subListSize && count > 0) {
                currList = new ArrayList<T>();
                finalResult.add(currList);
            }

        }

        return finalResult;
    }

    public static List<Long> stringIdsToLong(String[] arr) {
        if (ArrayUtils.isEmpty(arr)) {
            return ListUtils.EMPTY_LIST;
        }

        List<Long> res = new ArrayList<Long>();
        for (String string : arr) {
            res.add(Long.valueOf(string));
        }
        return res;
    }

    public static List<Long> stringIdsToLong(List<String> arr) {
        if (CollectionUtils.isEmpty(arr)) {
            return ListUtils.EMPTY_LIST;
        }

        List<Long> res = new ArrayList<Long>();
        for (String string : arr) {
            res.add(Long.valueOf(string));
        }
        return res;
    }
}
