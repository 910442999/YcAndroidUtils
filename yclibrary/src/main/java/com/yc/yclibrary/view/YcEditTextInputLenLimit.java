package com.yc.yclibrary.view;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * EditText输入限制类
 * 仅支持中文
 */
public class YcEditTextInputLenLimit {

    private static char[] chineseParam = new char[]{'」', '，', '。', '？', '…', '：', '～', '【', '＃', '、', '％', '＊', '＆', '＄', '（', '‘', '’',
            '“', '”', '『', '〔', '｛', '【', '￥', '￡', '‖', '〖', '《', '「', '》', '〗', '】', '｝', '〕', '』', '”', '）', '！', '；', '—'};

    public static void lengthFilter(final int input_limit_len, final EditText editText) {
        InputFilter[] filters = new InputFilter[1];

        filters[0] = new InputFilter.LengthFilter(input_limit_len) {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                boolean isChinese = checkNameChese(source.toString());

                // 如果不是中文，返回“”
                if (!isChinese) {
                    return "";
                }
                //长度过长就返回“”
                if (dest.toString().length() >= input_limit_len) {
                    return "";
                }

                return source;
            }
        };
        // Sets the list of input filters that will be used if the buffer is Editable. Has no effect otherwise.
        editText.setFilters(filters);
    }

    /**
     * 检测String是否全是中文
     *
     * @param name
     */
    public static boolean checkNameChese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();

        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * 判定输入汉字是否是中文
     *
     * @param c
     */
    public static boolean isChinese(char c) {
        for (char param : chineseParam) {
            if (param == c) {
                return false;
            }
        }
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
}