package com.yep.uiautomator2.handler;

import android.graphics.Point;
import android.support.test.uiautomator.UiDevice;

import com.yep.uiautomator2.ServiceManage.MTelePhoneManage;
import com.yep.uiautomator2.core.UiAutomatorBridge;
import com.yep.uiautomator2.executorserver.AndroidCommand;
import com.yep.uiautomator2.executorserver.AndroidCommandResult;
import com.yep.uiautomator2.utils.Device;
import com.yep.uiautomator2.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

/**
 * 获取屏幕大小
 * Created by Administrator on 2017/3/12.
 */
public class GetDeviceSize extends BaseCommandHandler {
    @Override
    public AndroidCommandResult execute(AndroidCommand command) throws JSONException {
        try{
            final JSONObject res = new JSONObject();
            boolean flag = (boolean)command.params().get("flag");
            if(flag){
                Point p = new Point();
                UiAutomatorBridge.getInstance().getDefaultDisplay().getRealSize(p);
                res.put("height", p.y);
                res.put("width", p.x);
            }else {
                res.put("height", Device.getInstance().getUiDevice().getDisplayHeight());
                res.put("width", Device.getInstance().getUiDevice().getDisplayWidth());
            }
            return getSuccessResult(res);
        }catch (Exception e){
            return getErrorResult(e.getMessage());
        }
    }
}
