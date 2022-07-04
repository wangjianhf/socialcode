package com.cn.wangjian.utils.socialcode;

import com.cn.wangjian.utils.areacode.AreaCode;
import org.apache.commons.lang.StringUtils;

/**
 * @author 王剑
 */
public class SocialCreditNum {

	//机构类别
	private final int[] PRE1 = { 9, 1 };

	//地区码
	private final int[] PRE2 = new AreaCode().createAreaCode();

	// 组织机构代码校验时每一位的加权因子
	private final int[] POWER1 = { 3, 7, 9, 10, 5, 8, 4, 2 };

	//数字类型的组织机构代码
	OrgCodeNum ocn = new OrgCodeNum();
	public int[] orgCode =  ocn.getOrgCode();

	// 数字类型的统一社会信用代码
	public int[] socCode = new int[18];

	// 字符串类型的统一社会信用代码
	public String[] strCode = new String[18];


	public  void getSocialCode(int[] temp) {
        //统一社会信用代码每一位位取值
		getSocialCode0_7(temp);
		getSocialCode8_15(temp);
		getSocialCode16(temp);
	}

	public void getSocialCode0_7(int[] a) {
        //统一社会信用代码第0-7位取值,共8位
		a[0] = PRE1[0];
		a[1] = PRE1[1];
		System.arraycopy(PRE2, 0, a, 2, PRE2.length);
	}

	public void getSocialCode8_15(int[] a) {
        //统一社会信用代码第8-15位取值，共8位
		System.arraycopy(ocn.orgCode, 0, a, 8, orgCode.length);
	}

	public  void getSocialCode16(int[] a) {
        // 统一社会信用代码第16取值，共1位，即把组织机构代码的校验码放到统一社会信用代码里
		int sum = 0;
		for (int i = 0; i < POWER1.length; i++) {
			sum += a[i+8] * POWER1[i];
		}
		a[16] = 11 - sum % 11;
	}

	public  String[] intToString1(int[] intSocCode, String[] strCode){
		//将已经生成的统一社会信用代码（数字形式）转化为数字+字母形式
		for (int i = 0; i < 16; i++) {
			switch (intSocCode[i]) {
				case 0 : strCode[i] = "0"; break;
				case 1 : strCode[i] = "1"; break;
				case 2 : strCode[i] = "2"; break;
				case 3 : strCode[i] = "3"; break;
				case 4 : strCode[i] = "4"; break;
				case 5 : strCode[i] = "5"; break;
				case 6 : strCode[i] = "6"; break;
				case 7 : strCode[i] = "7"; break;
				case 8 : strCode[i] = "8"; break;
				case 9 : strCode[i] = "9"; break;
				case 10: strCode[i] = "A"; break;
				case 11: strCode[i] = "B"; break;
				case 12: strCode[i] = "C"; break;
				case 13: strCode[i] = "D"; break;
				case 14: strCode[i] = "E"; break;
				case 15: strCode[i] = "F"; break;
				case 16: strCode[i] = "G"; break;
				case 17: strCode[i] = "H"; break;
				case 19: strCode[i] = "J"; break;
				case 20: strCode[i] = "K"; break;
				case 21: strCode[i] = "L"; break;
				case 22: strCode[i] = "M"; break;
				case 23: strCode[i] = "N"; break;
				case 25: strCode[i] = "P"; break;
				case 26: strCode[i] = "Q"; break;
				case 27: strCode[i] = "R"; break;
				case 29: strCode[i] = "T"; break;
				case 30: strCode[i] = "U"; break;
				case 32: strCode[i] = "W"; break;
				case 33: strCode[i] = "X"; break;
				case 34: strCode[i] = "Y"; break;
				default: break;
			}

		}
		switch (intSocCode[16]) {
			case 0 :
			case 11: strCode[16] = "0"; break;
			case 1 : strCode[16] = "1"; break;
			case 2 : strCode[16] = "2"; break;
			case 3 : strCode[16] = "3"; break;
			case 4 : strCode[16] = "4"; break;
			case 5 : strCode[16] = "5"; break;
			case 6 : strCode[16] = "6"; break;
			case 7 : strCode[16] = "7"; break;
			case 8 : strCode[16] = "8"; break;
			case 9 : strCode[16] = "9"; break;
			case 10: strCode[16] = "X"; break;
			default: break;
		}

		return strCode;

	}

	public  String intToString2(int a){
		String str = null;
			switch (a) {
				case 0 :
				case 31: str = "0"; break;
				case 1 : str = "1"; break;
				case 2 : str = "2"; break;
				case 3 : str = "3"; break;
				case 4 : str = "4"; break;
				case 5 : str = "5"; break;
				case 6 : str = "6"; break;
				case 7 : str = "7"; break;
				case 8 : str = "8"; break;
				case 9 : str = "9"; break;
				case 10: str = "A"; break;
				case 11: str = "B"; break;
				case 12: str = "C"; break;
				case 13: str = "D"; break;
				case 14: str = "E"; break;
				case 15: str = "F"; break;
				case 16: str = "G"; break;
				case 17: str = "H"; break;
				case 18: str = "J"; break;
				case 19: str = "K"; break;
				case 20: str = "L"; break;
				case 21: str = "M"; break;
				case 22: str = "N"; break;
				case 23: str = "P"; break;
				case 24: str = "Q"; break;
				case 25: str = "R"; break;
				case 26: str = "T"; break;
				case 27: str = "U"; break;
				case 28: str = "W"; break;
				case 29: str = "X"; break;
				case 30: str = "Y"; break;
				default: break;
			}
		return str;
	}

	public  String creatSocialCreditCode() {
		// 统一社会信用代码生成
		getSocialCode(socCode);
		String code17 = StringUtils.join(intToString1(socCode, strCode)).substring(0,17);
		socCode[17] = getSocCheckCode.checkCode(code17);
		return code17 + intToString2(socCode[17]);
	}

}

