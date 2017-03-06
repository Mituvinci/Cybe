package app.wistem.com.cybe.utilities;

import java.util.ArrayList;
import java.util.List;

import app.wistem.com.cybe.modelclass.PhoneNumberModel;

/**
 * Created by mitu on 11/6/16.
 */

public class ImportantPhoneNumbers {

    private static ImportantPhoneNumbers instance = null;

    private ImportantPhoneNumbers(){ }
    private static List<PhoneNumberModel> phoneNumberModelArrayList = new ArrayList<>();



    private static final String[] phoneNumber = {"10921","028961105 ","028963419","028363764","027174687"," 028059254"};


    private static final String[] areaDetails = { "Women and Children","RAB HQ ", "RAB-1","RAB-2","RAB-3","RAB-4"
    };





    public static synchronized ImportantPhoneNumbers getInstance() {

        if (instance == null)

            instance = new ImportantPhoneNumbers();

        return instance;

    }

    public static List<PhoneNumberModel> getPhoneNumber() {
        if (phoneNumberModelArrayList != null) {
            phoneNumberModelArrayList.clear();
        }

        for (int index=0;index< phoneNumber.length;) {
            phoneNumberModelArrayList.add(index, new PhoneNumberModel (areaDetails[index],phoneNumber[index]));
            index++;
        }
        return phoneNumberModelArrayList;
    }
}
