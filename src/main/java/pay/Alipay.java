package pay;

import cipher.DigestException;
import cipher.RSAUtils;
import com.alibaba.fastjson.JSONObject;
import net.HttpUtils;
import time.TimeX;

import java.net.URLEncoder;
import java.util.List;

public class Alipay {
    private static final String OPENAPI = "https://openapi.alipay.com/gateway.do";
    private static final String APPID = "your appid";
    private static final String PARTNER = "your parnerid";
    private static final String NOTIFY_URL = "your notify url";
    private static final String PUBLIC_KEY = "your public key";
    private static final String PRIVATE_KEY = "your private key";

    public static String placeOrder(String outTradeNo, Float fee, String subject, String body) {
        try {
            String method = "alipay.trade.app.pay";
            String charset = "utf-8";
            String version = "1.0";
            String timeExpress = "5m";
            String signType = "RSA";
            String timestamp = new TimeX().format("yyyy-MM-dd HH:mm:ss");
            String totalAmount = String.valueOf(fee);

            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", outTradeNo);
            bizContent.put("subject", subject);
            bizContent.put("body", body);
            bizContent.put("timeout_express", timeExpress);
            bizContent.put("total_amount", totalAmount);
            bizContent.put("product_code", "QUICK_MSECURITY_PAY");

            List<Parameter> parameterList = ParameterUtils.list(new Parameter("app_id", APPID),
                    new Parameter("method", method),
                    new Parameter("charset", charset),
                    new Parameter("sign_type", signType),
                    new Parameter("timestamp", timestamp),
                    new Parameter("version", version),
                    new Parameter("notify_url", NOTIFY_URL),
                    new Parameter("biz_content", bizContent.toJSONString()));
            ParameterUtils.sort(parameterList);

            String concatParameterStr = ParameterUtils.concat(parameterList);
            String sign = URLEncoder.encode(rsaSign(concatParameterStr));
            String orderInfo = concatParameterStr + "&sign=" + sign;
            return orderInfo;
        } catch (Exception e) {
            throw new AlipayExeception(e);
        }
    }

    public static AlipayOrder queryOrder(String outTradeNo) {
        try {
            String method = "alipay.trade.query";
            String charset = "utf-8";
            String version = "1.0";
            String signType = "RSA";
            String timestamp = new TimeX().format("yyyy-MM-dd HH:mm:ss");
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", outTradeNo);

            List<Parameter> parameterList = ParameterUtils.list(
                    new Parameter("app_id", APPID),
                    new Parameter("method", method),
                    new Parameter("charset", charset),
                    new Parameter("sign_type", signType),
                    new Parameter("timestamp", timestamp),
                    new Parameter("version", version),
                    new Parameter("biz_content", bizContent.toJSONString()));
            ParameterUtils.sort(parameterList);

            String concatParameterStr = ParameterUtils.concat(parameterList);
            String sign = rsaSign(concatParameterStr);
            parameterList.add(new Parameter("sign", sign));

            String responseData = HttpUtils.postForm(OPENAPI, parameterList);
            JSONObject reponseJson = JSONObject.parseObject(responseData);
            JSONObject queryResponse = reponseJson.getJSONObject("alipay_trade_query_response");
            String code = queryResponse.getString("code");
            if (!code.equals("10000")) {
                return new AlipayOrder(false, 0f);
            }

            String tradeStatus = queryResponse.getString("trade_status");
            if (!(tradeStatus.equals("TRADE_SUCCESS") || tradeStatus.equals("TRADE_FINISHED"))) {
                return new AlipayOrder(false, 0f);
            }

            Float totalAmount = Float.valueOf(queryResponse.getString("total_amount"));
            return new AlipayOrder(true, totalAmount);
        } catch (Exception e) {
            throw new AlipayExeception(e);
        }
    }

    private static String rsaSign(String preStr) throws DigestException {
        return RSAUtils.sign(preStr, PRIVATE_KEY);
    }

    public static class AlipayOrder {
        public Boolean existed;
        public Float totalFee;

        public AlipayOrder() {
        }

        public AlipayOrder(Boolean existed, Float totalFee) {
            this.existed = existed;
            this.totalFee = totalFee;
        }
    }
}
