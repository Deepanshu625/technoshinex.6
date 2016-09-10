package com.example.viking.tsx6;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viking.tsx6.Fragments.About_Fragment;
import com.example.viking.tsx6.Fragments.Config;
import com.example.viking.tsx6.Fragments.Contact_us_fragment;
import com.example.viking.tsx6.Fragments.Nethunt_Fragment;
import com.example.viking.tsx6.Fragments.Offline_fragment;
import com.example.viking.tsx6.Fragments.Online_fragment;
import com.example.viking.tsx6.Fragments.Sponsors;
import com.example.viking.tsx6.Fragments.login;
import com.example.viking.tsx6.Fragments.logout;
import com.example.viking.tsx6.Fragments.register;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

public class MainActivity extends AppCompatActivity {


    private MaterialViewPager mViewPager;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private static Context context;
    private EditText user, pass;
    public static String Username, Password, email;
    public static TextView username_textview;
    Dialog dialog;
    public String id = "v=_yXcoACi0kE";
    public static Typeface typeface, typeface_1, typeface_2, typeface_3;
    public static SharedPreferences sharedPreferences;


    FloatingActionButton fab;
    Boolean check_login;
    String sharedprefrences_username;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        context = this.getApplicationContext();

        //applying fonts
        typeface = Typeface.createFromAsset(getAssets(), "fonts/Bangers.ttf");
        typeface_1 = Typeface.createFromAsset(getAssets(), "fonts/ChallengeContour.ttf");
        typeface_2 = Typeface.createFromAsset(getAssets(), "fonts/PlayfairDisplay.ttf");
        typeface_3 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        username_textview = (TextView) findViewById(R.id.username);
        username_textview.setTypeface(typeface_2);

        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        check_login = sharedPreferences.getBoolean("key1", false);
        sharedprefrences_username = sharedPreferences.getString("Username", "");
        if (check_login) {
            username_textview.setText("Hello, " + sharedprefrences_username);
        } else {
            username_textview.setText("");
        }


        TextView tv = (TextView) findViewById(R.id.logo_white);
        tv.setTypeface(typeface);

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowHomeEnabled(false);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(false);
            }
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 6) {
                    case 0:
                        return About_Fragment.newInstance();
                    case 1:
                        return Online_fragment.newInstance();
                    case 2:
                        return Offline_fragment.newInstance();
                    case 3:
                        return Sponsors.newInstance();
                    case 4:
                        return Contact_us_fragment.newInstance();
                    case 5:
                        return Nethunt_Fragment.newInstance();
                    default:
                        return Offline_fragment.newInstance();
                }

            }

            @Override
            public int getCount() {

                return 6;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 6) {
                    case 0:
                        return "About";
                    case 1:
                        return "ONLINE";
                    case 2:
                        return "OFFLINE";
                    case 3:
                        return "SPONSORS";
                    case 4:
                        return "CONTACT US";
                    case 5:
                        return "NET HUNT";

                }
                return "";
            }
        });
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
//                switch (page) {
//                    case 0:
//
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"),ContextCompat.getDrawable(getApplicationContext(), R.drawable.colarge));
//                    case 1:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.colarge));
//                    case 2:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.colarge));
//                    case 3:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.splash_back));
//                    case 4:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.splash_back));
//                    case 5:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.splash_back));

//                }

                return HeaderDesign.fromColorAndDrawable(
                        Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.colarge));
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getViewPager().setCurrentItem(1);


        //Floating Action Buttons
        fab = (FloatingActionButton) findViewById(R.id.fab);

        //for floating action button...
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#03A9F4")));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check_login = sharedPreferences.getBoolean("key1", false);
                if (Config.USERNAME!=null) {
                    dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.logout);
                    dialog.show();

                }
                //for login
                else {

                    Toast.makeText(getApplication(), "Let's login first", Toast.LENGTH_SHORT).show();
                    dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.for_new_floating);
                    dialog.show();

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void chrome(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("www.cadnitd.co.in")));
    }

    public void mail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("technoshine.ca@gmail.com");
        startActivity(emailIntent);
    }

    public void youtube(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=_yXcoACi0kE"));
        startActivity(intent);
    }

    public void register_button(View view) {
        register register = new register();
        register.newInstance(dialog, MainActivity.this);

    }

    public void login_button(View view) {
        user = (EditText) dialog.findViewById(R.id.editText_username);
        pass = (EditText) dialog.findViewById(R.id.editText_password);
        email = user.getText().toString();
        Password = pass.getText().toString();
        login login = new login();
        login.newInstance(email, Password, context, dialog);
    }

    public static void getUsername(String username) {
        Username = username;

        if (Username == null) {
            Toast.makeText(MainActivity.context, "Wrong username or password", Toast.LENGTH_SHORT).show();
        } else {
            //username_textview.setVisibility(View.VISIBLE);
            username_textview.setText("Hello, " + Username);
            SharedPreferences.Editor ed = sharedPreferences.edit();
            ed.putBoolean("key1", true);
            ed.putString("Username", Username);
            ed.commit();
            Toast.makeText(MainActivity.context, "Succecessfully logged in as " + Username, Toast.LENGTH_SHORT).show();


        }

    }

    public void login_dialog(View view) {
        dialog.dismiss();
        Toast.makeText(getApplication(), "Let's login first", Toast.LENGTH_SHORT).show();
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.log_in);
        //dialog.setTitle("log In");
        dialog.show();


    }

    public void logout(View view) {

        Username = null;
        Toast.makeText(getApplication(), "Successfully, Logged out", Toast.LENGTH_SHORT).show();
//        username_textview.setText("");
        logout logout = new logout();
        logout.newInstance( context, dialog);

//        ed = sharedPreferences.edit();
//        ed.putBoolean("key1", false);
//        ed.putString("Username", "");
//        ed.commit();
        //dialog.dismiss();

    }

    public void cancel(View view) {
        dialog.dismiss();
    }

    public void register_dialog(View view) {
        dialog.dismiss();
        Toast.makeText(getApplication(), "Not registered, Register yourself", Toast.LENGTH_SHORT).show();
        dialog = new Dialog(MainActivity.this, R.style.DialogAnimation);
        dialog.setContentView(R.layout.register);
        // dialog.getWindow().setFormat(PixelFormat.TRANSLUCENT);
        dialog.setTitle("register");
        dialog.show();

    }

    public void start_nethunt(View view) {
        System.out.println("username" + Username);
        if (Config.USERNAME == null) {
            Toast.makeText(getApplication(), "You have to login first", Toast.LENGTH_SHORT).show();
            dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.for_new_floating);
            dialog.show();
        } else {
            Intent intent = new Intent(this, Net_hunt.class);
            startActivity(intent);
//            //intent.putExtra("username", Username);
        }

    }

    public void backspace(View view) {
        dialog.dismiss();
    }



    @SuppressWarnings("NewApi")
    public static Bitmap blurRenderScript(Bitmap smallBitmap, int radius) {


        try {
            smallBitmap = RGB565toARGB888(smallBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Bitmap bitmap = Bitmap.createBitmap(
                smallBitmap.getWidth(), smallBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(context);

        Allocation blurInput = Allocation.createFromBitmap(renderScript, smallBitmap);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(radius); // radius must be 0 < r <= 25
        blur.forEach(blurOutput);
        blurOutput.copyTo(bitmap);
        renderScript.destroy();

        return bitmap;

    }

    private static Bitmap RGB565toARGB888(Bitmap img) throws Exception {
        int numPixels = img.getWidth() * img.getHeight();
        int[] pixels = new int[numPixels];

        //Get JPEG pixels.  Each int is the color values for one pixel.
        img.getPixels(pixels, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());

        //Create a Bitmap of the appropriate format.
        Bitmap result = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);

        //Set RGB pixels.
        result.setPixels(pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight());
        return result;
    }

}
