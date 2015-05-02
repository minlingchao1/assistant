package assistant.app.common.util;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.MissingNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("unused")
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static final ObjectMapper mapper = new ObjectMapper();

    private static final String TAG = "JsonUtil";

    /**
     * change obj to json.
     * 
     * @param obj
     * @return json string
     */
    public static String toJsonWithException(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }

    public static <T> T toObject(String node, Class<T> className) {
        try {
            return mapper.readValue(node, className);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T> T toObject(JsonNode node, Class<T> className) {
        try {
            return mapper.readValue(node, className);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // public <T> T readValue(JsonParser jp, Class<T> valueType)

    public static String getJson(Object obj) {

        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("failed to write json:" + obj, e);
        }
        return "{}";
    }

    public static JsonNode readJsonResult(HttpResponse resp) {
        JsonNode jsonNode = MissingNode.getInstance();
        if (resp == null) {
            return jsonNode;
        }
        HttpEntity entity = resp.getEntity();
        if (entity == null) {
            return jsonNode;
        }

        try {
            String content = IOUtils.toString(entity.getContent());
            if (StringUtils.length(content) <= 2) {
                return jsonNode;
            }
            // log.info("[content]" + content);
            jsonNode = mapper.readValue(content, JsonNode.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jsonNode;
    }

    @SuppressWarnings("unchecked")
    public static <T> T readJsonResult(HttpResponse resp, Class<?> cls) {
        JsonNode jsonNode = MissingNode.getInstance();
        if (resp == null) {
            return null;
        }
        HttpEntity entity = resp.getEntity();
        if (entity == null) {
            return null;
        }
        Object readValue = null;
        try {
            String content = IOUtils.toString(entity.getContent());
            if (StringUtils.length(content) <= 2) {
                return (T) readValue;
            }
            readValue = mapper.readValue(content, cls);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return (T) readValue;
    }

    public static JsonNode readJsonResult(String content) {
        try {
            return mapper.readValue(content, JsonNode.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return MissingNode.getInstance();
    }

    public static JsonNode parserJSONP(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            content = content.substring(content.indexOf('(') + 1, content.lastIndexOf(')'));
            JsonNode node = JsonUtil.readJsonResult(content);
            return node;
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }

    public static <T> List<T> toList(String content, Class<T> className) {
        JSONArray jsonArray = JSONArray.fromObject(content);
        List<T> objectList = (List<T>) jsonArray.toCollection(jsonArray, className);
        return objectList;
    }
}
