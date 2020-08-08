/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.FontRequestEmojiCompatConfig;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.v4.provider.FontRequest;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.hahalolo.android.wallet.BuildConfig;
import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.data.entities.currency.CurrencyEntity;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;
import com.hahalolo.android.wallet.di.DaggerAppComponent;
import com.hahalolo.android.wallet.presentation.login.WalletLoginAct;
import com.hahalolo.android.wallet.utils.ActivityUtils;
import com.hahalolo.android.wallet.utils.Utils;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * @author ngannd
 * Create by ngannd on 22/05/2019
 */
public class HahaloloWalletApplication extends DaggerApplication {

    private static HahaloloWalletApplication mInstance;

    private TokenEntity token;

    private List<CurrencyEntity> currencies = new ArrayList<>();
    private CurrencyEntity targetCurrency;
    private static String walletId;

    /**
     * Change this to {@code false} when you want to use the downloadable Emoji font.
     */
    private static final boolean USE_BUNDLED_EMOJI = false;

    @Override
    public void onCreate() {
        super.onCreate();
        //strictMode();
        //leakCanary();
        stetho();

        mInstance = this;

        // Init Fresco load image
        Fresco.initialize(this);

        // Init emoji
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        configEmoji();

        // Init Utils Common Module
        Utils.init(this);
    }

    public static String getWalletId() {
        return walletId;
    }

    public static void updateWalletId(String id) {
        walletId = id;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // Init multi dex
        MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    public static synchronized HahaloloWalletApplication getInstance() {
        return mInstance;
    }

    /**
     * Get target token of user login
     *
     * @return {@link TokenEntity}
     */
    @Nullable
    public TokenEntity getToken() {
//        if (token == null) {
//            try {
//                restartApp();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        } else if (token.expired()) {
//            try {
//                loginApp();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
        return token;
    }

    /**
     * Khi xảy lỗi token, thì chạy lại app để khởi tạo lại token
     */
    public void restartApp() {
        Intent intent = new Intent(this, WalletLoginAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ActivityUtils.finishAllActivities();
    }

    /**
     * Nếu token đã bị hết hạn thì cho user đăng nhập lại
     */
    public void loginApp() {
        Intent intent = new Intent(this, WalletLoginAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ActivityUtils.finishAllActivities();
    }

    public String getUserId() {
        if (getToken() != null) {
            return getToken().getUserId();
        }
        return "";
    }

    public boolean isOwner(String id) {
        if (getToken() == null) {
            return false;
        }
        return TextUtils.equals(getUserId(), id);
    }

    public String getUserAvatar() {
        if (getToken() != null) {
            return getToken().getAvatar();
        }
        return "";
    }

    public String getAccessToken() {
        if (getToken() != null) {
            return getToken().getAccessToken();
        }
        return "";
    }

    public String getUserName() {
        if (getToken() != null) {
            return TextUtils.isEmpty(getToken().getFullname())
                    ? getString(R.string.name_format, getToken().getFirstname(), getToken().getLastname())
                    : getToken().getFullname();
        }
        return "";
    }

    public String getEmail() {
        if (getToken() != null) {
            return getToken().getMail();
        }
        return "";
    }

    public String getGender() {
        if (getToken() != null) {
            return getToken().getGender();
        }
        return "";
    }

    public long getBirthDay() {
        if (getToken() != null) {
            return getToken().nd106;
        }
        return 0;
    }

    /**
     * Set target {@link TokenEntity} of user login
     * UpdateChat all handler relate to token of user login
     *
     * @param token {@link TokenEntity}
     */
    public void setToken(@NonNull TokenEntity token) {
        this.token = token;
    }

    /**
     * Config for TextView Emoji
     */
    private void configEmoji() {
        final EmojiCompat.Config config;
        if (USE_BUNDLED_EMOJI) {
            // Use the bundled font for EmojiCompat
            config = new BundledEmojiCompatConfig(getApplicationContext());
        } else {
            // Use a downloadable font for EmojiCompat
            config = new FontRequestEmojiCompatConfig(getApplicationContext(), new FontRequest(
                    "com.google.android.gms.fonts",
                    "com.google.android.gms",
                    "Noto Color Emoji Compat",
                    R.array.com_google_android_gms_fonts_certs))
                    .setReplaceAll(true)
                    .registerInitCallback(new EmojiCompat.InitCallback() {
                        @Override
                        public void onInitialized() {
                        }

                        @Override
                        public void onFailed(@Nullable Throwable throwable) {
                            if (throwable != null) {
                                throwable.printStackTrace();
                            }
                        }
                    });
        }
        EmojiCompat.init(config);
    }

    /**
     * Get list currencies of Halo application
     *
     * @return List {@link CurrencyEntity}
     */
    public List<CurrencyEntity> getCurrencies() {
        return currencies;
    }

    /**
     * Set List {@link CurrencyEntity} for application
     *
     * @param currencies List {@link CurrencyEntity}
     */
    public void setCurrencies(List<CurrencyEntity> currencies) {
        this.currencies = currencies;
    }

    /**
     * Target currency of user login
     *
     * @return {@link CurrencyEntity}
     */
    public CurrencyEntity getTargetCurrency() {
        if (targetCurrency == null) {
            targetCurrency = CurrencyEntity.vnd();
        }
        return targetCurrency;
    }

    /**
     * Set target currency of user login
     *
     * @param targetCurrency {@link CurrencyEntity}
     */
    public void setTargetCurrency(CurrencyEntity targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    /**
     * https://developer.android.com/reference/android/os/StrictMode
     * <p>
     * StrictMode is a developer tool which detects things you might be doing by accident and brings them to your attention so you can fix them.
     * <p>
     * StrictMode is most commonly used to catch accidental disk or network access on the application's main thread,
     * where UI operations are received and animations take place.
     * Keeping disk and network operations off the main thread makes for much smoother,
     * more responsive applications. By keeping your application's main thread responsive,
     * you also prevent ANR dialogs from being shown to users.
     * <p>
     * Note that even though an Android device's disk is often on flash memory, many devices run a filesystem on top of that memory with very limited concurrency.
     * It's often the case that almost all disk accesses are fast, but may in individual cases be dramatically slower when certain I/O is happening in the background from other processes.
     * If possible, it's best to assume that such things are not fast.
     * <p>
     * You can decide what should happen when a violation is detected.
     * For example, using StrictMode.ThreadPolicy.Builder.penaltyLog() you can watch the output of adb logcat while you use your application to see the violations as they happen.
     * <p>
     * If you find violations that you feel are problematic, there are a variety of tools to help solve them: threads, Handler, AsyncTask, IntentService, etc.
     * But don't feel compelled to fix everything that StrictMode finds. In particular, many cases of disk access are often necessary during the normal activity lifecycle.
     * Use StrictMode to find things you did by accident. Network requests on the UI thread are almost always a problem, though.
     * You can decide what should happen when a violation is detected. For example, using StrictMode.ThreadPolicy.Builder.penaltyLog() you can watch the output of adb logcat while you use your application to see the violations as they happen.
     * <p>
     * If you find violations that you feel are problematic, there are a variety of tools to help solve them: threads, Handler, AsyncTask, IntentService, etc.
     * But don't feel compelled to fix everything that StrictMode finds.
     * In particular, many cases of disk access are often necessary during the normal activity lifecycle.
     * Use StrictMode to find things you did by accident. Network requests on the UI thread are almost always a problem, though.
     */
    private void strictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
    }

    private void leakCanary() {
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
    }

    /**
     * The integration with the Chrome DevTools frontend is implemented using a client/server protocol
     * which the Stetho software provides for your application. Once your application is integrated,
     * simply navigate to chrome://inspect and click "Inspect" to get started!
     */
    private void stetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
