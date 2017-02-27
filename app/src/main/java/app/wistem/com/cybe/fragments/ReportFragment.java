package app.wistem.com.cybe.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import app.wistem.com.cybe.R;
import app.wistem.com.cybe.utilities.StoreReportSharedPreferenc;

import static app.wistem.com.cybe.R.id.textViewLink;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment  implements  View.OnClickListener{

    private static String[] mScore = {"1","2","3","4","5","6","7","8","9","10"};
    private final static String KNWOING_SOURCE = "knowingSource";
    private final static String KNOWING_POSITION = "knowingposition";
    private static String mScareScorePosition = "1";
    private Button mButtonSubmit;

    private Button ButtonYesPublic;
    private Button ButtonNoPublic;


    private EditText mEditTextSummarize;

    private TextView mTextViewAudio;
    private TextView mTextViewVideo;
    private TextView mTextViewImage;
    private TextView mTextViewCamera;
    private SeekBar mSeekBar;

    private  String mPublicNot;
    private  String mSummarize ="";
    private String mScareScore;

    private StoreReportSharedPreferenc mSessionManager;
    private List<File> image;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    Bitmap bitmap;
    private String userChoosenTask;



    //private TextView mTextViewScore;
    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_report, container, false);
        mSessionManager = new StoreReportSharedPreferenc(getActivity());
       // mTextViewScore = (TextView) view.findViewById(R.id.textViewScoreit);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar1);
        mButtonSubmit = (Button) view.findViewById(R.id.buttonsubmit);

        ButtonYesPublic = (Button) view.findViewById(R.id.radioButtonMe);
        ButtonNoPublic = (Button) view.findViewById(R.id.radioButtonSomeoneIknow);;

        mEditTextSummarize = (EditText) view.findViewById(R.id.editTextSummarize);
        mTextViewAudio = (TextView) view.findViewById(R.id.textViewAudio);
        mTextViewVideo = (TextView) view.findViewById(R.id.textViewVideo);
        mTextViewImage = (TextView) view.findViewById(R.id.textViewImage);
        mTextViewCamera = (TextView) view.findViewById(R.id.textViewLink);
        mTextViewCamera.setOnClickListener(this);
        scareScore();
        submitReport();

        fileFetch();

        ButtonYesPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonNoPublic.setBackgroundColor(Color.parseColor("#BDBDBD"));
                ButtonYesPublic.setBackgroundColor(Color.parseColor("#4650A3"));
                ButtonYesPublic.setTextColor(Color.parseColor("#F1F1F1"));
                ButtonNoPublic.setTextColor(Color.parseColor("#000000"));

                mPublicNot = "YES";
            }
        });


        ButtonNoPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonNoPublic.setBackgroundColor(Color.parseColor("#4650A3"));
                ButtonNoPublic.setTextColor(Color.parseColor("#F1F1F1"));
                ButtonYesPublic.setTextColor(Color.parseColor("#000000"));
                ButtonYesPublic.setBackgroundColor(Color.parseColor("#BDBDBD"));
                mPublicNot = "NO";

            }
        });





        return  view;
    }

    private void fileFetch(){

        mTextViewAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new FileListFragment();
                Bundle bundle = new Bundle();

                bundle.putString("file","audio");
                fragment.setArguments(bundle);
//

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.containerHome, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mTextViewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FileListFragment();
                Bundle bundle = new Bundle();

                bundle.putString("file","video");
                fragment.setArguments(bundle);
              /*  getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.containerHome, fragment).commit();*/

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.containerHome, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        mTextViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment fragment = new ImageFragment();
//                getFragmentManager().beginTransaction().addToBackStack(null)
//                        .replace(R.id.containerHome, fragment).commit();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.containerHome, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }
    private  void submitReport(){

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSummarize = mEditTextSummarize.getText().toString();
               // mMoreDetails = mEditTextMoreDetails.getText().toString();

                    View view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    mSessionManager.storeUserInformation(mPublicNot,mScareScore,mSummarize);

                    // Log.d("report", "onClick: "+mPublicNot+" ,"+mScoreNumber+" , "+mSummarize+" ,"+mMoreDetails+" , "+mCertainty+" ,"+mPublicNot);

                if (!TextUtils.isEmpty(mPublicNot)) {
                    FragmentManager fm = getActivity().getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.containerHome, new ReportFeedBackFragment());
                    ft.addToBackStack(null);
                    fm.popBackStackImmediate();
                    ft.commit();
                }else {
                    Toast.makeText(getActivity(),"Choose to post it in public or Not",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
    private  void  scareScore(){
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("seekbar", "onProgressChanged: "+i);
                mScareScore = String.valueOf(i)+" %";

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case app.wistem.com.cybe.fragments.Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {

                }
                break;
        }
    }

    private void selectImage() {

        cameraIntent();

    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);

        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        if (thumbnail == null) throw new AssertionError();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        /*File destination = new File(Environment.getExternalStorageDirectory()+"/AjkerDeal",
                "adProfile"+ ".jpg");*/
        File destination = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "/AjkerDeal");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //imagePP.setImageBitmap(thumbnail);
        bitmap = BitmapFactory.decodeFile(String.valueOf(destination));
      //by me mitu  imagePP.setImageBitmap(bitmap);
        save(bitmap);
    }

    private void save(Bitmap bitmap) {
        //  SharedPreferences sp = getSharedPreferences("AppSharedPref", 1); // Open SharedPreferences with name AppSharedPref
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
        // SharedPreferences.Editor editor = sp.edit();
        editor.putString("ImagePath", "abc");
        editor.putString("ImagePath", encodeTobase64(bitmap)); // Store selectedImagePath with key "ImagePath". This key will be then used to retrieve data.
        editor.apply();
    }

    private void restore() {
        SharedPreferences prefschck = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String selectedImagePath = prefschck.getString("ImagePath", "abc");
        //SharedPreferences sp = getSharedPreferences("AppSharedPref", 1);
        // selectedImagePath = sp.getString("ImagePath", "abc");
        bitmap = decodeToBase64(selectedImagePath);
       //by me mitu imagePP.setImageBitmap(bitmap);
    }

    private static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    private static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                save(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       //by me mitu imagePP.setRotation(90);
      //by me mitu  rotation = true;
       //by me mitu imagePP.setImageBitmap(bm);
    }
    @Override
    public void onClick(View v) {

        View view = v;

        int id = v.getId();
        switch (id) {
            case textViewLink:
                if (checkPermission()) {

                    selectImage();


                } else {
                    requestPermission();

                }
                break;
/*            case R.id.request_permission:
                if (!checkPermission()) {

                    requestPermission();

                } else {

                    Snackbar.make(view,"Permission already granted.",Snackbar.LENGTH_LONG).show();

                }
                break;*/
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

            Toast.makeText(getActivity(), "Please Allow Camera and Storage Permission", Toast.LENGTH_SHORT).show();

        } else {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }


    public void rateApp() {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("http://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getActivity().getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }
}
