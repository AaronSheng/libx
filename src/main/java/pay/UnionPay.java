package pay;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import time.TimeX;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * UnionPay class depends on unionpay sdk.
 * So you should include unionpay sdk first and configure as required.
 */
public class UnionPay {
    private static final String MERID = "";
    private static final String FRONT_URL = "";
    private static final String BACK_URL = "";

    public static String placeOrder(String outTradeNo, Long fee) {
        try {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("version", "5.0.0");
            reqData.put("encoding", "UTF-8");
            reqData.put("signMethod", "01");
            reqData.put("txnType", "01");
            reqData.put("txnSubType", "01");
            reqData.put("bizType", "000201");
            reqData.put("channelType", "08");
            reqData.put("merId", MERID);
            reqData.put("accessType", "0");
            reqData.put("orderId", outTradeNo);
            reqData.put("backUrl", BACK_URL);
            reqData.put("currencyCode", "156");
            reqData.put("txnAmt", String.valueOf(fee));
            reqData.put("txnTime", new TimeX().format("yyyyMMddHHmmss"));
            reqData.put("payTimeout", new TimeX().getTimeXAfterMinutes(15).format("yyyyMMddHHmmss"));

            /*
            Map<String, String> req = AcpService.sign(reqData, "UTF-8");
            Map<String, String> resp = AcpService.post(req, SDKConfig.getConfig().getAppRequestUrl(), "UTF-8");
            if (resp.isEmpty() || !AcpService.validate(resp, "UTF-8") || !"00".equals(resp.get("respCode"))) {
                throw new PayException("Unionpay placeOrder fail!");
            }

            return resp.get("tn");
            */
            return "";
        } catch (Exception e) {
            throw new PayException(e);
        }
    }

    public static String placeWapOrder(String outTradeNo, Long fee) {
        try {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("version", "5.0.0");
            reqData.put("encoding", "UTF-8");
            reqData.put("signMethod", "01");
            reqData.put("txnType", "01");
            reqData.put("txnSubType", "01");
            reqData.put("bizType", "000201");
            reqData.put("channelType", "08");
            reqData.put("merId", MERID);
            reqData.put("accessType", "0");
            reqData.put("orderId", outTradeNo);
            reqData.put("currencyCode", "156");
            reqData.put("txnAmt", String.valueOf(fee));
            reqData.put("txnTime", new TimeX().format("yyyyMMddHHmmss"));
            reqData.put("payTimeout", new TimeX().getTimeXAfterMinutes(15).format("yyyyMMddHHmmss"));
            reqData.put("frontUrl", FRONT_URL + "?out_trade_no=" + outTradeNo);
            reqData.put("backUrl", BACK_URL);

            /*
            Map<String, String> req = AcpService.sign(reqData, "UTF-8");
            String requestUrl = SDKConfig.getConfig().getFrontRequestUrl();
            return AcpService.createAutoFormHtml(requestUrl, req, "UTF-8");
            */
            return "";
        } catch (Exception e) {
            throw new PayException(e);
        }
    }

    public static PayOrder queryOrder(String outTradeNo) {
        try {
            Map<String, String> reqData = new HashMap<>();
            reqData.put("merId", MERID);
            reqData.put("version", "5.0.0");
            reqData.put("encoding", "UTF-8");
            reqData.put("signMethod", "01");
            reqData.put("txnType", "00");
            reqData.put("txnSubType", "00");
            reqData.put("bizType", "000201");
            reqData.put("accessType", "0");
            reqData.put("orderId", "1483067519904100004");
            reqData.put("txnTime", "20161230111159");

            /*
            Map<String, String> req = AcpService.sign(reqData, "UTF-8");
            Map<String, String> resp = AcpService.post(req, SDKConfig.getConfig().getSingleQueryUrl(), "UTF-8");
            if (resp.isEmpty() || !AcpService.validate(resp, "UTF-8")) {
                throw new PayException("Unionpay queryOrder fail!");
            }
            if (!"00".equals(resp.get("respCode"))) {
                return new PayOrder(false, 0L);
            }

            Long totalFee = Long.valueOf(resp.get("txnAmt"));
            return new PayOrder(true, totalFee);
            */
            return new PayOrder(true, 0L);
        } catch (Exception e) {
            throw new PayException(e);
        }
    }
}
