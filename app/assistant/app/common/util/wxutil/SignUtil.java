/**
 * @Project:assistant
 * @Title: SignUtil.java
 * @date: 2015-2-3 下午10:08:43
 * @version 1.0
 */
package assistant.app.common.util.wxutil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import assistant.app.common.util.MD5Util;

/**
 * @ClassName SignUtil
 * @Description 微信验证
 * @author minlingchao
 * @date 2015-2-3 下午10:08:43
 */
public class SignUtil {

	/**
	 * 微信加密签名
	 */
	public static boolean vaildSign(String signature, String token, String timestamp, String nonce) {

		String[] paramArr = new String[] { token, timestamp, nonce };
		// 对token、timestamp、nonce 进行字典排序，并拼接成字符串
		Arrays.sort(paramArr);
		StringBuilder sb = new StringBuilder(paramArr[0]);
		sb.append(paramArr[1]).append(paramArr[2]);
		String ciphertext = null;
		MessageDigest md;
		// SHA1加密
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(sb.toString().getBytes());
			ciphertext = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 将sha1加密后的字符串与 signature 进行比较
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 高德地图签名获取
	 */

	public static String amapValidSign(String key, String tableid, String data) {
		String[] paramArr = new String[] { key, tableid, data };
		Arrays.sort(paramArr);
		StringBuilder sb = new StringBuilder(paramArr[0]);
		sb.append("&").append(paramArr[1]).append("&").append(paramArr[2]);
		String signature = MD5Util.MD5(sb.toString());
		return signature;
	}
	private static String byteToStr(byte[] byteArray){
		String rst = "";
		for (int i = 0; i < byteArray.length; i++) {
			rst += byteToHex(byteArray[i]);
		}
		return rst;
	}

	private static String byteToHex(byte b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(b >>> 4) & 0X0F];
		tempArr[1] = Digit[b & 0X0F];
		String s = new String(tempArr);
		return s;

	}

}
