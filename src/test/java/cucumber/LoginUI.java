package cucumber;

import net.serenitybdd.screenplay.targets.Target;

public class LoginUI {
    public static final Target EMAIL_INPUT_FIELD = Target.the("email input field").locatedBy("//input[@name='username']");
    public static final Target PASSWORD_INPUT_FIELD = Target.the("password input field").locatedBy("//input[@name='password']");
    public static final Target LOGIN_BUTTON = Target.the("click login").locatedBy("//input[contains(@type,'submit')]");
    public static final Target IAM_BUTTON = Target.the("IAM button").locatedBy("//div[contains(@id,'IAM')]");
    public static final Target USERS_BUTTON = Target.the("users button").locatedBy("//div[contains(@class,'MuiListItemText-root')]//span[contains(text(),'Users')]");
    public static final Target INVITE_USERS_BUTTON = Target.the("invite users").locatedBy("//button[contains(@id,'en-ztna-Users-inviteUsersButton')]");
    public static final Target WRITE_EMAIL = Target.the("write email").locatedBy("//input[contains(@placeholder,'Enter email addresses of new team members')]");
    public static final Target USER_GROUP = Target.the("user group").locatedBy("//div[contains(@class, 'react-select__value-container')]/div[contains(@class, 'react-select__input-container')]//input[@class='react-select__input']");
    public static final Target SEND_INVITE = Target.the("send invite").locatedBy("//button[contains(@id,'en-ztna-modal-saveButton')]");
    public static final Target MORE_OPTIONS = Target.the("more options").locatedBy("//button[contains(@id,'en-ztna-TableRow-0-actionIcon')]");
    public static final Target REMOVE_USER = Target.the("remove user").locatedBy("//div[contains(@class,'MuiListItem-button')]//p[contains(text(),'Remove User')]");
    public static final Target USER_INFO = Target.the("user info").locatedBy("//div[contains(@class,'jss46')]");
    public static final Target LOGOUT_BUTTON = Target.the("logout button").locatedBy("//div//p[contains(text(),'Logout')]");

}
