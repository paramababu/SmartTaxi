package in.taxi.smarttaxi.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import in.taxi.smarttaxi.model.UserBean;
import in.taxi.smarttaxi.net.ServiceNames;
import in.taxi.smarttaxi.net.WebConnector;
import in.taxi.smarttaxi.net.parsers.EditProfileParser;
import in.taxi.smarttaxi.net.utils.WSConstants;

public class EditProfileInvoker extends BaseInvoker {

    public EditProfileInvoker() {
        super();
    }

    public EditProfileInvoker(HashMap<String, String> urlParams,
                        JSONObject postData) {
        super(urlParams, postData);
    }

    public UserBean invokeEditProfileWS(List<String> fileList) {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.EDIT_PROFILE), WSConstants.PROTOCOL_HTTP, null, postData, fileList);

        //		webConnector= new WebConnector(new StringBuilder(ServiceNames.AUTH_EMAIL), WSConstants.PROTOCOL_HTTP, postData,null);
        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
        String wsResponseString = webConnector.connectToMULTIPART_POST_service("profile_update");
        //	String wsResponseString=webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        UserBean userBean = null;
        if (wsResponseString.equals("")) {
            return userBean = null;
        } else {
            userBean = new UserBean();
            EditProfileParser editProfileParser = new EditProfileParser();
            userBean = editProfileParser.parseEditProfileResponse(wsResponseString);
            return userBean;
        }
    }
}
