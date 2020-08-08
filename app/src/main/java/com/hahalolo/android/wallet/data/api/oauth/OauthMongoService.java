/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.api.oauth;

import android.arch.lifecycle.LiveData;

import com.hahalolo.android.wallet.data.api.utils.ApiResponse;
import com.hahalolo.android.wallet.data.entities.oauth.LogoutResponseEntity;
import com.hahalolo.android.wallet.data.entities.token.TokenEntity;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/15/18.
 * <p>
 * Oauth service
 */
public interface OauthMongoService {

    /**
     * Update lại thông tin gọi API login
     * VD: Login bằng Postman
     * -> Refer Sheet[Param]
     * Step sau khi login. Nếu access_token có giá trị = "n-active" thì sẽ di chuyển đến màn hình verifyAccount.
     * Else Sẽ tự động login vào hệ thống.
     * - Hệ thống sẽ tự động kiểm tra user đó đã send Code lần nào chưa. Nếu send code rồi thì sẽ ko send lại. =>
     * Ở chức năng verifyAccount có button cho người ta send lại lần nữa.
     * - Thông tin truyền cho verifyAccount get từ info Login
     * Khoản cách mỗi lần send code: Đối với phone thì 5phut 1 lần.
     * Các param truyền vào api verifyAccount được get từ thông tin login. Xem mô tả api verifyAccount bên dưới.
     * Sau khi verify thành công. Ở client tự set lại access_token cho session user ( thông tin access_token được trả ra ở hàm verify ).
     * khi đó tự động chuyển đến màn hình sau khi login ( newsfeed)
     *
     * @return {@link TokenEntity}
     */
    @POST("/api-rest-oauth/oauth/token?grant_type=client_credentials")
    LiveData<ApiResponse<TokenEntity>> signIn();

    /**
     * Logout hahalolo
     *
     * @param accessToken access token
     * @return {@link LogoutResponseEntity}
     */
    @GET("api-rest-oauth/service/user/logout")
    LiveData<ApiResponse<LogoutResponseEntity>> logout(@Query("access_token") String accessToken);
}
