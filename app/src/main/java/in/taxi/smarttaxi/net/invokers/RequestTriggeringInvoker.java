package in.taxi.smarttaxi.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import in.taxi.smarttaxi.model.BasicBean;
import in.taxi.smarttaxi.net.ServiceNames;
import in.taxi.smarttaxi.net.WebConnector;
import in.taxi.smarttaxi.net.parsers.BasicParser;
import in.taxi.smarttaxi.net.utils.WSConstants;

public class RequestTriggeringInvoker extends BaseInvoker {

    public RequestTriggeringInvoker() {
        super();
    }

    public RequestTriggeringInvoker(HashMap<String, String> urlParams,
                                    JSONObject postData) {
        super(urlParams, postData);
    }

    public BasicBean invokerequestTriggeringWS() {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.REQUEST_TRIGGERING), WSConstants.PROTOCOL_HTTP, null, postData);

        //		webConnector= new WebConnector(new StringBuilder(ServiceNames.AUTH_EMAIL), WSConstants.PROTOCOL_HTTP, postData,null);
        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
        String wsResponseString = webConnector.connectToPOST_service();
        //	String wsResponseString=webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        BasicBean basicBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return basicBean = null;
        } else {
            basicBean = new BasicBean();
            BasicParser basicParser = new BasicParser();
            basicBean = basicParser.parseBasicResponse(wsResponseString);
            return basicBean;
        }
    }
}

