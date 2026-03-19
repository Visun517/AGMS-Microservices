package lk.ijse.gdse71.zoneservice.service;

public interface ExternalAuthService {
     String getAccessToken();
     void refreshAccessToken();

}
