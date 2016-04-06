package enterprayz.megatools;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> fc76bc5... change copyright
 * Copyright 2016 U.Prayzner
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
<<<<<<< HEAD
=======
 * Created by urec on 30.09.15 urecki22@gmail.com.
>>>>>>> c4311c8... Init commit
=======
>>>>>>> fc76bc5... change copyright
 */
public class NotificationBuilder {
    private static final int SERVICE_NOTIFY_ID = 101;


    public static void createCanceledNotification(Context context, Class activity, int largeIcoRes, int smallIco, String title, String subtitle, String ticket) {
        createNotification(
                context,
                activity,
                (int) Calendar.getInstance().getTimeInMillis(),
                largeIcoRes,
                smallIco,
                title,
                subtitle,
                ticket,
                true);
    }

    public static void killServiceNotification(Context context) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(SERVICE_NOTIFY_ID);
    }


    private static void createNotification(Context context, Class activity, int id, int largeIcoRes, int smallIcoRes, String title, String subTitle, String ticket, boolean canCancel) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), largeIcoRes);

        Intent intent = new Intent(context, activity);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, FLAG_ACTIVITY_NEW_TASK);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                context)
                .setContentText(subTitle)
                .setContentTitle(title)
                .setSmallIcon(smallIcoRes)
                .setOngoing(!canCancel)
                .setTicker(ticket)
                .setLargeIcon(largeIcon)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent);

        Notification notification = notificationBuilder.build();

        notificationManager.notify(id, notification);
    }
}