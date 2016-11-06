package app.wistem.com.cybe.modelclass;

/**
 * Created by mitu on 11/6/16.
 */

public class PhoneNumberModel {

    private String mAreaName;
    private String mPhoneNumber;


    public PhoneNumberModel(String mAreaName, String mPhoneNumber) {
        this.mAreaName = mAreaName;
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmAreaName() {
        return mAreaName;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }
}
