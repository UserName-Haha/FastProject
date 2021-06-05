/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.haha.fastproject.push.xpush;

import android.content.Context;

import com.xuexiang.xpush.core.receiver.impl.XPushReceiver;
import com.xuexiang.xpush.entity.XPushCommand;
import com.xuexiang.xpush.entity.XPushMsg;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * @author xuexiang
 * @since 2019-08-16 17:50
 */
public class CustomPushReceiver extends XPushReceiver {

    private String TAG = "CustomPushReceiver";

    @Override
    public void onNotificationClick(Context context, XPushMsg msg) {
        super.onNotificationClick(context, msg);
        //打开自定义的Activity
        KLog.e(TAG, "onNotificationClick" + msg.getTitle());
    }

    @Override
    public void onCommandResult(Context context, XPushCommand command) {
        super.onCommandResult(context, command);
        KLog.e(TAG, "onCommandResult" + command.getDescription());
    }

    @Override
    public void onMessageReceived(Context context, XPushMsg msg) {
        super.onMessageReceived(context, msg);
        KLog.e(TAG, "onMessageReceived" + msg.getTitle());

    }
}
