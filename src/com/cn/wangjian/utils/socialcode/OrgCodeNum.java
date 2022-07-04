package com.cn.wangjian.utils.socialcode;

import java.util.Random;

public class OrgCodeNum {
	// 定义长度为8的整型数组存放 组织机构代码的每一位
	int[] orgCode = new int[8];

	// 随机生成0-35之间的数
	public void createOrgCodeArray(int[] temp) {
		for (int i = 0; i < 8; i++) {
            //随机生成组织机构代码的每一位的，记为code，code ∈ [0-35);
			int code = new Random().nextInt(35);
			switch (code) {
				case 18:
				case 24:
				case 28:
				case 31: code-=1; break;
				default: break;
			}
			temp[i] = code;
		}
	}

	public  int[] creatOrgCode(int[] temp) {
		createOrgCodeArray(temp);
		return temp;
	}

	public  int[] getOrgCode() {
		return creatOrgCode(orgCode);
	}

}
