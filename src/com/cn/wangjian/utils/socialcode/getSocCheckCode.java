package com.cn.wangjian.utils.socialcode;

public class getSocCheckCode {
    /**
     * 获取统一社会信用代码校验码（第18位）
     */
    public static int checkCode(String str) {
        //统一社会信用代码中用到的英文字母（A~Z中除去I、O、S、V、Z这个五个不用）
        final char[] tempStr = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'T', 'U', 'W', 'X', 'Y'};
        //统一社会信用代码中英文字母所代表的数字值
        final int[] tempInt = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        //统一社会信用代码前17位的权重
        final int[] qz = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};

        //校验码计算
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            char tempChar = str.charAt(i);
            if ('0' <= tempChar & tempChar <= '9') {
                total += Integer.parseInt(tempChar + "") * qz[i];
            } else {
                for (int m = 0; m < tempStr.length; m++) {
                    total += (tempChar == tempStr[m]) ? (tempInt[m] * qz[i]) : 0;
                }
            }
        }
        return (31 - total % 31);
    }
}
