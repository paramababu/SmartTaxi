package in.taxi.smarttaxi.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import in.taxi.smarttaxi.model.OTPBean;
import in.taxi.smarttaxi.net.ServiceNames;
import in.taxi.smarttaxi.net.WebConnector;
import in.taxi.smarttaxi.net.parsers.OTPSubmitParser;
import in.taxi.smarttaxi.net.utils.WSConstants;

public class OTPSubmitInvoker extends BaseInvoker {

    public OTPSubmitInvoker() {
        super();
    }

    public OTPSubmitInvoker(HashMap<String, String> urlParams,
                            JSONObject postData) {
        super(urlParams, postData);
    }

    public OTPBean invokeOTPSubmitWS() {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.OTP_SEND), WSConstants.PROTOCOL_HTTP, null, postData);

        String wsResponseString = webConnector.connectToPOST_service();

        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        OTPBean otpBean = null;
        if (wsResponseString.equals("")) {

            return otpBean = null;

        } else {

            otpBean = new OTPBean();
            OTPSubmitParser otpSubmitParser = new OTPSubmitParser();
            otpBean = otpSubmitParser.parseOtpSubmitResponse(wsResponseString);
            return otpBean;
        }
    }
}