/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.api.wallet;

import android.arch.lifecycle.LiveData;

import com.hahalolo.android.wallet.data.api.utils.ApiResponse;
import com.hahalolo.android.wallet.data.entities.wallet.PayoutBody;
import com.hahalolo.android.wallet.data.entities.wallet.PayoutRequestBody;
import com.hahalolo.android.wallet.data.entities.wallet.ResponseN300;
import com.hahalolo.android.wallet.data.entities.wallet.WalletBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author ngannd
 * Create by ngannd on 18/05/2019
 */
public interface WalletMongoService {

    /**
     * 'Create payout acc for wallet user
     * + Validate payment account
     * - Trường hợp quốc gia là Viet Nam thì ko cần check mã số thuế, số an sinh xã hội và địa chỉ 2
     * - Trường hợp quốc gia là khác Việt Nam thì bắt buộc nhập tất cả thông tin form input
     * + Check exists wallet info(Nếu là ví business thì check exists business)
     * + Update payment account info into N300#Fn450
     *
     * @return {@link ResponseN300} {@link com.hahalolo.android.wallet.data.entities.wallet.N300}
     */
    @POST("api-rest-wallet/service/wallet/createPayout/v2")
    LiveData<ApiResponse<ResponseN300>> createPayout(@Body PayoutBody body,
                                                     @Query("access_token") String accessToken);

    /**
     * (Case nn317 is null then set default nn317: 0(ví cá nhân))
     * Create wallet for User
     * - validation param
     * - Check exist vi (cá nhân và doanh nghiệp)
     * - Regist info
     *
     * @return {@link ResponseN300} {@link com.hahalolo.android.wallet.data.entities.wallet.N300}
     */
    @POST("api-rest-wallet/service/wallet/createWallet/v2")
    LiveData<ApiResponse<ResponseN300>> createWallet(@Body WalletBody body,
                                                     @Query("access_token") String accessToken);

    /**
     * Wallet info
     * .nn302:CurrentAvailable
     * .nn303:IncomeWaiting
     * .nn304:IncomePending
     * .nn305:IncomeHolding
     * .nn306:OutcomeWaiting
     * .nn307:OutcomePending
     * .nn308:OutcomeHolding
     * .nn317: (0: ví cá nhân, 1: ví doanh nghiệp)
     * .fn302 : history order
     * ..ph100: type order(Hotel, tour, shop)
     * ..dl146 : Ngày cập nhật tiền
     * ..nn306: số tiền
     * ..nn303 : nội dung (0 : user thanh toán order, 1 Confirm order)
     * ..pp100 : page ID
     *
     * @param id          Wallet ID
     * @param uClientId
     * @param accessToken
     * @return
     */
    @GET("api-rest-wallet/service/wallet/walletInfo/v1")
    LiveData<ApiResponse<ResponseN300>> walletInfo(@Query("id") String id,
                                                   @Query("uClientId") String uClientId,
                                                   @Query("access_token") String accessToken);

    /**
     * Hiển thị tất cả các paypout Account của Wallet
     * Không hiển thị acc đã bị xóa logic (dl145 khác null), hiển thị thông tin default account đầu tiên
     * Thông tin hiển thị:
     * .fn450: object payout account info
     * ..nv451 : Country Name
     * ..nv455 : AccountName
     * ..nv478 : email
     * ..nn452 : Type account (1: ca nhan, 2: doanh nghiep)
     * ..nn462 : PaymentInfo (1: Bank transfer, 2: paypal ...)
     * ..nn481 : Defaut account (1: defaut, 0 : un-defaut)
     *
     * @param id          Wallet ID
     * @param uClientId
     * @param accessToken
     * @return
     */
    @GET("api-rest-wallet/service/wallet/payoutInfo/v1")
    LiveData<ApiResponse<ResponseN300>> payoutInfo(@Query("id") String id,
                                                   @Query("uClientId") String uClientId,
                                                   @Query("access_token") String accessToken);

    /**
     * Hiển thị chi tiết 1 payout account
     *
     * @param pn300       Wallet ID
     * @param pn450       Payout ID
     * @param uClientId
     * @param accessToken
     * @return
     */
    @GET("api-rest-wallet/service/wallet/payoutById/v1")
    LiveData<ApiResponse<ResponseN300>> payoutById(@Query("pn300") String pn300,
                                                   @Query("pn450") String pn450,
                                                   @Query("uClientId") String uClientId,
                                                   @Query("access_token") String accessToken);

    /**
     * Xóa logic thông tin payout account (update dl145)
     * Chỉ có thể xóa được Tài khoản khi không tồn tại bất cứ Request payout nào đang ở trạng thái chờ (chưa xử lý)
     *
     * @param body
     * @param accessToken
     * @return
     */
    @POST("api-rest-wallet/service/wallet/removePayoutAcc/v1")
    LiveData<ApiResponse<ResponseN300>> removePayoutAcc(@Body PayoutBody body,
                                                        @Query("access_token") String accessToken);

    /**
     * Set default cho payout account được chọn.
     * Account được set default sẽ hiển thị đầu tiên của list
     *
     * @param body
     * @param accessToken
     * @return
     */
    @POST("api-rest-wallet/service/wallet/setDefaultAcc/v1")
    LiveData<ApiResponse<ResponseN300>> setDefaultAcc(@Body PayoutBody body,
                                                      @Query("access_token") String accessToken);

    /**
     * Send request rút tiền tới hệ thống Hahalolo
     * - Check bắt buộc: pn300, pn100, nn503, currency, nv507, pn450
     * - Check số tiền request >= 50$ (code error 8002)
     * - Check số tiền yêu cầu vượt quá số tiền khả dụng của ví (code error : 8001)
     * - Check số tiền khả dụng của ví khong tồn tại hoặc = 0 (code error : 8000)
     *
     * @param body
     * @param accessToken
     * @return
     */
    @POST("api-rest-wallet/service/wallet/sendRequest/v1")
    LiveData<ApiResponse<ResponseN300>> sendRequest(@Body PayoutRequestBody body,
                                                    @Query("access_token") String accessToken);

    /**
     * Get lịch sử gởi request payout
     * Check bắt buộc: pn100, pn300
     * Thông tin hiển thị:
     * .pn450 : ID payout acc
     * .nd501 : Ngày request
     * .nn503 : số tiền request
     * .nn505 : Status (0: Waiting, 1: Approved, 2: Processing, 3: Denied, 4: Done)
     * Loader: Hahalolo Payment Service (giá tri mặc định)
     *
     * @param pn100
     * @param pn300
     * @param strDate
     * @param endDate
     * @param limit
     * @param uClientId
     * @param accessToken
     * @return
     */
    @GET("api-rest-wallet/service/wallet/historyPayout/v1")
    Call<ResponseN300> historyPayout(@Query("pn100") String pn100,
                                     @Query("pn300") String pn300,
                                     @Query("strDate") String strDate,
                                     @Query("endDate") String endDate,
                                     @Query("limit") String limit,
                                     @Query("uClientId") String uClientId,
                                     @Query("access_token") String accessToken);

    /**
     * Get lịch sử gởi request payout
     * Check bắt buộc: pn100, pn300
     * Thông tin hiển thị:
     * .pn450 : ID payout acc
     * .nd501 : Ngày request
     * .nn503 : số tiền request
     * .nn505 : Status (0: Waiting, 1: Approved, 2: Processing, 3: Denied, 4: Done)
     * Loader: Hahalolo Payment Service (giá tri mặc định)
     *
     * @param pn100
     * @param pn300
     * @param strDate
     * @param endDate
     * @param limit
     * @param offset
     * @param uClientId
     * @param accessToken
     * @return
     */
    @GET("api-rest-wallet/service/wallet/historyPayout/v1")
    Call<ResponseN300> historyPayoutAfter(@Query("pn100") String pn100,
                                          @Query("pn300") String pn300,
                                          @Query("strDate") String strDate,
                                          @Query("endDate") String endDate,
                                          @Query("limit") String limit,
                                          @Query("offset") String offset,
                                          @Query("uClientId") String uClientId,
                                          @Query("access_token") String accessToken);
}
