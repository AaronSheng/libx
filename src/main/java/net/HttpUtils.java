package net;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpUtils {
    public static String postXml(String url, String xml) throws HttpException {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader(HTTP.CONTENT_TYPE, "text/xml");
            StringEntity stringEntity = new StringEntity(xml, "utf-8");
            stringEntity.setContentEncoding("UTF-8");
            httpPost.setEntity(stringEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            } else {
                throw new HttpException(httpResponse.getStatusLine().getReasonPhrase());
            }
        } catch (Exception e) {
            throw new HttpException(e);
        }
    }

    public static String postForm(String url, List<Parameter> parameters) throws HttpException {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for (Parameter parameter : parameters) {
                nameValuePairs.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
            urlEncodedFormEntity.setContentEncoding("UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            } else {
                throw new HttpException(httpResponse.getStatusLine().getReasonPhrase());
            }
        } catch (Exception e) {
            throw new HttpException(e);
        }
    }

    public static String get(String url) throws HttpException {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            } else {
                throw new HttpException(httpResponse.getStatusLine().getReasonPhrase());
            }
        } catch (Exception e) {
            throw new HttpException(e);
        }
    }
}
