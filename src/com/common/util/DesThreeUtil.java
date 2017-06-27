/**
 * File name:      DescUtil.java
 * Date:           2011-9-11
 * Description:    // 用于详细说明此程序文件完成的主要功
 *                 // 能与其他模块或函数的接口，输出值、
 *                 // 取值范围、含义及参数间的关系
 **********************************************************************
 */

package com.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created on 2011-9-11
 * <p>
 * Title: 汇环_[子系统统名]_[模块名]/p>
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @version 1.0
 */
public class DesThreeUtil {

	static Log log = LogFactory.getLog(DesThreeUtil.class);

	private static final String ALGORITHM = "DESede"; // 加密算法,可用 DES,DESede,Blowfish

	private static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

	private static final String HEX_REGEXP = "^[A-F0-9]{48}$"; // 16进制字符范围

	private static PropertyConfig cryptKeyProperty = new PropertyConfig(); // 密钥配置文件
	private static int MAX_KEY_FILES_NUM = 10; // 密钥文件最大拆分数
	private static String PROPERTY_KEY_VALUE = "keyValue"; // 密钥文件值
	private static String PROPERTY_SUB_KEY_FILE = "subKeyFile"; // 子密钥文件

	private static byte[] pswdCryptKey = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x68, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30,
			0x39, 0x36, (byte) 0xE2 }; // 24字节的默认密钥

	public static void initFileCryptKey(String filePath, StringBuffer errorMsg) {
		if (errorMsg == null) {
			errorMsg = new StringBuffer("");
		} else {
			errorMsg.setLength(0); // 清空出错消息
		}

		if (filePath == null || filePath.equals("")) {
			errorMsg.append("密钥文件路径为空,使用系统默认密钥!");
		} else {
			int recurNum = 0; // 私钥递归次数初始化
			StringBuffer msg = new StringBuffer("");

			// 获取私钥
			String pswdCryptKey = getFileCryptKey(filePath, recurNum, msg);
			errorMsg.append(msg);

			// 设置密钥
			setPswdCryptKey(pswdCryptKey, msg);
			errorMsg.append(msg);
		}
	}

	/**
	 * 读取密钥
	 * 
	 * @param filePath 密钥文件地址
	 * @param errorMsg 密钥设置错误消息
	 */
	private static String getFileCryptKey(String filePath, int recurNum, StringBuffer errorMsg) {
		recurNum++; // 文件递归次数自增1

		if (errorMsg == null) {
			errorMsg = new StringBuffer("");
		} else {
			errorMsg.setLength(0); // 清空出错消息
		}

		if (filePath == null || filePath.equals("")) {
			errorMsg.append("读取第").append(recurNum).append("个密钥文件时出错:").append("密钥文件路径为空!");
			return "";
		} else {
			try {
				// 加载密钥配置文件
				cryptKeyProperty.LoadAppConfiguration(filePath);

				String keyValue = cryptKeyProperty.getProperty(PROPERTY_KEY_VALUE); // 读取密钥
				String subKeyFile = cryptKeyProperty.getProperty(PROPERTY_SUB_KEY_FILE); // 读取密钥子文件
				// 没有密钥则设置密钥为空字符串
				if (keyValue == null) {
					keyValue = "";
				}

				if (recurNum >= MAX_KEY_FILES_NUM || subKeyFile == null || subKeyFile.equals("")) {
					return keyValue;
				} else {
					return keyValue + getFileCryptKey(getClassPath() + subKeyFile, recurNum, errorMsg);
				}

			} catch (FileNotFoundException e) {
				errorMsg.append("读取第").append(recurNum).append("个密钥文件时出错:").append("找不到密钥文件!");
				return "";
			} catch (IOException e) {
				errorMsg.append("读取第").append(recurNum).append("个密钥文件时出错:").append("读取密钥文件失败!");
				return "";
			}
		}
	}

	/**
	 * 设置密钥
	 * 
	 * @param pswdCryptKey 密钥，16进制字符(0~F)组成的48位字符串
	 * @param errorMsg 密钥设置错误消息
	 */
	public static void setPswdCryptKey(String pswdCryptKey, StringBuffer errorMsg) {
		if (errorMsg == null) {
			errorMsg = new StringBuffer("");
		} else {
			errorMsg.setLength(0); // 清空出错消息
		}

		if (checkPswdCryptKey(pswdCryptKey)) {
			DesThreeUtil.pswdCryptKey = getKeyByStr(pswdCryptKey);
		} else {
			errorMsg.append("密钥必须是由16进制字符(0~F)组成的48位字符串,输入无效,使用系统默认密钥!");
		}
	}

	/**
	 * 验证密钥合法性
	 * 
	 * @param pswdCryptKey 密钥，16进制字符(0~F)组成的48位字符串
	 * @return 返回验证结果
	 */
	private static boolean checkPswdCryptKey(String pswdCryptKey) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(HEX_REGEXP);
		Matcher m = p.matcher(pswdCryptKey);
		return m.matches();
	}

	/**
	 * 加密
	 * 
	 * @param src 被加密的数据缓冲区（源）
	 * @param key 密钥，24字节的密钥
	 * @return 返回加密后的数据
	 * @throws Exception
	 */

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
			// 加密
			Cipher c1 = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src 被加密的数据缓冲区（源）
	 * @param key 密钥，24字节的密钥
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
			// 解密
			Cipher c1 = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 密码解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */

	public final static String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()), pswdCryptKey));
		} catch (Exception e) {
			log.error(e);
		}
		return ""; // modify by zhangxiaofeng 解密失败时返回空串
	}

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String password) {
		try {
			return byte2hex(encrypt(password.getBytes(), pswdCryptKey));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	private static String getByteStr(byte[] byt) {
		String strRet = "";
		for (int i = 0; i < byt.length; i++) {
			strRet += getHexValue((byt[i] & 240) / 16);
			strRet += getHexValue(byt[i] & 15);
		}
		return strRet;
	}

	private static String getHexValue(int s) {
		String sRet = null;
		switch (s) {
		case 0:
			sRet = "0";
			break;
		case 1:
			sRet = "1";
			break;
		case 2:
			sRet = "2";
			break;
		case 3:
			sRet = "3";
			break;
		case 4:
			sRet = "4";
			break;
		case 5:
			sRet = "5";
			break;
		case 6:
			sRet = "6";
			break;
		case 7:
			sRet = "7";
			break;
		case 8:
			sRet = "8";
			break;
		case 9:
			sRet = "9";
			break;
		case 10:
			sRet = "A";
			break;
		case 11:
			sRet = "B";
			break;
		case 12:
			sRet = "C";
			break;
		case 13:
			sRet = "D";
			break;
		case 14:
			sRet = "E";
			break;
		case 15:
			sRet = "F";
		}
		return sRet;
	}

	/**
	 * 计算一个16进制字符的10进制值
	 * 输入：0-F
	 */
	private static int getChrInt(char chr) {
		int iRet = 0;
		if (chr == "0".charAt(0))
			iRet = 0;
		if (chr == "1".charAt(0))
			iRet = 1;
		if (chr == "2".charAt(0))
			iRet = 2;
		if (chr == "3".charAt(0))
			iRet = 3;
		if (chr == "4".charAt(0))
			iRet = 4;
		if (chr == "5".charAt(0))
			iRet = 5;
		if (chr == "6".charAt(0))
			iRet = 6;
		if (chr == "7".charAt(0))
			iRet = 7;
		if (chr == "8".charAt(0))
			iRet = 8;
		if (chr == "9".charAt(0))
			iRet = 9;
		if (chr == "A".charAt(0))
			iRet = 10;
		if (chr == "B".charAt(0))
			iRet = 11;
		if (chr == "C".charAt(0))
			iRet = 12;
		if (chr == "D".charAt(0))
			iRet = 13;
		if (chr == "E".charAt(0))
			iRet = 14;
		if (chr == "F".charAt(0))
			iRet = 15;
		return iRet;
	}

	/**
	 * 输入密码的字符形式，返回字节数组形式。
	 * 如输入字符串：AD67EA2F3BE6E5AD
	 * 返回字节数组：{173,103,234,47,59,230,229,173}
	 */
	private static byte[] getKeyByStr(String str) {
		byte[] bRet = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; i++) {
			Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i)) + getChrInt(str.charAt(2 * i + 1)));
			bRet[i] = itg.byteValue();
		}
		return bRet;
	}

	public static void main(String args[]) {
		// byte[] PASSWORD_CRYPT_KEY_BYTES = {0x11, 0x22, 0x4F, 0x58,
		// (byte)0x88, 0x10, 0x68, 0x38, 0x28, 0x25, 0x79, 0x51,
		// (byte)0xCB, (byte)0xDD, 0x55, 0x66, 0x77, 0x29, 0x74,
		// (byte)0x98, 0x30, 0x39, 0x36, (byte)0xE2
		// }; //24字节的密钥
		Scanner input; // 界面输入
		String method; // 输入参数

		// 选择加密/解密
		System.out.println("请输参数：1:文件密钥；2：输入密钥");
		input = new Scanner(System.in);
		method = input.nextLine();
		if (method == null || (!method.equals("1") && !method.equals("2"))) {
			System.out.println("输参数错误!");
			return;
		}

		if (method.equals("1")) {
			// 文件密钥
			System.out.println("请输文件密钥(-1代表默认路径)：");
			input = new Scanner(System.in);
			String keyPath = input.nextLine();
			if (StringUtil.isEmpty(keyPath) || keyPath.equals("-1"))
				keyPath = "/home/ptd/app/postrade/WEB-INF/classes/resources/security/key1.properties";
			StringBuffer errorMsg = new StringBuffer("");
			initFileCryptKey(keyPath, errorMsg);
			if (errorMsg.length() > 0) {
				System.out.println(errorMsg);
			}
		} else {
			// 输入密钥
			System.out.println("请输16进制字符(0~F)组成的48位字符串密钥：");
			input = new Scanner(System.in);
			String keyStr = input.nextLine();
			StringBuffer errorMsg = new StringBuffer("");
			setPswdCryptKey(keyStr, errorMsg);
			System.out.println(DesThreeUtil.pswdCryptKey);
			if (errorMsg.length() > 0) {
				System.out.println(errorMsg);
			}
		}

		// 选择加密/解密
		System.out.println("请输参数：1:加密；2：解密");
		input = new Scanner(System.in);
		method = input.nextLine();
		if (method == null || (!method.equals("1") && !method.equals("2"))) {
			System.out.println("输参数错误!");
			return;
		}

		// 输入需加密的字符串
		System.out.println("请输入密码：");
		input = new Scanner(System.in);
		String str = input.nextLine();

		if (method.equals("1")) {
			System.out.println("[" + str + "] 加密后: ");
			System.out.println(encrypt(str));
		} else {
			System.out.println("[" + str + "] 解密后: ");
			System.out.println(decrypt(str));
		}
	}

	/**
	 * Function Name: getClassPath
	 * 
	 * @return
	 *         description:获取当前项目路径
	 *         Modification History:
	 */
	private static String getClassPath() {
		String classPath = DesThreeUtil.class.getClassLoader().getResource("").getPath();
		try {
			classPath = URLDecoder.decode(classPath, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return classPath;
	}

	/**
	 * Function Name: filePassDecrypt
	 * 
	 * @param passWord,加密后的密码
	 * @return,解密后的密码
	 *                description:
	 *                Modification History:
	 */
	public static String filePassDecrypt(String passWord) {
		StringBuffer errorMsg = new StringBuffer("");
		String filePath = getClassPath() + "/resources/security/key1.properties";
		initFileCryptKey(filePath, errorMsg);
		if (errorMsg.length() > 0) {
			System.out.println(errorMsg);
		}
		return decrypt(passWord);
	}

	/**
	 * Function Name: filePassEncrypt
	 * @param src,加密前的密码
	 * @return,加密后的密码
	 */
	public static String filePassEncrypt(String src) {
		StringBuffer errorMsg = new StringBuffer("");
		String filePath = getClassPath() + "/resources/security/key1.properties";
		initFileCryptKey(filePath, errorMsg);
		if (errorMsg.length() > 0) {
			System.out.println(errorMsg);
		}
		return encrypt(src);
	}

}
