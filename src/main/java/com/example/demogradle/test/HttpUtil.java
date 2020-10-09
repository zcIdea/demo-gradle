package com.example.demogradle.test;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final OkHttpClient client = OkHttpClientObject.CLIENT.getClientInstance();

    public static String get(String url, Map<String, String> header) {
        Request.Builder request = new Request.Builder().url(url).get();
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.get e:[{}]", e);
        }
        return null;
    }

    public static byte[] getBytes(String url, Map<String, String> header) {
        Request.Builder request = new Request.Builder().url(url).get();
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.bytes();
        } catch (Exception e) {
            logger.error("HttpUtil.getBytes e:[{}]", e);
        }
        return null;
    }

    public static String delete(String url, Map<String, String> header) {
        Request.Builder request = new Request.Builder().url(url).delete();
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.delete e:[{}]", e);
        }
        return null;
    }

    public static String post(String url, Map<String, String> param, Map<String, String> header) {
        Request.Builder request = new Request.Builder().url(url);
        FormBody.Builder formBody = new FormBody.Builder();
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    formBody.add(entry.getKey(), entry.getValue());
                }
            }
        }
        request.post(formBody.build());
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.post e:[{}]", e);
        }
        return null;
    }

    public static String postBody(String url, String json, Map<String, String> header) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request.Builder request = new Request.Builder().url(url).post(body);
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.postBody e:[{}]", e);
        }
        return null;
    }

    public static String patchBody(String url, String json, Map<String, String> header) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request.Builder request = new Request.Builder().url(url).patch(body);
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.patchBody e:[{}]", e);
        }
        return null;
    }

    public static String putBody(String url, String json, Map<String, String> header) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request.Builder request = new Request.Builder().url(url).put(body);
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.putBody e:[{}]", e);
        }
        return null;
    }

    private static void addHeaders(Request.Builder request, Map<String, String> header) {
        if (header != null && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }
    private static String postRequestBody(String url, Map<String, ?> headers, RequestBody body) throws IOException {
        final Request.Builder builder = getBuilderWithHeaders(headers);
        Request request = builder.url(url).post(body).build();
        return getResult(request);
    }
    private static String getResult(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            final String result = response.body().string();
            logger.debug("result: {}", result);
            return result;
        }
    }
    private static Request.Builder getBuilderWithHeaders(Map<String, ?> headers) {
        final Request.Builder builder = new Request.Builder();
        if (!CollectionUtils.isEmpty(headers))
            headers.forEach((k, v) -> {
                if (k != null && v != null) builder.header(k, v.toString());
            });
        return builder;
    }

    public static String postFile(String url, Map<String, ?> formParams, Map<String, ?> headers) throws IOException {
        final MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (!CollectionUtils.isEmpty(formParams)) {
            formParams.forEach((k, v) -> {
                if (k != null && v != null) {
                    if (v instanceof File) {
                        final File file = (File) v;
                        builder.addFormDataPart(k, file.getName(), RequestBody.create(null,file));
                    } else {
                        builder.addFormDataPart(k, String.valueOf(v));
                    }
                }
            });
        }
        return postRequestBody(url, headers, builder.build());
    }
    public static String postFile(String url, String fieldName, String fileName, File file, Map<String, ?> headers) throws IOException {
        final MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart(fieldName, fileName, RequestBody.create(null,file));
        return postRequestBody(url, headers, builder.build());
    }

    public static void main(String[] args) {

        String url="https://futures-test.yijiantongying.com/JytfBankApi/user/spdb/queryCreate";
        String url_bank="https://futures-test.yijiantongying.com/JytfBankApi/spdb/queryCardMoney";
        Map param=new HashMap(16);
        param.put("user_id","110");
        param.put("ip", "192.168.16.187");
        param.put("sign",Md5Demo.createSign(param, "zcy@0808"));

        String post = post(url_bank, param, null);
        System.out.println(post);


    }

}
