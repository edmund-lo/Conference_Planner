package common;

public interface ILoggedInView extends IView {
    String getSessionUsername();
    void setSessionUsername(String username);
    String getSessionUserType();
    void setSessionUserType(String userType);
}
