package com.lenovo.manufacture.hxf.Utils;

import android.content.Context;
import android.widget.Toast;
/**
 * 适用于需要实时刷新Toast内容的场景，可将上一次的Toast内容覆盖
 */
public class ShowToast{
    private static Toast mtoast=null;//初始化Toast
    public static void show(Context context,String str){
        if (mtoast!=null){//如果Toast内容不为空，则取消Toast显示，并赋值为null
            mtoast.cancel();//注销之前显示的那条信息
            mtoast=null;//上一步相当于隐藏了信息，mtoast并没有为空，强制使它为空
        }
        if (mtoast==null){//如果Toast内容为空，则将内容赋值给mtoast，并显示
            mtoast=Toast.makeText(context,str, Toast.LENGTH_SHORT);//将新的内容赋值给mtoast
            mtoast.show();//显示Toast
        }
    }
}
