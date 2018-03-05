package com.dq.easy.cloud.model.common.sign.utils;

import org.apache.http.message.BasicNameValuePair;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.encrypt.md5.utils.DqMD5Utils;
import com.dq.easy.cloud.model.common.encrypt.rsa.utils.DqRSA2Utils;
import com.dq.easy.cloud.model.common.encrypt.rsa.utils.DqRSAUtils;
import com.dq.easy.cloud.model.common.encrypt.sha.utils.DqSHA1Utils;
import com.dq.easy.cloud.model.common.encrypt.sha.utils.DqSHA256Utils;
import com.dq.easy.cloud.model.common.sign.constant.DqSignErrorCode;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 
 * <p>
 * 签名 工具
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午2:38:37
 */
public enum DqSignUtils {

	MD5 {
		/**
		 *
		 * @param content
		 *            需要签名的内容
		 * @param key
		 *            密钥
		 * @param characterEncoding
		 *            字符编码
		 * @return 签名值
		 */
		@Override
		public String createSign(String content, String key, String characterEncoding) {
			return DqMD5Utils.sign(content, key, characterEncoding);
		}

		/**
		 * 签名字符串
		 * 
		 * @param text
		 *            需要签名的字符串
		 * @param sign
		 *            签名结果
		 * @param key
		 *            密钥
		 * @param characterEncoding
		 *            编码格式
		 * @return 签名结果
		 */
		@Override
		public boolean verify(String text, String sign, String key, String characterEncoding) {
			return DqMD5Utils.verify(text, sign, key, characterEncoding);
		}
	},
	HMACSHA256 {
		/**
		 * 签名
		 *
		 * @param content
		 *            需要签名的内容
		 * @param key
		 *            密钥
		 * @param characterEncoding
		 *            字符编码
		 *
		 * @return 签名值
		 */
		@Override
		public String createSign(String content, String key, String characterEncoding) {
			Mac sha256_HMAC = null;
			try {
				sha256_HMAC = Mac.getInstance("HmacSHA256");
				SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(characterEncoding), "HmacSHA256");
				sha256_HMAC.init(secret_key);
				byte[] array = sha256_HMAC.doFinal(content.getBytes(characterEncoding));
				StringBuilder sb = new StringBuilder();
				for (byte item : array) {
					sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
				}
				return sb.toString().toUpperCase();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			throw DqBaseBusinessException.newInstance(DqSignErrorCode.SIGN_HMACSHA256_EXCEPTION);
		}

		/**
		 * 签名字符串
		 *
		 * @param text
		 *            需要签名的字符串
		 * @param sign
		 *            签名结果
		 * @param key
		 *            密钥
		 * @param characterEncoding
		 *            编码格式
		 *
		 * @return 签名结果
		 */
		@Override
		public boolean verify(String text, String sign, String key, String characterEncoding) {
			return createSign(text, key, characterEncoding).equals(sign);
		}
	},

	RSA {
		@Override
		public String createSign(String content, String key, String characterEncoding) {
			return DqRSAUtils.sign(content, key, characterEncoding);
		}

		@Override
		public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
			return DqRSAUtils.verify(text, sign, publicKey, characterEncoding);
		}
	},

	RSA2 {
		@Override
		public String createSign(String content, String key, String characterEncoding) {
			return DqRSA2Utils.sign(content, key, characterEncoding);
		}

		@Override
		public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
			return DqRSA2Utils.verify(text, sign, publicKey, characterEncoding);
		}
	},
	SHA1 {
		@Override
		public String createSign(String content, String key, String characterEncoding) {
			return DqSHA1Utils.sign(content, key, characterEncoding);
		}

		@Override
		public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
			return DqSHA1Utils.verify(text, sign, publicKey, characterEncoding);
		}
	},
	SHA256 {
		@Override
		public String createSign(String content, String key, String characterEncoding) {
			return DqSHA256Utils.sign(content, key, characterEncoding);
		}

		@Override
		public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
			return DqSHA256Utils.verify(text, sign, publicKey, characterEncoding);
		}
	},
	SM3 {
		@Override
		public String createSign(String content, String key, String characterEncoding) {
			return DqRSA2Utils.sign(content, key, characterEncoding);
		}

		@Override
		public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
			return DqRSA2Utils.verify(text, sign, publicKey, characterEncoding);
		}
	};

	/**
	 *
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“@param separator”字符拼接成字符串
	 * 
	 * @param parameters
	 *            参数
	 * @return 去掉空值与签名参数后的新签名，拼接后字符串
	 */
	public static String parameterText(Map<String, Object> parameters) {
		return parameterText(parameters, "&");

	}

	/**
	 *
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“@param separator”字符拼接成字符串
	 * 
	 * @param parameters
	 *            参数
	 * @param separator
	 *            分隔符
	 * @return 去掉空值与签名参数后的新签名，拼接后字符串
	 */
	public static String parameterText(Map<String, Object> parameters, String separator) {
		return parameterText(parameters, separator, "signature", "sign", "key", "sign_type");
	}

	/**
	 *
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“@param separator”字符拼接成字符串
	 * 
	 * @param parameters
	 *            参数
	 * @param separator
	 *            分隔符
	 * @param ignoreKey
	 *            需要忽略添加的key
	 * @return 去掉空值与签名参数后的新签名，拼接后字符串
	 */
	public static String parameterText(Map<String, Object> parameters, String separator, String... ignoreKey) {
		if (parameters == null) {
			return "";
		}
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		if (null != ignoreKey) {
			Arrays.sort(ignoreKey);
		}
		if (parameters instanceof SortedMap) {
			for (String k : ((Set<String>) parameters.keySet())) {
				Object v = parameters.get(k);
				if (DqBaseUtils.isNull(v) || DqStringUtils.isEmpty(v.toString()) || (null != ignoreKey && Arrays.binarySearch(ignoreKey, k) >= 0)) {
					continue;
				}
				sb.append(k).append("=").append(v.toString().trim()).append(separator);
			}
			if (sb.length() > 0 && !"".equals(separator)) {
				sb.deleteCharAt(sb.length() - 1);
			}
			return sb.toString();

		}

		List<String> keys = new ArrayList<String>(parameters.keySet());
		// 排序
		Collections.sort(keys);
		for (String k : keys) {
			String valueStr = DqStringUtils.EMPTY;
			Object o = parameters.get(k);
			if (o instanceof String[]) {
				String[] values = (String[]) o;
				for (int i = 0; i < values.length; i++) {
					String value = values[i].trim();
					if (DqStringUtils.isEmpty(value)) {
						continue;
					}
					valueStr += (i == values.length - 1) ? value : value + ",";
				}
			} else if (o != null) {
				valueStr = o.toString();
			}
			if (DqStringUtils.isEmpty(valueStr) || (null != ignoreKey && Arrays.binarySearch(ignoreKey, k) >= 0)) {
				continue;
			}
			sb.append(k).append("=").append(valueStr).append(separator);
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 将参数集合(事前做好排序)按分割符号拼凑字符串并加密为MD5 example: mchnt_cd+"|"
	 * +order_id+"|"+order_amt+"|"+order_pay_type+"|"+page_notify_url+"|"+
	 * back_notify_url+"|"+order_valid_time+"|"+iss_ins_cd+"|"+goods_name+"|"+
	 * "+goods_display_url+"|"+rem+"|"+ver+"|"+mchnt_key
	 * 
	 * @param parameters
	 *            参数集合
	 * @param separator
	 *            分隔符
	 * @return 参数排序好的值
	 */
	@SuppressWarnings("unchecked")
	public static String parameters2MD5Str(Object parameters, String separator) {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();

		if (parameters instanceof LinkedHashMap) {
			Set<String> keys = (Set<String>) ((LinkedHashMap<String, ?>) parameters).keySet();
			for (String key : keys) {
				String val = ((LinkedHashMap<?, ?>) parameters).get(key).toString();
				sb.append(val).append(separator);
			}
		} else if (parameters instanceof List) {
			for (BasicNameValuePair bnv : ((List<BasicNameValuePair>) parameters)) {
				sb.append(bnv.getValue()).append(separator);
			}
		}

		return DqStringUtils.isEmpty(sb.toString()) ? DqStringUtils.EMPTY : sb.deleteCharAt(sb.length() - 1).toString();
	}

	/**
	 * 获取随机字符串
	 * 
	 * @return 随机字符串
	 */
	public static String randomStr() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 签名
	 *
	 * @param parameters
	 *            需要进行排序签名的参数
	 * @param key
	 *            密钥
	 * @param characterEncoding
	 *            编码格式
	 * @return 签名值
	 */
	public String sign(Map<String, Object> parameters, String key, String characterEncoding) {

		return createSign(parameterText(parameters, "&"), key, characterEncoding);
	}

	/**
	 * 签名
	 * 
	 * @param parameters
	 *            需要进行排序签名的参数
	 * @param key
	 *            密钥
	 * @param separator
	 *            分隔符 默认 &amp;
	 * @param characterEncoding
	 *            编码格式
	 * @return 签名值
	 */
	public String sign(Map<String, Object> parameters, String key, String separator, String characterEncoding) {

		return createSign(parameterText(parameters, separator), key, characterEncoding);

	}

	/**
	 * 签名
	 *
	 * @param content
	 *            需要签名的内容
	 * @param key
	 *            密钥
	 * @param characterEncoding
	 *            字符编码
	 * @return 签名值
	 */
	public abstract String createSign(String content, String key, String characterEncoding);

	/**
	 * 签名字符串
	 *
	 * @param params
	 *            需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @param key
	 *            密钥
	 * @param characterEncoding
	 *            编码格式
	 * @return 签名结果
	 */
	public boolean verify(Map<String, Object> params, String sign, String key, String characterEncoding) {
		// 判断是否一样
		return this.verify(parameterText(params), sign, key, characterEncoding);
	}

	/**
	 * 签名字符串
	 *
	 * @param text
	 *            需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @param key
	 *            密钥
	 * @param characterEncoding
	 *            编码格式
	 * @return 签名结果
	 */
	public abstract boolean verify(String text, String sign, String key, String characterEncoding);

}
