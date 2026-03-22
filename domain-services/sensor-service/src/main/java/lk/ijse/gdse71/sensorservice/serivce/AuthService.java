package lk.ijse.gdse71.sensorservice.serivce;

public interface AuthService {
    String getAccessToken();
    String login();
    void clearToken();
}
