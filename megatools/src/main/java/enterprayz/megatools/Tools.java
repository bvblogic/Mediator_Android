package enterprayz.megatools;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

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
 * Created by con on 20.03.16.
>>>>>>> c4311c8... Init commit
=======
>>>>>>> fc76bc5... change copyright
 */
public class Tools {

    private static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static void openBrowser(Activity activity, String url) throws ActivityNotFoundException {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent browserIntent;
        if (url.contains("facebook")) {
            browserIntent = newFacebookIntent(activity.getPackageManager(), url);
        } else {
            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        }
        activity.startActivity(browserIntent);

    }

    public static void openGmaps(Activity activity, String label, double latitude, double longitude) throws ActivityNotFoundException {
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }

    public static void openCallendar(Activity activity, Date begin, Date end, String location) throws ActivityNotFoundException {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", begin.getTime());
        intent.putExtra("allDay", true);
        intent.putExtra("eventLocation", location);
        intent.putExtra("endTime", end.getTime());
        intent.putExtra("title", "A Test Event from android app");
        activity.startActivity(intent);
    }

    public static String readText(Context context, @RawRes int rawTextFileResId) {
        InputStream inputStream = context.getResources().openRawResource(rawTextFileResId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }

    public static void showHintMessage(@NonNull View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void showHintMessage(@NonNull View view, @StringRes int message) {
        showHintMessage(view, view.getResources().getString(message));
    }

    public static String decodeBase64(String base64Format) {
        byte[] authEncBytes = Base64.decode(base64Format.getBytes(), Base64.DEFAULT);
        return new String(authEncBytes);
    }

    public static int getRelativeTop(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }

    public static Uri ResourceToUri(Context context, int resID) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                context.getResources().getResourcePackageName(resID) + '/' +
                context.getResources().getResourceTypeName(resID) + '/' +
                context.getResources().getResourceEntryName(resID));
    }


    public static int getRandomInteger(int aStart, int aEnd, Random aRandom) {
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        return (int) (fraction + aStart);
    }

    public static int getTextHeight(String text, int maxWidth, float textSize, Typeface typeface) {
        TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        paint.setTextSize(textSize);
        paint.setTypeface(typeface);

        int lineCount = 0;

        int index = 0;
        int length = text.length();

        while (index < length - 1) {
            index += paint.breakText(text, index, length, true, maxWidth, null);
            lineCount++;
        }

        Rect bounds = new Rect();
        paint.getTextBounds("Py", 0, 2, bounds);
        return (int) Math.floor(lineCount * bounds.height());
    }

    public static void setStatusBarColor(Activity activity, int resColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTranslucentStatus(activity, true);
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(ContextCompat.getColor(activity, resColor));
        }
    }

    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static Rect getViewRect(View view) {
        int[] pointA = new int[2];
        view.getLocationOnScreen(pointA);
        return new Rect(pointA[0], pointA[1], pointA[0] + view.getWidth(), pointA[1] + view.getHeight());
    }

    public static void replace(View source, int xTo, int yTo, float xScale, float yScale) {
        AnimationSet replaceAnimation = new AnimationSet(false);
        replaceAnimation.setFillAfter(true);

        ScaleAnimation scale = new ScaleAnimation(1.0f, xScale, 1.0f, yScale);
        scale.setDuration(1000);

        TranslateAnimation trans = new TranslateAnimation(0, 0,
                TranslateAnimation.ABSOLUTE, xTo - source.getLeft(), 0, 0,
                TranslateAnimation.ABSOLUTE, yTo - source.getTop());
        trans.setDuration(1000);

        replaceAnimation.addAnimation(scale);
        replaceAnimation.addAnimation(trans);

        source.startAnimation(replaceAnimation);
    }

    static public boolean hasTelephony(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null)
            return false;


        PackageManager pm = context.getPackageManager();

        if (pm == null)
            return false;

        boolean retval = false;
        try {
            Class<?>[] parameters = new Class[1];
            parameters[0] = String.class;
            Method method = pm.getClass().getMethod("hasSystemFeature", parameters);
            Object[] parm = new Object[1];
            parm[0] = "android.hardware.telephony";
            Object retValue = method.invoke(pm, parm);
            if (retValue instanceof Boolean)
                retval = ((Boolean) retValue).booleanValue();
            else
                retval = false;
        } catch (Exception e) {
            retval = false;
        }

        return retval;
    }
}
