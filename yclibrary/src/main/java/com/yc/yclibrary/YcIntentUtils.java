package com.yc.yclibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Intent相关 YcIntentUtils
 * <p>
 * getInstallAppIntent         : 获取安装App(支持7.0)的意图
 * getUninstallAppIntent       : 获取卸载App的意图
 * getLaunchAppIntent           : 获取打开App的意图
 * getAppInfoIntent            : 获取App信息的意图
 * getShareInfoIntent          : 获取App信息分享的意图
 * getIntentByPackageName      : 根据包名获取意图
 * getComponentNameIntent      : 获取其他应用的Intent
 * getAppDetailsSettingsIntent: 获取 App 具体设置的意图
 * getShareTextIntent         : 获取分享文本的意图
 * getShareImageIntent        : 获取分享图片的意图
 * getComponentIntent         : 获取其他应用组件的意图
 * getShutdownIntent          : 获取关机的意图
 * getCaptureIntent           : 获取拍照的意图
 */
public class YcIntentUtils {

    /**
     * 获取安装App(支持7.0)的意图
     *
     * @param context
     * @param filePath
     * @return
     */
    public static Intent getInstallAppIntent(Context context, String filePath) {
        //apk文件的本地路径
        File apkfile = new File(filePath);
        if (!apkfile.exists()) {
            return null;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri contentUri = YcFileUtils.getUriForFile(context, apkfile);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * 获取卸载App的意图
     *
     * @param packageName 包名
     * @return 意图
     */
    public static Intent getUninstallAppIntent(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取打开App的意图
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 意图
     */
    public static Intent getLaunchAppIntent(Context context, String packageName) {
        return getIntentByPackageName(context, packageName);
    }

    /**
     * 根据包名获取意图
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 意图
     */
    private static Intent getIntentByPackageName(Context context, String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * 获取App信息的意图
     *
     * @param packageName 包名
     * @return 意图
     */
    public static Intent getAppInfoIntent(String packageName) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        return intent.setData(Uri.parse("package:" + packageName));
    }

    /**
     * 获取App信息分享的意图
     *
     * @param info 分享信息
     * @return 意图
     */
    public static Intent getShareInfoIntent(String info) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        return intent.putExtra(Intent.EXTRA_TEXT, info);
    }

    /**
     * 获取其他应用的Intent
     *
     * @param packageName 包名
     * @param className   全类名
     * @return 意图
     */
    public static Intent getComponentNameIntent(String packageName, String className) {
        return getComponentNameIntent(packageName, className, null);
    }

    /**
     * 获取其他应用的Intent
     *
     * @param packageName 包名
     * @param className   全类名
     * @return 意图
     */
    public static Intent getComponentNameIntent(String packageName, String className, Bundle bundle) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null)
            intent.putExtras(bundle);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取应用详情页面具体设置 intent
     *
     * @return
     */
    public static Intent getAppDetailsSettingsIntent(Context mContext) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", mContext.getPackageName());
        }
        return localIntent;
    }

    /**
     * 获取应用详情页面具体设置 intent
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getAppDetailsSettingsIntent(String packageName) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", packageName, null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", packageName);
        }
        return localIntent;
    }

    /**
     * 是否获取新的意图
     *
     * @param intent
     * @param isNewTask
     * @return
     */
    private static Intent getIntent(final Intent intent, final boolean isNewTask) {
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }

    /**
     * Return 获取分享文本的意图.
     *
     * @param content   The content.
     * @param isNewTask 是否是新的任务.
     * @return 获取分享文本的意图
     */

    public static Intent getShareTextIntent(final String content, final boolean isNewTask) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        return getIntent(intent, isNewTask);
    }

    /**
     * 获取分享图片的意图
     *
     * @param content
     * @param imagePath 图片路径.
     * @param isNewTask 是否是新的任务.
     * @return 获取分享图片的意图
     */
    public static Intent getShareImageIntent(final String content,
                                             final String imagePath,
                                             final boolean isNewTask) {
        if (imagePath == null || imagePath.length() == 0)
            return null;
        return getShareImageIntent(content, new File(imagePath), isNewTask);
    }

    /**
     * 获取分享图片的意图
     *
     * @param content
     * @param image     图片文件.
     * @param isNewTask 是否是新的任务.
     * @return 获取分享图片的意图
     */
    public static Intent getShareImageIntent(final String content,
                                             final File image,
                                             final boolean isNewTask) {
        if (image != null && image.isFile())
            return null;
        return getShareImageIntent(content, Uri.fromFile(image), isNewTask);
    }

    /**
     * 获取分享图片的意图
     *
     * @param content
     * @param uri       图片的 uri
     * @param isNewTask 是否是新的任务.
     * @return 获取分享图片的意图
     */
    public static Intent getShareImageIntent(final String content,
                                             final Uri uri,
                                             final boolean isNewTask) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        return getIntent(intent, isNewTask);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName The name of the package.
     * @param className   The name of class.
     * @return the intent of component
     */
    public static Intent getComponentIntent(final String packageName, final String className) {
        return getComponentIntent(packageName, className, null, false);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName The name of the package.
     * @param className   The name of class.
     * @param isNewTask   True to add flag of new task, false otherwise.
     * @return the intent of component
     */
    public static Intent getComponentIntent(final String packageName,
                                            final String className,
                                            final boolean isNewTask) {
        return getComponentIntent(packageName, className, null, isNewTask);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName The name of the package.
     * @param className   The name of class.
     * @param bundle      The Bundle of extras to add to this intent.
     * @return the intent of component
     */
    public static Intent getComponentIntent(final String packageName,
                                            final String className,
                                            final Bundle bundle) {
        return getComponentIntent(packageName, className, bundle, false);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName The name of the package.
     * @param className   The name of class.
     * @param bundle      The Bundle of extras to add to this intent.
     * @param isNewTask   True to add flag of new task, false otherwise.
     * @return the intent of component
     */
    public static Intent getComponentIntent(final String packageName, final String className, final Bundle bundle, final boolean isNewTask) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null)
            intent.putExtras(bundle);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return getIntent(intent, isNewTask);
    }

    /**
     * 获取关机的意图
     * <p>Requires root permission
     * or hold {@code android:sharedUserId="android.uid.system"},
     * {@code <uses-permission android:name="android.permission.SHUTDOWN/>}
     * in manifest.</p>
     *
     * @return the intent of shutdown
     */
    public static Intent getShutdownIntent() {
        return getShutdownIntent(false);
    }

    /**
     * 获取关机的意图
     * <p>Requires root permission
     * or hold {@code android:sharedUserId="android.uid.system"},
     * {@code <uses-permission android:name="android.permission.SHUTDOWN/>}
     * in manifest.</p>
     *
     * @param isNewTask True to add flag of new task, false otherwise.
     * @return the intent of shutdown
     */
    public static Intent getShutdownIntent(final boolean isNewTask) {
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        return getIntent(intent, isNewTask);
    }

    /**
     * 获取拍照的意图
     */
    public static Intent getCaptureIntent(final String imagePath, final boolean isNewTask) {
        if (imagePath == null || imagePath.length() == 0)
            return null;
        return getCaptureIntent(new File(imagePath), isNewTask);
    }

    /**
     * 获取拍照的意图
     */
    public static Intent getCaptureIntent(final File file, final boolean isNewTask) {
        if (file != null && file.isFile())
            return null;
        return getCaptureIntent(Uri.fromFile(file), isNewTask);
    }

    /**
     * 获取拍照的意图
     *
     * @param outUri    The uri of output.
     * @param isNewTask True to add flag of new task, false otherwise.
     * @return the intent of capture
     */
    public static Intent getCaptureIntent(final Uri outUri, final boolean isNewTask) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        return getIntent(intent, isNewTask);
    }

}
