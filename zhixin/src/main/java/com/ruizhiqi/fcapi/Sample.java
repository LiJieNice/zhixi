package com.ruizhiqi.fcapi;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Sample {

    static final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static void main(String[] args) {
        // sample of normal interface
        final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = sdf.format(new Date());
        System.out.println(utcTime);
        sampleOfNormalInterface(utcTime);
    }
    private static Map<String, String> createHeaders(String utcTime) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-bce-date", utcTime);
        headers.put("host", "sem.baidubce.com");
        return headers;
    }

    private static void sampleOfNormalInterface(String utcTime) {
        Signer signer = new Signer();
        signer.withAccessKey("fef449a6fd884a968eef62f9c4681736")//AK
                .withSecretKey("05ef4dc93c8b40e5b0b7a792cf4cbf43")//SK
                .withTimestamp(utcTime)
                .withMethod("POST")
                .withVersion("1") // now must be 1
                .withExpire(1800)
                .withUri("/v1/cloud/PreviewService/getPreview")
                .withSignHeaders(createHeaders(utcTime)); // 只传递需要签名的header
        String authorization = "";
        try {
            String signature = signer.genSignature();
            System.out.println(signature);
        } catch (SignerException e) {
            System.out.println(e.getMessage());
        }
        try {
            authorization = signer.genAuthorization();
            System.out.println(authorization);
        } catch (SignerException e) {
            System.out.println(e.getMessage());
        }

        CloseableHttpClient httpClient = new DefaultHttpClient();
        try {

            // Creating an instance of HttpPost.
            HttpPost httpost = new HttpPost(
                    "http://sem.baidubce.com/v1/cloud/PreviewService/getPreview");

            httpost.addHeader("Host", "sem.baidubce.com");
            httpost.addHeader("Content-Type", "application/json");
            httpost.addHeader("x-bce-date", utcTime);
            httpost.addHeader("authorization", authorization);
            StringEntity strEntity = new StringEntity(
                    "{\"body\":{\"keyWords\":[\"鲜花\"],\"device\":0,\"region\":1000},\"header\":{\"opUsername\":\"OPUSERNAME\",\"opPassword\":\"OPPASSWORD\", \"tgUsername\": \"TGUSERNAME\", \"tgPassword\": \"TGPASSWORD\"}}",
                    Consts.UTF_8);
            httpost.setEntity(strEntity);

            // Executing the request.
            HttpResponse response = httpClient.execute(httpost);
            BufferedReader rd;
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = null;
            StringBuffer result = new StringBuffer();
            try {
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(result);


        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
