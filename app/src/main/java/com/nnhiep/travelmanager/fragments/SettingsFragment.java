package com.nnhiep.travelmanager.fragments;

import static androidx.core.app.ActivityCompat.*;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.views.FQASet;
import com.nnhiep.travelmanager.views.InstructSet;
import com.nnhiep.travelmanager.views.ListViewTeamProduct;
import com.nnhiep.travelmanager.views.RulesSet;

import java.util.Locale;

/**
 * Trang thiết lập - Bắc
 */
public class SettingsFragment extends Fragment {
    public SettingsFragment() {
    }

    //private ImageView imgTeam,imgVersion,imgcontact,imgRules,imginstruct,imageFQA;
    private RelativeLayout relaRules, relaContact, relaVersion, relaInstruct, relaTeam, relaFqa;
    private Button changeLa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.setting_frag, container, false);
        loadLocale();
        relaRules = view.findViewById(R.id.RelaRules);
        relaContact = view.findViewById(R.id.Relacontact);
        relaVersion = view.findViewById(R.id.RelaVersion);
        relaInstruct = view.findViewById(R.id.RelaIntruct);
        relaTeam = view.findViewById(R.id.RelaTeam);
        relaFqa = view.findViewById(R.id.RelaFqa);
        changeLa = view.findViewById(R.id.changeLang);

        changeLa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showchangelanguageDialog();

            }
        });

//        imgTeam = view.findViewById(R.id.imageView21);
//        imgVersion =  view.findViewById(R.id.imageView23);
//        imgcontact =  view.findViewById(R.id.imageView18);
//        imgRules = view.findViewById(R.id.imageView20);
//        imginstruct = view.findViewById(R.id.imageView19);
//        imageFQA = view.findViewById(R.id.imageView22);
//
        relaFqa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FQASet.class);
                startActivity(intent);
            }
        });

        relaInstruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InstructSet.class);
                startActivity(intent);
            }
        });
        relaRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RulesSet.class);
                startActivity(intent);
            }
        });
//
        relaContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogcontact();
            }
        });

        relaVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        relaTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ListViewTeamProduct.class);
                startActivity(intent);
            }
        });


        return view;


    }

    public void openDialogcontact() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Contact us")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),
                                "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setMessage("group1_android@gmail.com")
        ;
        builder.show();

    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),
                                "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setMessage("Version 1.0")
        ;
        builder.show();

    }

    private void showchangelanguageDialog() {
        final String listitiem[] = {"Vietnam", "English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Language...");
        builder.setSingleChoiceItems(listitiem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
//                tieng viet
                if (i == 0) {
                    setLocale("vi");
                    getActivity().recreate();
                } else if (i == 1) {
                    setLocale("en");
                    getActivity().recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("app_lang", lang);
        editor.apply();
    }

    private void loadLocale() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String lang = preferences.getString("app_lang", "");
        setLocale(lang);
    }
}