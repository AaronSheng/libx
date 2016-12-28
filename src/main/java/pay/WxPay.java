package pay;

import cipher.DigestException;
import cipher.Md5Utils;
import net.HttpUtils;
import string.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class WxPay {
    private static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static final String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    private static String APPID = "your appid";
    private static String MCHID = "your mchid";
    private static String NOTIFY_URL = "your notify url";
    private static String KEY = "your key";

    public static WxUnifiedOrder placeOrder(String outTradeNo, Float fee, String body, String ip) throws WxPayException {
        try {
            fee = fee * 100f;
            String totalFee = String.valueOf(fee.intValue());
            String nonceStr = StringUtils.randomAlphabet(16);
            String tradeType = "APP";
            String pck = "Sign=WXPay";
            List<Parameter> parameters = ParameterUtils.list(new Parameter("appid", APPID),
                    new Parameter("mch_id", MCHID),
                    new Parameter("nonce_str", nonceStr),
                    new Parameter("body", body),
                    new Parameter("out_trade_no", outTradeNo),
                    new Parameter("total_fee", totalFee),
                    new Parameter("spbill_create_ip", ip),
                    new Parameter("trade_type", tradeType),
                    new Parameter("notify_url", NOTIFY_URL));
            ParameterUtils.sort(parameters);

            String concatParameterString = ParameterUtils.concat(parameters);
            String sign = md5Sign(concatParameterString);
            parameters.add(new Parameter("sign", sign));

            String requestXmlString = getRequestXmlStr(parameters);
            String responseXmlString = HttpUtils.postXml(UNIFIED_ORDER_URL, requestXmlString);
            if (!isSuccessFromUnifiedResponseXmlStr(responseXmlString)) {
                throw new WxPayException("WxPay unifiedorder fail");
            }
            String prepayId = parsePrepayIdFromUnifiedResponseXmlStr(responseXmlString);
            return new WxUnifiedOrder(MCHID, prepayId, nonceStr, pck, sign);
        } catch (Exception e) {
            throw new WxPayException(e);
        }
    }

    public static WxOrder queryOrder(String outTradeNo) throws WxPayException {
        try {
            String nonceStr = StringUtils.randomAlphabet(16);
            List<Parameter> parameters = ParameterUtils.list(new Parameter("appid", APPID),
                    new Parameter("mch_id", MCHID),
                    new Parameter("nonce_str", nonceStr),
                    new Parameter("out_trade_no", outTradeNo));
            ParameterUtils.sort(parameters);

            String concatParameterString = ParameterUtils.concat(parameters);
            String sign = md5Sign(concatParameterString);
            parameters.add(new Parameter("sign", sign));

            String requestXmlString = getRequestXmlStr(parameters);
            String responseXmlString = HttpUtils.postXml(ORDER_QUERY_URL, requestXmlString);
            if (!isSuccessFromQueryResponseXmlStr(responseXmlString)) {
                throw new WxPayException("WxPay queryorder fail");
            }

            if (isExistedFromQueryResponseXmlStr(responseXmlString)) {
                Integer fee = parseTotalFeeFromQueryResponseXmlStr(responseXmlString);
                return new WxOrder(true, fee / 100.0f);
            } else {
                return new WxOrder(false, 0f);
            }
        } catch (Exception e) {
            throw new WxPayException(e);
        }
    }

    private static String getRequestXmlStr(List<Parameter> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (Parameter parameter : parameters) {
            sb.append("<"+parameter.getName()+">"+"<![CDATA["+parameter.getValue()+"]]></"+parameter.getName()+">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    private static String md5Sign(String preStr) throws DigestException {
        String signStr = preStr + "&key=" + KEY;
        return Md5Utils.md5(signStr).toUpperCase();
    }

    private static Boolean isSuccessFromUnifiedResponseXmlStr(String responseXmlString) throws DocumentException {
        Document document = DocumentHelper.parseText(responseXmlString);
        Element root = document.getRootElement();

        Iterator<?> returnCodeItr = root.elementIterator("return_code");
        Element returnCodeElement = (Element) returnCodeItr.next();
        return returnCodeElement.getText().equals("SUCCESS");
    }

    private static String parsePrepayIdFromUnifiedResponseXmlStr(String responseXmlString) throws DocumentException {
        Document document = DocumentHelper.parseText(responseXmlString);
        Element root = document.getRootElement();

        Iterator<?> prepayIdItr = root.elementIterator("prepay_id");
        Element prepayIdElement = (Element) prepayIdItr.next();
        return prepayIdElement.getText();
    }

    private static Boolean isSuccessFromQueryResponseXmlStr(String responseXmlString) throws DocumentException {
        Document document = DocumentHelper.parseText(responseXmlString);
        Element root = document.getRootElement();

        Iterator<?> returnCodeItr = root.elementIterator("return_code");
        Element returnCodeElement = (Element) returnCodeItr.next();
        return returnCodeElement.getText().equals("SUCCESS");
    }

    private static Boolean isExistedFromQueryResponseXmlStr(String responseXmlString) throws DocumentException {
        Document document = DocumentHelper.parseText(responseXmlString);
        Element root = document.getRootElement();

        Iterator<?> resultCodeItr = root.elementIterator("result_code");
        Element resultCodeElement = (Element) resultCodeItr.next();
        return resultCodeElement.getText().equals("SUCCESS");
    }

    private static Integer parseTotalFeeFromQueryResponseXmlStr(String responseXmlString) throws DocumentException {
        Document document = DocumentHelper.parseText(responseXmlString);
        Element root = document.getRootElement();

        Iterator<?> prepayIdItr = root.elementIterator("total_fee");
        Element prepayIdElement = (Element) prepayIdItr.next();
        return Integer.valueOf(prepayIdElement.getText());
    }

    public static class WxUnifiedOrder {
        public String partnerId;
        public String prepayId;
        public String nonceStr;
        public String pck;
        public String sign;

        public WxUnifiedOrder() {
        }

        public WxUnifiedOrder(String partnerId, String prepayId, String nonceStr, String pck, String sign) {
            this.partnerId = partnerId;
            this.prepayId = prepayId;
            this.nonceStr = nonceStr;
            this.pck = pck;
            this.sign = sign;
        }
    }

    public static class WxOrder {
        public Boolean existed;
        public Float totalFee;

        public WxOrder() {
        }

        public WxOrder(Boolean existed, Float totalFee) {
            this.existed = existed;
            this.totalFee = totalFee;
        }
    }
}
