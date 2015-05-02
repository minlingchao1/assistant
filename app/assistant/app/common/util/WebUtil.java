package assistant.app.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.ProxyHost;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebUtil {

    private final static Logger LOG = LoggerFactory.getLogger(WebUtil.class);

    public static String getContent(String url, String charset) throws Exception {
        InputStream in = null;
        String s = "";
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("Connection", "close");
            in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, charset));
            StringBuffer sb = new StringBuffer();
            char[] charBuf = new char[1024 * 4];
            int len = br.read(charBuf);
            while (len != -1) {
                sb.append(charBuf, 0, len);
                len = br.read(charBuf);
            }
            br.close();
            s = sb.toString();
        } catch (BindException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return s;
    }

    public static void main(String[] args) throws Exception {
        // System.out.println(getContent("http://tradecardseller.wangwang.taobao.com/tradecard/nameCard.htm?uid=cntaobaosimpleliangy",
        // "gbk"));
        System.out
                .println(getContent(
						"http://117.135.134.240/msg/HttpPkgSM?account=zhuluu&pswd=123456Aa&product=1233577153&msg=15167893925|亲保邮哦&msg=15167893925|亲保邮哦2",
                        "gbk"));
    }

    public static String doPost(String Url) throws HttpException, IOException {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("UTF-8");
        client.getParams().setConnectionManagerTimeout(10000);
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        client.executeMethod(method);
        return method.getResponseBodyAsString();
    }

    public static String doPost(String Url, NameValuePair[] data) throws HttpException, IOException {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        method.setRequestBody(data);
        client.getParams().setContentCharset("UTF-8");
        client.getParams().setConnectionManagerTimeout(10000);
        client.executeMethod(method);
        return method.getResponseBodyAsString();
    }

    public static String doPostForWechat(String Url, NameValuePair[] data) throws HttpException, IOException {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        method.setRequestHeader("Referer", "https://mp.weixin.qq.com/");
        method.setRequestHeader("Connection", "keep-alive");
        method.setRequestHeader("Host", "mp.weixin.qq.com");

        method.setRequestBody(data);
        client.getParams().setContentCharset("UTF-8");
        client.getParams().setConnectionManagerTimeout(10000);
        client.executeMethod(method);
        return method.getResponseBodyAsString();
    }

    public static String doPost(String Url, NameValuePair[] data, String charset) throws HttpException, IOException {
        // HttpClient client = new HttpClient();
        // PostMethod method = new PostMethod(Url);
        // client.getParams().setContentCharset(charset);
        // client.getParams().setConnectionManagerTimeout(10000);
        // method.setRequestHeader("ContentType",
        // "application/x-www-form-urlencoded;charset=" + charset);
        // client.executeMethod(method);
        // method.setRequestBody(data);
        // return method.getResponseBodyAsString();

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=" + charset);
        method.setRequestBody(data);
        client.getParams().setContentCharset(charset);
        client.getParams().setConnectionManagerTimeout(10000);
        client.executeMethod(method);
        return method.getResponseBodyAsString();
    }

    public static String doPost(String Url, NameValuePair[] data, String charset, RequestEntity entity)
            throws HttpException, IOException {
        // HttpClient client = new HttpClient();
        // PostMethod method = new PostMethod(Url);
        // client.getParams().setContentCharset(charset);
        // client.getParams().setConnectionManagerTimeout(10000);
        // method.setRequestHeader("ContentType",
        // "application/x-www-form-urlencoded;charset=" + charset);
        // client.executeMethod(method);
        // method.setRequestBody(data);
        // return method.getResponseBodyAsString();

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=" + charset);
        method.setRequestBody(data);
        method.setRequestEntity(entity);
        client.getParams().setContentCharset(charset);
        client.getParams().setConnectionManagerTimeout(10000);
        client.executeMethod(method);
        return method.getResponseBodyAsString();
    }

    public static String post(String url, List<NameValuePair> params) {

        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        if (!CollectionUtils.isEmpty(params)) {
            for (NameValuePair param : params) {
                method.addParameter(param);
            }
        }

        try {
            int status = client.executeMethod(method);
            if (status != HttpStatus.SC_OK) {
                LOG.error("post eror, status code:" + status);
            } else {
                return method.getResponseBodyAsString();
            }
        } catch (HttpException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            method.releaseConnection();
        }
        return StringUtils.EMPTY;
    }

    public static String get(String url) {

        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

        GetMethod method = new GetMethod(url);
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        try {
            int status = client.executeMethod(method);
            if (status != HttpStatus.SC_OK) {
                LOG.error("Get subway rate error, status code:" + status);
            } else {
                return method.getResponseBodyAsString();
            }
        } catch (HttpException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public static String getByHttpClient(String url) {
        return getByHttpClient(url, null);
    }

    public static String getByHttpClient(String url, ProxyHost proxyHost) {

        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

        if (proxyHost != null) {
            client.getHostConfiguration().setProxyHost(proxyHost);
            LOG.error("proxy host:" + proxyHost.getHostName() + ":" + proxyHost.getPort());
        }

        int count = 0;
        while (count++ < 3) {

            GetMethod method = new GetMethod(url);
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

            try {
                int status = client.executeMethod(method);
                if (status != HttpStatus.SC_OK) {
                    LOG.error("Get subway rate error, status code:" + status);
                } else {
                    return method.getResponseBodyAsString();
                }
            } catch (HttpException e) {
                LOG.error(e.getMessage(), e);
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            } finally {
                method.releaseConnection();
            }
        }
        return null;
    }

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			LOG.error("Weixin server connection timed out.");
		} catch (Exception e) {
			LOG.error("https request error:{}", e);
		}
		return jsonObject.toString();
	}
}
