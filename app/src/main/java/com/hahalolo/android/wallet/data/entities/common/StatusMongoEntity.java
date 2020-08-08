/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.ACCEPTED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.BAD_GATEWAY;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.BAD_REQUEST;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.CONFLICT;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.CREATE;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.DATE_NOT_FIT_FORM;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.EXIST_MAIL;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.EXIST_PAGE;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.EXIST_USER;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.EXPECTATION_FAILED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.FORBIDDEN;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.FOUND;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.FRIENDED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.GATEWAY_TIMEOUT;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.HTTP_VERSION_NOT_SUPPORTED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.INTERNAL_SERVER_ERROR;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.LENGTH_REQUIRE;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.METHOD_NOT_ALLOWED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.MOVED_PERMANENTLY;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.MULTIPLE_CHOICES;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.NON_AUTHORITATIVE_INFORMATION;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.NOT_ACCEPTTABLE;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.NOT_FOUND;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.NOT_IMPLEMENTED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.NOT_MODIFIED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.NO_CONTENT;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.NO_ENTITY;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.NO_PARAM;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.PARTIAL_CONTENT;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.PAYMENT_REQUIRED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.PRECONFITION_FAILED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.PROXY_AUTHENTICATION_REQUIRED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.REQUEST_ENTITY_TOO_LARGE;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.REQUEST_RANGE_NOT_SATISFIABLE;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.REQUEST_TIMEOUT;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.REQUEST_URI_TOO_LONG;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.RESET_CONTENT;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.SEE_OTHER;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.SERVICE_UNAVAILABLE;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.SUCCESS;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.UNAUTHORIZED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.UNKNOW;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.UNSUPPORTED_MEDIA_TYPE;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.UNUSED;
import static com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity.Status.USE_PROXY;

/**
 * @author ndn
 * Created by ndn
 * Created on 7/26/18
 */
public class StatusMongoEntity implements Parcelable {

    @IntDef({SUCCESS, CREATE, ACCEPTED, NON_AUTHORITATIVE_INFORMATION, NO_CONTENT, RESET_CONTENT,
            PARTIAL_CONTENT, MULTIPLE_CHOICES, MOVED_PERMANENTLY, FOUND, SEE_OTHER, NOT_MODIFIED,
            USE_PROXY, UNUSED, BAD_REQUEST, UNAUTHORIZED, PAYMENT_REQUIRED, FORBIDDEN, NOT_FOUND,
            METHOD_NOT_ALLOWED, NOT_ACCEPTTABLE, PROXY_AUTHENTICATION_REQUIRED, REQUEST_TIMEOUT,
            CONFLICT, LENGTH_REQUIRE, PRECONFITION_FAILED, REQUEST_ENTITY_TOO_LARGE, REQUEST_URI_TOO_LONG,
            UNSUPPORTED_MEDIA_TYPE, REQUEST_RANGE_NOT_SATISFIABLE, EXPECTATION_FAILED,
            INTERNAL_SERVER_ERROR, NOT_IMPLEMENTED, BAD_GATEWAY, SERVICE_UNAVAILABLE, GATEWAY_TIMEOUT,
            HTTP_VERSION_NOT_SUPPORTED, NO_ENTITY, NO_PARAM, EXIST_MAIL, EXIST_USER, EXIST_PAGE, FRIENDED,
            DATE_NOT_FIT_FORM, UNKNOW})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {

        // The request has succeeded. The information returned with the response is dependent on the method used in the request
        int SUCCESS = 200;

        // The request has been fulfilled and resulted in a new resource being created.
        // The newly created resource can be referenced by the URI(s) returned in the entity of the response,
        // with the most specific URI for the resource given by a Location header field.
        // The response SHOULD include an entity containing a list of resource characteristics and location(s)
        // from which the user or user agent can choose the one most appropriate.
        // The entity format is specified by the media type given in the Content-TypeAlbum header field
        int CREATE = 201;

        // The request has been fulfilled and resulted in a new resource being created.
        // The newly created resource can be referenced by the URI(s) returned in the entity of the response,
        // with the most specific URI for the resource given by a Location header field.
        // The response SHOULD include an entity containing a list of resource characteristics and location(s)
        // from which the user or user agent can choose the one most appropriate.
        // The entity format is specified by the media type given in the Content-TypeAlbum header field
        int ACCEPTED = 202;

        int NON_AUTHORITATIVE_INFORMATION = 203;

        int NO_CONTENT = 204;

        // The request has been fulfilled and resulted in a new resource being created.
        // The newly created resource can be referenced by the URI(s) returned in the entity of the response,
        // with the most specific URI for the resource given by a Location header field.
        // The response SHOULD include an entity containing a list of resource characteristics and location(s)
        // from which the user or user agent can choose the one most appropriate.
        // The entity format is specified by the media type given in the Content-TypeAlbum header field
        int RESET_CONTENT = 205;

        int PARTIAL_CONTENT = 206;

        int MULTIPLE_CHOICES = 300;

        int MOVED_PERMANENTLY = 301;

        // The requested resource resides temporarily under a different URI.
        // Since the redirection might be altered on occasion, the client SHOULD continue to use the Request-URI for future requests.
        // This response is only cacheable if indicated by a Cache-Control or Expires header field.
        int FOUND = 302;

        int SEE_OTHER = 303;

        int NOT_MODIFIED = 304;

        // The requested resource MUST be accessed through the proxy given by the Location field.
        // The Location field gives the URI of the proxy. The recipient is expected to repeat this single request via the proxy.
        // 305 responses MUST only be generated by origin servers.
        int USE_PROXY = 305;

        // The 306 statusNetwork code was used in a previous version of the specification, is no longer used, and the code is reserved.
        int UNUSED = 306;

        // The server cannot or will not process the request due to an apparent client error
        // (e.g., malformed request syntax, size too large, invalid request message framing, or deceptive request routing).
        int BAD_REQUEST = 400;

        int UNAUTHORIZED = 401;

        int PAYMENT_REQUIRED = 402;

        // The server has not found anything matching the Request-URI.
        // No indication is given of whether the condition is temporary or permanent.
        // The 410 (Gone) statusNetwork code SHOULD be used if the server knows, through some internally configurable mechanism,
        // that an old resource is permanently unavailable and has no forwarding address.
        // This statusNetwork code is commonly used when the server does not wish to reveal exactly why the request has been refused, or when no other response is applicable.
        int FORBIDDEN = 403;

        int NOT_FOUND = 404;

        int METHOD_NOT_ALLOWED = 405;

        // he resource identified by the request is only capable of generating response entities
        // which have content characteristics not acceptable according to the accept headers sent in the request.
        int NOT_ACCEPTTABLE = 406;

        int PROXY_AUTHENTICATION_REQUIRED = 407;

        // The client did not produce a request within the time that the server was prepared to wait.
        // The client MAY repeat the request without modifications at any later time.
        int REQUEST_TIMEOUT = 408;

        // The request could not be completed due to a conflict with the current state of the resource.
        // This code is only allowed in situations where it is expected that the user might be able to resolve the conflict and resubmit the request.
        // The response body SHOULD include enough
        int CONFLICT = 409;

        // The server refuses to accept the request without a defined Content- Length.
        // The client MAY repeat the request if it adds a valid Content-Length header field
        // containing the length of the message-body in the request message.
        int LENGTH_REQUIRE = 411;

        int PRECONFITION_FAILED = 412;

        // The server is refusing to process a request because the request entity is larger than the server is willing or able to process.
        // The server MAY close the connection to prevent the client from continuing the request
        int REQUEST_ENTITY_TOO_LARGE = 413;

        // The server is refusing to service the request because the Request-URI is longer than the server is willing to interpret.
        // This rare condition is only likely to occur when a client has improperly converted a POST request to a GET request
        // with long query information, when the client has descended into a URI "black hole" of redirection
        // (e.g., a redirected URI prefix that points to a suffix of itself), or when the server is under attack by a client
        // attempting to exploit security holes present in some servers using fixed-length buffers for reading or manipulating the Request-URI
        int REQUEST_URI_TOO_LONG = 414;

        // The server is refusing to service the request because the entity of the request is in a format not supported by the requested resource for the requested method.
        int UNSUPPORTED_MEDIA_TYPE = 415;

        int REQUEST_RANGE_NOT_SATISFIABLE = 416;

        // The expectation given in an Expect request-header field (see section 14.20) could not be met by this server, or, if the server is a proxy,
        // the server has unambiguous evidence that the request could not be met by the next-hop server.
        int EXPECTATION_FAILED = 417;

        // The server encountered an unexpected condition which prevented it from fulfilling the request.
        int INTERNAL_SERVER_ERROR = 500;

        // The server does not support the functionality required to fulfill the request.
        // This is the appropriate response when the server does not recognize the request
        // method and is not capable of supporting it for any resource.
        int NOT_IMPLEMENTED = 501;

        int BAD_GATEWAY = 502;

        // The server is currently unable to handle the request due to a temporary overloading or maintenance of the server.
        // The implication is that this is a temporary condition which will be alleviated after some delay.
        // If known, the length of the delay MAY be indicated in a Retry-After header.
        // If no Retry-After is given, the client SHOULD handle the response as it would for a 500 response.
        int SERVICE_UNAVAILABLE = 503;

        int GATEWAY_TIMEOUT = 504;

        int HTTP_VERSION_NOT_SUPPORTED = 505;

        /*
            Hahalolo
         */

        int BUSINESS_VERIFY =  5003;

        int NO_ENTITY = 600;

        int NO_PARAM = 601;

        int EXIST_MAIL = 701;

        int EXIST_USER = 702;

        int EXIST_PAGE = 703;

        int FRIENDED = 1001;

        int DATE_NOT_FIT_FORM = 2010;

        int UNKNOW = -1;
    }

    @SerializedName("success")
    @Expose
    public boolean success;

    @SerializedName("errors")
    @Expose
    public String errors;

    @SerializedName("code")
    @Expose
    public int code;

    public StatusMongoEntity() {
    }

    private StatusMongoEntity(int code) {
        this.code = code;
    }

    public static StatusMongoEntity error(int code) {
        return new StatusMongoEntity(code);
    }

    public StatusMongoEntity(boolean success, String errors, int code) {
        this.success = success;
        this.errors = errors;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isUnauthorized() {
        return code == Status.UNAUTHORIZED;
    }

    public boolean isSuccessCode() {
        return code >= Status.SUCCESS && code < Status.MULTIPLE_CHOICES;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.errors);
        dest.writeInt(this.code);
    }

    protected StatusMongoEntity(Parcel in) {
        this.success = in.readByte() != 0;
        this.errors = in.readString();
        this.code = in.readInt();
    }

    public static final Creator<StatusMongoEntity> CREATOR = new Creator<StatusMongoEntity>() {
        @Override
        public StatusMongoEntity createFromParcel(Parcel source) {
            return new StatusMongoEntity(source);
        }

        @Override
        public StatusMongoEntity[] newArray(int size) {
            return new StatusMongoEntity[size];
        }
    };

    @Override
    public String toString() {
        return "StatusEntity{" +
                "success=" + success +
                ", errors='" + errors + '\'' +
                ", code=" + code +
                '}';
    }
}
