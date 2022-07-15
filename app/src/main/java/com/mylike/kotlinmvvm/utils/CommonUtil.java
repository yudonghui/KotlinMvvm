package com.mylike.kotlinmvvm.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mylike.kotlinmvvm.MyApplication;
import com.mylike.kotlinmvvm.R;
import com.mylike.kotlinmvvm.common.SPUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class CommonUtil {

    //-----------------------------------------------------------toast提示-------------------------------------------------------
    private static Toast toast;

    /**
     * 解决Toast重复弹出 长时间不消失的问题
     *
     * @param message
     */
    public static void showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }


    //-------------------------------------------------------手机号正则表达式-------------------------------------------------------
    /**
     * 判断字符串是否符合手机号码格式
     * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
     * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
     * 电信号段: 133,149,153,170,173,177,180,181,189
     * "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
     */
    private static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";

    /**
     * 判断是否是手机号
     *
     * @param mobileNums
     * @return 是手机号true
     */
    public static boolean isChinaPhoneLegal(String mobileNums) {
        if (TextUtils.isEmpty(mobileNums)) {
            return false;
        } else {
            return mobileNums.matches(REGEX_MOBILE);
        }
    }


    //-------------------------------------------------------键盘的显示隐藏-------------------------------------------------------

    //根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
    public static boolean isShouldHideInput(View v, MotionEvent event, int a) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                if (a == 0) {
                    isShouldHideInput(v, event, 1);
                }
                return true;
            }
        }
        return false;
    }

    //多种隐藏软件盘方法的其中一种
    public static void hideSoftInput(IBinder token, Context context) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //如果软键盘在窗口上已经显示，则隐藏，反之则显示
    public static void hideKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    //-------------------------------------------------------获取日期-------------------------------------------------------
    //获取年月日
    public static String getY(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    //获取年月日
    public static String getYMD(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    //获取年月日时分
    public static String getYMDHM(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return format.format(date);
    }

    //获取年月日时分
    public static String getYMDHMS(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }

    //-------------------------------------------------------App检查更新-------------------------------------------------------
    private AlertDialog.Builder builder1;
    private AlertDialog.Builder builder2;
    private AlertDialog alertDialog1;
    private AlertDialog alertDialog2;

    //判断是否为手机号   合法true   不合法flase
    public static boolean isLegalPhone(String phone) {
        if (TextUtils.isEmpty(phone)) return false;
        String reg = "^1[3-9][0-9]{9}$";
        return Pattern.matches(reg, phone);
    }

    /**
     * 判断车牌号 一般的车牌号
     */
    public static boolean isCarnumberNO(String carnumber) {
        /*
         * 车牌号格式：汉字 + A-Z + 5位A-Z或0-9 （只包括了普通车牌号，教练车和部分部队车等车牌号不包括在内）
         */
        String carnumRegex = "[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}";//在正则表达
        if (TextUtils.isEmpty(carnumber))
            return false;
        else
            return carnumber.matches(carnumRegex);
    }

    public static int px2dp(double pxValue) {
        float m = MyApplication.context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / m + 0.5f);
    }

    public static int dp2px(double dipValue) {
        float m = MyApplication.context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * m + 0.5f);
    }

    public static int sp2px(float spValue) {
        final float fontScale = MyApplication.context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int toIntByStr(String s) {
        if (TextUtils.isEmpty(s)) return 0;
        return Integer.parseInt(s);
    }

    public static int strToInt(String string) {
        if (TextUtils.isEmpty(string)) {
            return 0;
        }
        return Integer.parseInt(string);
    }

    public static void removeEmpty(Map<String, Set<String>> selectedValue) {
        if (selectedValue != null && selectedValue.size() > 0) {
            Iterator<Map.Entry<String, Set<String>>> iterator = selectedValue.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Set<String>> entry = iterator.next();
                Set<String> value = entry.getValue();
                if (value == null || value.size() == 0) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 通过身份证号码获取出生年月日
     */
    public static String getBirthdayById(String id) {
        if (TextUtils.isEmpty(id) || id.length() != 18) {
            return "";
        }
        String birthDay = id.substring(6, 14);
        String yearBirthStr = birthDay.substring(0, 4);
        String monthBirthStr = birthDay.substring(4, 6);
        String dayBirthStr = birthDay.substring(6);
        return yearBirthStr + "-" + monthBirthStr + "-" + dayBirthStr;
    }

    /**
     * 根据身份证号码计算年龄
     *
     * @param psptNo
     * @return
     */
    public static int getAgeByPsptNo(String psptNo) {
        if (TextUtils.isEmpty(psptNo)) {
            return 0;
        }
        String birthDay = psptNo.substring(6, 14);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String yearStr = time.split("-")[0];
        String monthStr = time.split("-")[1];
        String dayStr = time.split("-")[2];
        String yearBirthStr = birthDay.substring(0, 4);
        String monthBirthStr = birthDay.substring(4, 6);
        String dayBirthStr = birthDay.substring(6);
        int year = Integer.valueOf(yearStr);
        int yearBirth = Integer.valueOf(yearBirthStr);
        if (year - yearBirth <= 0) {
            return 0;
        }
        int age = year - yearBirth;
        int month = Integer.valueOf(monthStr);
        int monthBirth = Integer.valueOf(monthBirthStr);
        if (month - monthBirth > 0) {
            return age;
        }
        if (month - monthBirth < 0) {
            return --age;
        }
        int day = Integer.valueOf(dayStr);
        int dayBirth = Integer.valueOf(dayBirthStr);
        if (day - dayBirth >= 0) {
            return age;
        }
        return --age;
    }
}
