package in.taxi.smarttaxi.net;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import in.taxi.smarttaxi.listeners.AppStatusListener;
import in.taxi.smarttaxi.listeners.BasicListener;
import in.taxi.smarttaxi.listeners.CarInfoListener;
import in.taxi.smarttaxi.listeners.DriverDetailsListener;
import in.taxi.smarttaxi.listeners.DriverRatingListener;
import in.taxi.smarttaxi.listeners.EditProfileListener;
import in.taxi.smarttaxi.listeners.FreeRideListener;
import in.taxi.smarttaxi.listeners.LandingPageListener;
import in.taxi.smarttaxi.listeners.LocationSaveListener;
import in.taxi.smarttaxi.listeners.LoginListener;
import in.taxi.smarttaxi.listeners.OTPResendCodeListener;
import in.taxi.smarttaxi.listeners.PolyPointsListener;
import in.taxi.smarttaxi.listeners.PromoCodeListener;
import in.taxi.smarttaxi.listeners.RegistrationListener;
import in.taxi.smarttaxi.listeners.RequestRideListener;
import in.taxi.smarttaxi.listeners.RequestStatusListener;
import in.taxi.smarttaxi.listeners.SavedLocationListener;
import in.taxi.smarttaxi.listeners.SuccessListener;
import in.taxi.smarttaxi.listeners.TotalFareListener;
import in.taxi.smarttaxi.listeners.TripCancellationListener;
import in.taxi.smarttaxi.listeners.TripDetailsListener;
import in.taxi.smarttaxi.listeners.TripFeedbackListener;
import in.taxi.smarttaxi.listeners.TripListListener;
import in.taxi.smarttaxi.listeners.UserInfoListener;
import in.taxi.smarttaxi.model.AuthBean;
import in.taxi.smarttaxi.model.BasicBean;
import in.taxi.smarttaxi.model.CarBean;
import in.taxi.smarttaxi.model.DriverBean;
import in.taxi.smarttaxi.model.DriverRatingBean;
import in.taxi.smarttaxi.model.FareBean;
import in.taxi.smarttaxi.model.FreeRideBean;
import in.taxi.smarttaxi.model.LandingPageBean;
import in.taxi.smarttaxi.model.LocationBean;
import in.taxi.smarttaxi.model.PolyPointsBean;
import in.taxi.smarttaxi.model.PromoCodeBean;
import in.taxi.smarttaxi.model.RequestBean;
import in.taxi.smarttaxi.model.SuccessBean;
import in.taxi.smarttaxi.model.TripCancellationBean;
import in.taxi.smarttaxi.model.TripDetailsBean;
import in.taxi.smarttaxi.model.TripFeedbackBean;
import in.taxi.smarttaxi.model.TripListBean;
import in.taxi.smarttaxi.model.UserBean;
import in.taxi.smarttaxi.net.WSAsyncTasks.AppStatusTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.CarInfoTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.DriverDetailsTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.DriverRatingTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.EditProfileTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.FreeRideTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.LandingPageDetailsTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.LocationSaveTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.LoginTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.NewPasswordTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.OTPResendCodeTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.MobileAvailabilityCheckTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.PolyPointsTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.PromoCodeTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.RegistrationTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.RequestCancelTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.RequestRideTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.RequestStatusTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.RequestTriggeringTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.SavedLocationTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.SuccessDetailsTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.TotalFareTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.TripCancellationTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.TripDetailsTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.TripFeedbackTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.TripListTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.UpdateFCMTokenTask;
import in.taxi.smarttaxi.net.WSAsyncTasks.UserInfoTask;
import in.taxi.smarttaxi.util.AppConstants;

public class DataManager {

    public static void performUpdateFCMToken(JSONObject postData, final BasicListener listener) {

        UpdateFCMTokenTask updateFCMTokenTask = new UpdateFCMTokenTask(postData);
        updateFCMTokenTask.setUpdateFCMTokenTaskListener(new UpdateFCMTokenTask.UpdateFCMTokenTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(BasicBean basicBean) {
                if (basicBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (basicBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(basicBean);
                    } else if (basicBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(basicBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        updateFCMTokenTask.execute();
    }

    public static void performRegistration(JSONObject postData, final RegistrationListener listener) {

        RegistrationTask registrationTask = new RegistrationTask(postData);
        registrationTask.setRegistrationTaskListener(new RegistrationTask.RegistrationTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(AuthBean authBean) {
                if (authBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (authBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(authBean);
                    } else if (authBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(authBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        registrationTask.execute();
    }


    /*public static void performOTPSubmit(JSONObject postData, final OTPSubmitListener listener) {

        OTPSubmitTask otpSubmitTask = new OTPSubmitTask(postData);
        otpSubmitTask.setOtpSubmitTaskListener(new OTPSubmitTask.OTPSubmitTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(OTPBean otpBean) {
                if (otpBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (otpBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(otpBean);
                    } else if (otpBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(otpBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        otpSubmitTask.execute();
    }*/

    public static void performLocationSave(JSONObject postData, final LocationSaveListener listener) {

        LocationSaveTask locationSaveTask = new LocationSaveTask(postData);
        locationSaveTask.setLocationTaskListener(new LocationSaveTask.LocationTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(LocationBean locationBean) {
                if (locationBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (locationBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(locationBean);
                    } else if (locationBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(locationBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        locationSaveTask.execute();
    }

    public static void performDriverRating(JSONObject postData, final DriverRatingListener listener) {

        DriverRatingTask driverRatingTask = new DriverRatingTask(postData);
        driverRatingTask.setDriverRatingTaskListener(new DriverRatingTask.DriverRatingTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(DriverRatingBean driverRatingBean) {
                if (driverRatingBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (driverRatingBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(driverRatingBean);
                    } else if (driverRatingBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(driverRatingBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        driverRatingTask.execute();
    }


    public static void performLogin(JSONObject postData, final LoginListener listener) {

        LoginTask loginTask = new LoginTask(postData);
        loginTask.setLoginTaskListener(new LoginTask.LoginTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(AuthBean authBean) {
                if (authBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (authBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(authBean);
                    } else if (authBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(authBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        loginTask.execute();
    }

    public static void performEditProfile(JSONObject postData, List<String> fileList, final EditProfileListener listener) {

        EditProfileTask editProfileTask = new EditProfileTask(postData, fileList);
        editProfileTask.setEditProfileTaskListener(new EditProfileTask.EditProfileTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(UserBean userBean) {
                if (userBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (userBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(userBean);
                    } else if (userBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(userBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        editProfileTask.execute();
    }

    public static void performFreeRide(JSONObject postData, final FreeRideListener listener) {

        FreeRideTask freeRideTask = new FreeRideTask(postData);
        freeRideTask.setFreeRideTaskListener(new FreeRideTask.FreeRideTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(FreeRideBean freeRideBean) {
                if (freeRideBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (freeRideBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(freeRideBean);
                    } else if (freeRideBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(freeRideBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        freeRideTask.execute();
    }

    public static void performOTPResendCode(JSONObject postData, final OTPResendCodeListener listener) {

        OTPResendCodeTask otpResendCodeTask = new OTPResendCodeTask(postData);
        otpResendCodeTask.setOtpResendTaskListener(new OTPResendCodeTask.OTPResendTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(BasicBean basicBean) {
                if (basicBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (basicBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(basicBean);
                    } else if (basicBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(basicBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
            }
        });
        otpResendCodeTask.execute();
    }

    public static void fetchUserInfo(HashMap<String, String> urlParams, String userID, final UserInfoListener listener) {

        UserInfoTask userInfoTask = new UserInfoTask(urlParams);
        userInfoTask.setUserInfoTaskListener(new UserInfoTask.UserInfoTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(UserBean userBean) {
                if (userBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (userBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(userBean);
                    } else if (userBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(userBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        userInfoTask.execute();
    }

    public static void fetchPromoCode(HashMap<String, String> urlParams, final PromoCodeListener listener) {

        PromoCodeTask promoCodeTask = new PromoCodeTask(urlParams);
        promoCodeTask.setPromoCodeTaskListener(new PromoCodeTask.PromoCodeTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(PromoCodeBean promoCodeBean) {
                if (promoCodeBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (promoCodeBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(promoCodeBean);
                    } else if (promoCodeBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(promoCodeBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        promoCodeTask.execute();
    }

    public static void fetchCarAvailability(HashMap<String, String> urlParams, final CarInfoListener listener) {

        CarInfoTask carInfoTask = new CarInfoTask(urlParams);
        carInfoTask.setCarInfoTaskListener(new CarInfoTask.CarInfoTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(CarBean carBean) {
                if (carBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (carBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(carBean);
                    } else if (carBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(carBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        carInfoTask.execute();
    }

    public static void fetchLandingPageDetails(HashMap<String, String> urlParams, final LandingPageListener landingPageListener) {

        LandingPageDetailsTask landingPageDetailsTask = new LandingPageDetailsTask(urlParams);
        landingPageDetailsTask.setLandingPageDetailsTaskListener(new LandingPageDetailsTask.LandingPageDetailsTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(LandingPageBean landingPageListBean) {
                if (landingPageListBean == null)
                    landingPageListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (landingPageListBean.getStatus().equalsIgnoreCase("Success")) {
                        landingPageListener.onLoadCompleted(landingPageListBean);
                    } else if (landingPageListBean.getStatus().equalsIgnoreCase("Error")) {
                        landingPageListener.onLoadFailed(landingPageListBean.getErrorMsg());
                    } else {
                        landingPageListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                landingPageListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        landingPageDetailsTask.execute();
    }

    public static void fetchTripDetails(HashMap<String, String> urlParams, final TripDetailsListener listener) {

        TripDetailsTask tripDetailsTask = new TripDetailsTask(urlParams);
        tripDetailsTask.setTripDetailsTaskListener(new TripDetailsTask.TripDetailsTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(TripDetailsBean tripDetailsBean) {
                if (tripDetailsBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (tripDetailsBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(tripDetailsBean);
                    } else if (tripDetailsBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(tripDetailsBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        tripDetailsTask.execute();
    }

    public static void fetchTotalFare(HashMap<String, String> urlParams, final TotalFareListener listener) {

        TotalFareTask totalFareTask = new TotalFareTask(urlParams);
        totalFareTask.setTotalFareTaskListener(new TotalFareTask.TotalFareTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(FareBean fareBean) {
                if (fareBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (fareBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(fareBean);
                    } else if (fareBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(fareBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        totalFareTask.execute();
    }


    /*public static void fetchRecentSearches(HashMap<String, String> urlParams, final RecentSearchListener listener) {

        RecentSearchTask recentSearchTask = new RecentSearchTask(urlParams);
        recentSearchTask.setRecentSearchListener(new RecentSearchTask.RecentSearchListener() {

            @Override
            public void dataDownloadedSuccessfully(RecentSearchBean recentSearchBean) {
                if (recentSearchBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (recentSearchBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(recentSearchBean);
                    } else if (recentSearchBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(recentSearchBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);

            }
        });

        recentSearchTask.execute();
    }*/

    public static void fetchTripList(HashMap<String, String> urlParams, final TripListListener listener) {

        TripListTask tripListTask = new TripListTask(urlParams);
        tripListTask.setTripListTaskListener(new TripListTask.TripListTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(TripListBean tripListBean) {
                if (tripListBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (tripListBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(tripListBean);
                    } else if (tripListBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(tripListBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        tripListTask.execute();
    }

    public static void fetchSavedLocation(HashMap<String, String> urlParams, final SavedLocationListener listener) {

        SavedLocationTask savedLocationTask = new SavedLocationTask(urlParams);
        savedLocationTask.setSavedLocationTaskListener(new SavedLocationTask.SavedLocationTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(LocationBean locationBean) {
                if (locationBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (locationBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(locationBean);
                    } else if (locationBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(locationBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        savedLocationTask.execute();
    }

    /*public static void fetchLandingPageDetails(HashMap<String, String> urlParams, final LandingPageDetailsListener listener) {

        LandingPageDetailsTask landingPageDetailsTask = new LandingPageDetailsTask(urlParams);
        landingPageDetailsTask.setLandingPageDetailsTaskListener(new LandingPageDetailsTask.LandingPageDetailsTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(LandingPageListBean landingPageListBean) {
                if (landingPageListBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (landingPageListBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(landingPageListBean);
                    } else if (landingPageListBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(landingPageListBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        landingPageDetailsTask.execute();
    }*/


    public static void fetchRequestStatus(HashMap<String, String> urlParams, final RequestStatusListener listener) {

        RequestStatusTask requestStatusTask = new RequestStatusTask(urlParams);
        requestStatusTask.setRequestStatusTaskListener(new RequestStatusTask.RequestStatusTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(RequestBean requestBean) {
                if (requestBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (requestBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(requestBean);
                    } else if (requestBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(requestBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        requestStatusTask.execute();
    }

    public static void fetchRequestStatus(HashMap<String, String> urlParams, final DriverDetailsListener listener) {

        DriverDetailsTask driverdetailsTask = new DriverDetailsTask(urlParams);
        driverdetailsTask.setDriverDetailsTaskListener(new DriverDetailsTask.DriverDetailsTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(DriverBean driverBean) {
                if (driverBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (driverBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(driverBean);
                    } else if (driverBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(driverBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        driverdetailsTask.execute();
    }


    public static void fetchTripCompletionDetails(HashMap<String, String> urlParams, final SuccessListener listener) {

        SuccessDetailsTask successDetailsTask = new SuccessDetailsTask(urlParams);
        successDetailsTask.setSuccessDetailsTaskListener(new SuccessDetailsTask.SuccessDetailsTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(SuccessBean successBean) {
                if (successBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (successBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(successBean);
                    } else if (successBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(successBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        successDetailsTask.execute();

    }

    public static void fetchPolyPoints(HashMap<String, String> urlParams, final PolyPointsListener polyPointsListener) {

        PolyPointsTask polyPointsTask = new PolyPointsTask(urlParams);
        polyPointsTask.setPolyPointsTaskListener(new PolyPointsTask.PolyPointsTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(PolyPointsBean polyPointsBean) {
                if (polyPointsBean == null)
                    polyPointsListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (polyPointsBean.getStatus().equalsIgnoreCase("Success")) {
                        polyPointsListener.onLoadCompleted(polyPointsBean);
                    } else if (polyPointsBean.getStatus().equalsIgnoreCase("Error")) {
                        polyPointsListener.onLoadFailed(polyPointsBean.getErrorMsg());
                    } else {
                        polyPointsListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                polyPointsListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        polyPointsTask.execute();
    }

    public static void performTripFeedback(JSONObject postData, final TripFeedbackListener tripFeedbackListener) {

        TripFeedbackTask tripFeedbackTask = new TripFeedbackTask(postData);
        tripFeedbackTask.setTripFeedbackTaskListener(new TripFeedbackTask.TripFeedbackTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(TripFeedbackBean tripFeedbackBean) {
                if (tripFeedbackBean == null)
                    tripFeedbackListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (tripFeedbackBean.getStatus().equalsIgnoreCase("Success")) {
                        tripFeedbackListener.onLoadCompleted(tripFeedbackBean);
                    } else if (tripFeedbackBean.getStatus().equalsIgnoreCase("Error")) {
                        tripFeedbackListener.onLoadFailed(tripFeedbackBean.getErrorMsg());
                    } else {
                        tripFeedbackListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                tripFeedbackListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        tripFeedbackTask.execute();
    }

    public static void performTripCancellation(JSONObject postData, final TripCancellationListener tripCancellationListener) {

        TripCancellationTask tripCancellationTask = new TripCancellationTask(postData);
        tripCancellationTask.setTripCancellationTaskListener(new TripCancellationTask.TripCancellationTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(TripCancellationBean tripCancellationBean) {
                if (tripCancellationBean == null)
                    tripCancellationListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (tripCancellationBean.getStatus().equalsIgnoreCase("Success")) {
                        tripCancellationListener.onLoadCompleted(tripCancellationBean);
                    } else if (tripCancellationBean.getStatus().equalsIgnoreCase("Error")) {
                        tripCancellationListener.onLoadFailed(tripCancellationBean.getErrorMsg());
                    } else {
                        tripCancellationListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                tripCancellationListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        tripCancellationTask.execute();
    }

    public static void performRequestRide(JSONObject postData, final RequestRideListener requestRideListener) {

        RequestRideTask requestRideTask = new RequestRideTask(postData);
        requestRideTask.setRequestRideTaskListener(new RequestRideTask.RequestRideTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(RequestBean requestBean) {
                if (requestBean == null)
                    requestRideListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (requestBean.getStatus().equalsIgnoreCase("Success")) {
                        requestRideListener.onLoadCompleted(requestBean);
                    } else if (requestBean.getStatus().equalsIgnoreCase("Error")) {
                        requestRideListener.onLoadFailed(requestBean.getErrorMsg());
                    } else {
                        requestRideListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                requestRideListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });
        requestRideTask.execute();
    }

    public static void performNewPassword(JSONObject postData, final BasicListener basicListener) {

        NewPasswordTask newPasswordTask = new NewPasswordTask(postData);
        newPasswordTask.setNewPasswordTaskListener(new NewPasswordTask.NewPasswordTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(BasicBean basicBean) {
                if (basicBean == null)
                    basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (basicBean.getStatus().equalsIgnoreCase("Success")) {
                        basicListener.onLoadCompleted(basicBean);
                    } else if (basicBean.getStatus().equalsIgnoreCase("Error")) {
                        basicListener.onLoadFailed(basicBean.getErrorMsg());
                    } else {
                        basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        newPasswordTask.execute();
    }

    public static void performRequestCancel(JSONObject postData, final BasicListener basicListener) {

        RequestCancelTask requestCancelTask = new RequestCancelTask(postData);
        requestCancelTask.setRequestCancelTaskListener(new RequestCancelTask.RequestCancelTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(BasicBean basicBean) {
                if (basicBean == null)
                    basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (basicBean.getStatus().equalsIgnoreCase("Success")) {
                        basicListener.onLoadCompleted(basicBean);
                    } else if (basicBean.getStatus().equalsIgnoreCase("Error")) {
                        basicListener.onLoadFailed(basicBean.getErrorMsg());
                    } else {
                        basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        requestCancelTask.execute();
    }

    public static void performMobileAvailabilityCheck(JSONObject postData, final BasicListener basicListener) {

        MobileAvailabilityCheckTask mobileAvailabilityCheckTask = new MobileAvailabilityCheckTask(postData);
        mobileAvailabilityCheckTask.setMobileAvailabilityCheckTaskListener(
                new MobileAvailabilityCheckTask.MobileAvailabilityCheckTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(BasicBean basicBean) {
                if (basicBean == null)
                    basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (basicBean.getStatus().equalsIgnoreCase("Success")) {
                        basicListener.onLoadCompleted(basicBean);
                    } else if (basicBean.getStatus().equalsIgnoreCase("Error")) {
                        basicListener.onLoadFailed(basicBean.getErrorMsg());
                    } else {
                        basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        mobileAvailabilityCheckTask.execute();
    }

    public static void performRequestTriggering(JSONObject postData, final BasicListener basicListener) {

        RequestTriggeringTask requestTriggeringTask = new RequestTriggeringTask(postData);
        requestTriggeringTask.setRequestTriggeringTaskListener(new RequestTriggeringTask.RequestTriggeringTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(BasicBean basicBean) {
                if (basicBean == null)
                    basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (basicBean.getStatus().equalsIgnoreCase("Success")) {
                        basicListener.onLoadCompleted(basicBean);
                    } else if (basicBean.getStatus().equalsIgnoreCase("Error")) {
                        basicListener.onLoadFailed(basicBean.getErrorMsg());
                    } else {
                        basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                basicListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        requestTriggeringTask.execute();
    }

    public static void fetchAppStatus(HashMap<String, String> urlParams, final AppStatusListener appStatusListener) {

        AppStatusTask appStatusTask = new AppStatusTask(urlParams);
        appStatusTask.setAppStatusTaskListener(new AppStatusTask.AppStatusTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(DriverBean driverBean) {
                if (driverBean == null)
                    appStatusListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (driverBean.getStatus().equalsIgnoreCase("Success")) {
                        appStatusListener.onLoadCompleted(driverBean);
                    } else if (driverBean.getStatus().equalsIgnoreCase("Error")) {
                        appStatusListener.onLoadFailed(driverBean.getErrorMsg());
                    } else {
                        appStatusListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                appStatusListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
            }
        });

        appStatusTask.execute();
    }
}



    /*public static void fetchSearchResults(HashMap<String, String> urlParams, final SearchResultsListener listener){

        SearchResultsTask searchResultsTask = new SearchResultsTask(urlParams);
        searchResultsTask.setSearchResultsTaskListener(new SearchResultsTask.SearchResultsTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(SearchResultsBean searchResultsBean) {
                if (searchResultsBean == null)
                    listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                else {
                    if (searchResultsBean.getStatus().equalsIgnoreCase("Success")) {
                        listener.onLoadCompleted(searchResultsBean);
                    } else if (searchResultsBean.getStatus().equalsIgnoreCase("Error")) {
                        listener.onLoadFailed(searchResultsBean.getErrorMsg());
                    } else {
                        listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                    }
                }
            }

            @Override
            public void dataDownloadFailed() {
                listener.onLoadFailed(AppConstants.WEB_ERROR_MSG);

            }
        });

        searchResultsTask.execute();
        }
    }*/

