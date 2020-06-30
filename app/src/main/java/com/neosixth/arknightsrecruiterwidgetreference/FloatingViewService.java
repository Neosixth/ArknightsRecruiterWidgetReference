package com.neosixth.arknightsrecruiterwidgetreference;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.os.Build;
import android.os.IBinder;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.LineBackgroundSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class FloatingViewService extends Service implements View.OnClickListener {

    //Base widget variables
    private WindowManager mWindowManager;
    private View mFloatingView;
    private View collapsedView;
    private Boolean collapsedTrue;
    private View currentView;
    //private View currentViewAux;
    //End of Base widget variables


    //Base widget buttons
    private ImageView trueIcon;
    private ImageView bCloseMin;
    private ImageView bKofi;
    private Button bScan;
    private Button bGuaranteed;
    private Button bPrev;
    private Button bManual;
    private Button bEnter;
    private Button bPrevFromManual;
    private Button bReset;
    private WebView bWebview;
    private Button bPrevFromKofi;
    private TextView ocrOutput;
    private TextView kofiText;
    //End of base widget buttons

    private Button senior;
    private Button top;
    private Button melee;
    private Button ranged;
    private Button guard;
    private Button medic;
    private Button vanguard;
    private Button caster;
    private Button sniper;
    private Button defender;
    private Button supporter;
    private Button specialist;
    private Button healing;
    private Button support;
    private Button dps;
    private Button aoe;
    private Button slow;
    private Button survival;
    private Button defense;
    private Button debuff;
    private Button shift;
    private Button crowdcontrol;
    private Button nuker;
    private Button summon;
    private Button fastredeploy;
    private Button dprecovery;

    //TODO: Customize and add expandedViews as needed
    private View expandedView1;
    private View expandedViewG;
    private View expandedViewManual;
    private View expandedViewKofi;
    //private View expandedView1AUX;
    //End of expandedViews


    Set<String> chosenSet = new HashSet<>();
    ArrayList<CharSequence> mobi = new ArrayList<>();
    ArrayList<CharSequence> guaranteedMobi = new ArrayList<>();

    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterG;
    //TODO: Customize and add buttons as needed
    //End of buttons

    List<String> ocrListTags = Arrays.asList( "Starter","Senior", "Top", "Melee", "Ranged", "Guard", "Medic", "Vanguard", "Caster", "Sniper", "Defender", "Supporter", "Specialist", "Healing", "Support", "DPS", "AoE", "Slow", "Survival", "Defense", "Debuff", "Shift", "Crowd", "Nuker", "Summon", "Fast", "Recovery");
    List<String> listButtonTagNames = Arrays.asList( "Senior", "Top", "Melee", "Ranged", "Guard", "Medic", "Vanguard", "Caster", "Sniper", "Defender", "Supporter", "Specialist", "Healing", "Support", "DPS", "AoE", "Slow", "Survival", "Defense", "Debuff", "Shift", "Crowd-Control", "Nuker", "Summon", "Fast-Redeploy", "DP-Recovery");
    List<String> listTop = Arrays.asList("Siege", "Shining", "Nightingale", "Ifrit", "Exusiai", "SilverAsh", "Hoshiguma", "Saria");
    List<String> listSenior = Arrays.asList("Texas", "Zima", "Ptilopsis", "Silence", "Warfarin", "Projekt Red", "Manticore", "Cliffheart", "FEater", "Provence", "Blue Poison", "Firewatch", "Meteorite", "Platinum", "Pramanix", "Istina", "Mayer", "Specter", "Indra", "Nearl", "Liskarm", "Vulcan", "Croissant");
    List<String> listThreeStar = Arrays.asList("Beagle", "Melantha", "Hibiscus", "Ansel", "Lava", "Steward", "Kroos", "Adnachiel", "Fang", "Vanilla", "Plume", "Orchid");

    List<Button> listTagButtons;

    private static final String CHANNEL_ID = "channel_01";

    public FloatingViewService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //! Start of Base widget starting code: Do not touch
        super.onCreate();
        //getting the widget layout from xml using layout inflater
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);
        //setting the layout parameters
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);















        //getting windows services and adding the floating view to it
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);

        adapter = new ArrayAdapter<CharSequence>(this, R.layout.activity_listview, mobi);
        adapterG = new ArrayAdapter<CharSequence>(this, R.layout.activity_listview, guaranteedMobi);
        ListView listView = (ListView) mFloatingView.findViewById(R.id.outputListView);
        listView.setAdapter(adapter);
        //ListView listViewG = (ListView) mFloatingView.findViewById(R.id.outputListViewGuaranteed);
        //listViewG.setAdapter(adapterG);


        bKofi = mFloatingView.findViewById(R.id.bKofiXML);
        trueIcon = mFloatingView.findViewById(R.id.widgetMainIcon);
        trueIcon.setClipToOutline(true);

        kofiText = mFloatingView.findViewById(R.id.kofiTextXML);
        kofiText.setMovementMethod(new ScrollingMovementMethod());

        //getting the collapsed and expanded view from the floating view
        bCloseMin = mFloatingView.findViewById(R.id.buttonClose);
        collapsedView = mFloatingView.findViewById(R.id.layoutCollapsed);
        bScan = (Button) mFloatingView.findViewById(R.id.bScanXML);
        bManual = (Button) mFloatingView.findViewById(R.id.bManualXML);
        bWebview = (WebView) mFloatingView.findViewById(R.id.wv1);

        WebSettings webSettings = bWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        bWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                /*
                Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                view.getContext().startActivity(intent);

                 */
                try{
                    //Uri uri = Uri.parse("https://www.google.com"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //view.loadUrl("https://www.google.com");
                    startActivity(intent);
                } catch(Exception e){

                }

                return true;
            }
        });
        bWebview.loadUrl("file:///android_asset/kofihtml.html");


        //! End of Base widget starting code

        collapsedTrue = true;

        //Declare Tags
        {
            senior = mFloatingView.findViewById(R.id.bSeniorOperator);
            top = mFloatingView.findViewById(R.id.bTopOperator);
            melee = mFloatingView.findViewById(R.id.bMelee);
            ranged = mFloatingView.findViewById(R.id.bRanged);
            guard = mFloatingView.findViewById(R.id.bGuard);
            medic = mFloatingView.findViewById(R.id.bMedic);
            vanguard = mFloatingView.findViewById(R.id.bVanguard);
            caster = mFloatingView.findViewById(R.id.bCaster);
            sniper = mFloatingView.findViewById(R.id.bSniper);
            defender = mFloatingView.findViewById(R.id.bDefender);
            supporter = mFloatingView.findViewById(R.id.bSupporter);
            specialist = mFloatingView.findViewById(R.id.bSpecialist);
            healing = mFloatingView.findViewById(R.id.bHealing);
            support = mFloatingView.findViewById(R.id.bSupport);
            dps = mFloatingView.findViewById(R.id.bDPS);
            aoe = mFloatingView.findViewById(R.id.bAoE);
            slow = mFloatingView.findViewById(R.id.bSlow);
            survival = mFloatingView.findViewById(R.id.bSurvival);
            defense = mFloatingView.findViewById(R.id.bDefense);
            debuff = mFloatingView.findViewById(R.id.bDebuff);
            shift = mFloatingView.findViewById(R.id.bShift);
            crowdcontrol = mFloatingView.findViewById(R.id.bCrowdControl);
            nuker = mFloatingView.findViewById(R.id.bNuker);
            summon = mFloatingView.findViewById(R.id.bSummon);
            fastredeploy = mFloatingView.findViewById(R.id.bFastRedeploy);
            dprecovery = mFloatingView.findViewById(R.id.bDPRecovery);
        }

        listTagButtons = Arrays.asList(senior, top, melee, ranged, guard, medic, vanguard, caster, sniper, defender, supporter, specialist, healing, support, dps, aoe, slow, survival, defense,debuff, shift, crowdcontrol, nuker, summon, fastredeploy, dprecovery);


        for(int i = 0; i < listTagButtons.size(); i++){
            final Button currB = listTagButtons.get(i);
            final String nameB = listButtonTagNames.get(i);
            currB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //starter.setBackground("#000000");
                    if (!chosenSet.contains(nameB)) {
                        chosenSet.add(nameB);
                        currB.setBackgroundColor(Color.BLUE);
                    } else {
                        chosenSet.remove(nameB);
                        currB.setBackgroundColor(Color.BLACK);
                    }
                }
            });
        }


        //TODO: Customize and add expandedViews here
        expandedView1 = mFloatingView.findViewById(R.id.expanded1);
        expandedViewG = mFloatingView.findViewById(R.id.expandedG);
        expandedViewManual = mFloatingView.findViewById(R.id.expandedManual);
        expandedViewKofi = mFloatingView.findViewById(R.id.expandedKofi);
        bReset = mFloatingView.findViewById(R.id.bResetXML);

        ocrOutput = (TextView) mFloatingView.findViewById(R.id.ocrOutputXML);
        ocrOutput.setMovementMethod(new ScrollingMovementMethod());
        //expandedView1AUX = mFloatingView.findViewById(R.id.expanded1AUX);
        currentView = expandedView1;

        ListView listViewG = (ListView) mFloatingView.findViewById(R.id.outputList6);
        listViewG.setAdapter(adapterG);




        //currentViewAux = expandedView1AUX;

        //End of linking expandedViews

        //adding click listener to close button and expanded view
        mFloatingView.findViewById(R.id.buttonClose).setOnClickListener(this);
        mFloatingView.findViewById(R.id.bKofiXML).setOnClickListener(this);
        mFloatingView.findViewById(R.id.wv1).setOnClickListener(this);
        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenSet.clear();
                for(int i = 0; i < listTagButtons.size(); i++){
                    final Button currB = listTagButtons.get(i);
                    final String nameB = listButtonTagNames.get(i);

                    currB.setBackgroundColor(Color.BLACK);

                }
                //startActivity(new Intent(this, ScreenCapture.class));
            }
        });

        //expandedView1AUX.setOnClickListener(this);
        bScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocrOutput.setText("Loading...");
                chosenSet.clear();
                mobi.clear();
                guaranteedMobi.clear();
                adapter.notifyDataSetChanged();
                adapterG.notifyDataSetChanged();
                mobi.add("Loading... scanning can take a few seconds...(if the game freezes behind the widget, please allow this app to be always allowed to scan the screen to fix it.");
                Intent dialogIntent = new Intent(FloatingViewService.this, ScreenCapture.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);




                /*
                Intent notificationIntent = new Intent(FloatingViewService.this, ScreenCapture.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(FloatingViewService.this, 0,
                        notificationIntent, 0);

                Notification notification = new NotificationCompat.Builder(FloatingViewService.this)
                        .setSmallIcon(R.drawable.ak_widget_main_icon)
                        .setContentTitle("My Awesome App")
                        .setContentText("Doing some work...")
                        .setContentIntent(pendingIntent).build();

                startForeground(1337, notification);

                 */



                startActivity(dialogIntent);
            }
        });

        bManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chosenSet.clear();
                //look through chosenSet and change color of buttons that exist.
                for(int i = 0; i < listTagButtons.size(); i++){
                    final Button currB = listTagButtons.get(i);
                    final String nameB = listButtonTagNames.get(i);
                    if (chosenSet.contains(nameB)) {
                        currB.setBackgroundColor(Color.BLUE);
                    } else {
                        currB.setBackgroundColor(Color.BLACK);
                    }
                }



                mobi.clear();
                guaranteedMobi.clear();
                adapter.notifyDataSetChanged();
                adapterG.notifyDataSetChanged();
                expandedView1.setVisibility(View.GONE);
                expandedViewManual.setVisibility(View.VISIBLE);
                currentView = expandedViewManual;
                //startActivity(new Intent(this, ScreenCapture.class));
            }
        });

        bEnter = mFloatingView.findViewById(R.id.bEnterXML);
        bEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobi.clear();
                guaranteedMobi.clear();
                adapter.notifyDataSetChanged();
                adapterG.notifyDataSetChanged();
                expandedView1.setVisibility(View.VISIBLE);
                expandedViewManual.setVisibility(View.GONE);
                currentView = expandedView1;


                TagsFilter tagsFilter = new TagsFilter();
                HashMap<List, List> output = tagsFilter.filterTags(chosenSet);
                Iterator it3 = output.entrySet().iterator();
                //ocrOutput.setText(chosenSet.toString());
                List<String> chosenHolder = new ArrayList<>();
                String h0 = "<span style =\"background-color:#363636;  color:black;  \"> ";
                //            String h0 = "<span style =\"background-color:#363636;\"> ";
                for (String curr : chosenSet) {
                    //String newCurr = "."+h0+"<font color=#FFFFFF>"+curr+"</font> </span>";
                    String newCurr = "<font color=#000000>.</font><font color=#FFFFFF>"+h0+curr+" </font>      </span> ";
                    //String newCurr = ".<u>"+h0+curr+"</u></span>";
                    chosenHolder.add(newCurr);
                }
                String joinedChosenTags = TextUtils.join("<font color=#000000>,</font>", chosenHolder);
                //setTextWithSpan(ocrOutput, android.graphics.Color.BLUE, "Hellhghghgh ghghghg gggghhhh   ghghghg  yy  tlll hello hogwatrs xion phantasy star ongline", 1.1f);
                //ocrOutput.setPaintFlags(ocrOutput.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                mobi.add(Html.fromHtml("♜"+joinedChosenTags));
                //TODO:clear the mobi every scan
                while (it3.hasNext()) {
                    Map.Entry ko3 = (Map.Entry) it3.next();

                    //Filter good and bad tags here
                    //Good tags: List.size() > 1, fastredeploy, summon, crowdcontrol, shift, nuker
                    List<String> holder = new ArrayList<String>();
                    List<String> holder2 = new ArrayList<String>();
                    List<String> holderSpan = new ArrayList<>();

                    holder = (List) ko3.getKey();
                    holder2 = (List) ko3.getValue();

                    String h = "<span style =\"background-color:#363636;\"> ";
                    for(int i = 0; i < holder.size(); i++){
                        String curr = holder.get(i);
                        curr = "<font color=#000000>.</font>"+h+"<font color=#FFFFFF> "+curr+"</font> </span>";
                        holderSpan.add(curr);
                    }


                    String joinedKeyTags = TextUtils.join("<font color=#000000>,</font>", holderSpan);
                    //Task: Make them go    | Support || Medic |


                    String joinedValueOperators = TextUtils.join(",", holder2);
                    //{Mayer(Purple), Gravel(Purple), Projekt Red(Gold), Siege(Red)
                    // joinedValueOperators = "Gravel, Projekt Red"
                    String[] uncoloredOps = joinedValueOperators.split(",");
                    //StringJoiner sb = new StringJoiner(", ");
                    //List<String> coloredList = new ArrayList<String>();

                    List<String> redOps = new ArrayList<String>();
                    List<String> goldOps = new ArrayList<String>();
                    List<String> purpleOps = new ArrayList<String>();
                    List<String> blueOps = new ArrayList<String>();


                    for (int i = 0; i < uncoloredOps.length; i++) {
                        if (listTop.contains(uncoloredOps[i])) {
                            uncoloredOps[i] = "<font color=#FF3300>" + uncoloredOps[i] + "</font>";
                            redOps.add(uncoloredOps[i]);
                        } else if (listThreeStar.contains(uncoloredOps[i])) {
                            uncoloredOps[i] = "<font color=#0080FE>" + uncoloredOps[i] + "</font>";
                            blueOps.add(uncoloredOps[i]);
                        } else if (listSenior.contains(uncoloredOps[i])) {
                            uncoloredOps[i] = "<font color=#FFD700>" + uncoloredOps[i] + "</font>";
                            goldOps.add(uncoloredOps[i]);
                        } else {
                            uncoloredOps[i] = "<font color=#CC99ff>" + uncoloredOps[i] + "</font>";
                            purpleOps.add(uncoloredOps[i]);
                        }
                        //coloredList.add(uncoloredOps[i]);
                    }

                    List<String> coloredList = new ArrayList<String>();
                    if (redOps.size() > 0) {
                        coloredList.addAll(redOps);
                    } else {
                        coloredList.addAll(goldOps);
                        coloredList.addAll(purpleOps);
                        coloredList.addAll(blueOps);
                    }
                    String coloredOps = TextUtils.join(", ", coloredList);

                    //TODO: This is what makes colored words work in HTML
                    //String h = "<span style =\"background-color:#4295d6;\">";
                    String listElement = joinedKeyTags +"<br/>" + coloredOps;
                    mobi.add(Html.fromHtml(listElement));
                }//end of hasNext while loop

            } //End of onClick function
        });

        bPrevFromKofi = mFloatingView.findViewById(R.id.bPrevKofiXML);
        bPrevFromKofi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandedView1.setVisibility(View.VISIBLE);
                expandedViewKofi.setVisibility(View.GONE);
                currentView = expandedView1;
            }
        });

        bPrevFromManual= mFloatingView.findViewById(R.id.bPrevFromManualXML);
        bPrevFromManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenSet.clear();
                for(int i = 0; i < listTagButtons.size(); i++){
                    final Button currB = listTagButtons.get(i);
                    currB.setBackgroundColor(Color.BLACK);
                }
                mobi.clear();
                //mobi.add(Html.fromHtml("When in Recruit page, press SCAN to detect the tags.<br/> - The filter is default to 9hours timer. Hence starter and robot tag would not be detected and filtered."));
                mobi.add(Html.fromHtml("When in Recruit page, press SCAN to detect the tags."));
                mobi.add(Html.fromHtml("♜ Scroll downwards to read unshown information."));
                mobi.add(Html.fromHtml("♜ Press MANUAL to manually add or select tags to filter."));
                mobi.add(Html.fromHtml("♜ Press GUARANTEED to see if any Guaranteed tag combinations exist"));
                mobi.add(Html.fromHtml("♜ Since the filter is default to 9hours timer, Starter and Robot tags would not be detected or filtered."));
                guaranteedMobi.clear();
                adapter.notifyDataSetChanged();
                adapterG.notifyDataSetChanged();
                expandedView1.setVisibility(View.VISIBLE);
                expandedViewManual.setVisibility(View.GONE);
                currentView = expandedView1;
                //startActivity(new Intent(this, ScreenCapture.class));
            }
        });

        bGuaranteed = mFloatingView.findViewById(R.id.bGuaranteedXML);
        bGuaranteed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                expandedView1.setVisibility(View.GONE);
                expandedViewG.setVisibility(View.VISIBLE);
                currentView = expandedViewG;
                guaranteedMobi.clear();

                //Below shows chosenSet is working
                //chosenSet has already been initialized

                if(chosenSet.contains("Support")){
                    if(chosenSet.contains("DP-Recovery")){
                        String listElement ="[Support+DP-Recovery]<br/><font color=#FFD700>Zima</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if (chosenSet.contains("Vanguard")){
                        String listElement ="[Support+Vanguard]<br/><font color=#FFD700>Zima</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if (chosenSet.contains("Healing")){
                        String listElement ="[Support+Healing]<br/><font color=#FFD700>Warfarin</font>, <font color=#FFD700>Ptilopsis</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));

                    }
                    if (chosenSet.contains("Ranged")){
                        String listElement ="[Support+Healing]<br/><font color=#FFD700>Warfarin</font>, <font color=#FFD700>Ptilopsis</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Guard")){
                        String listElement ="[Support+Guard]<br/><font color=#CC99ff>Dobermann</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("DPS")){
                        String listElement ="[Support+DPS]<br/><font color=#FFD700>Zima</font>, <font color=#CC99ff>Dobermann</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                }
                if(chosenSet.contains("DPS")){
                    if(chosenSet.contains("Defender")){
                        String listElement ="[DPS+Defender]<br/><font color=#FFD700>Liskarm</font>, <font color=#FFD700>Vulcan</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Shift")){
                        String listElement ="[DPS+Shift]<br/><font color=#FFD700>Cliffheart</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Supporter")){
                        String listElement ="[DPS+Supporter]<br/><font color=#FFD700>Istina</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                }
                if(chosenSet.contains("Crowd-Control")){
                    if(chosenSet.contains("Specialist")){
                        String listElement ="[Crowd-Control+Specialist]<br/><font color=#FFD700>Projekt Red</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Fast-Redeploy")){
                        String listElement ="[Crowd-Control+Fast-Redeploy]<br/><font color=#FFD700>Projekt Red</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Supporter")){
                        String listElement ="[Crowd-Control+Supporter]<br/><font color=#FFD700>Mayer</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Summon")){
                        String listElement ="[Crowd-Control+Summon]<br/><font color=#FFD700>Mayer</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Vanguard")){
                        String listElement ="[Crowd-Control+Vanguard]<br/><font color=#FFD700>Texas</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("DP-Recovery")){
                        String listElement ="[Crowd-Control+DP-Recovery]<br/><font color=#FFD700>Texas</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    String listElement ="[Crowd-Control]<br/><font color=#FFD700>Texas</font>, <font color=#FFD700>Projekt Red</font>, <font color=#FFD700>Mayer</font>";
                    guaranteedMobi.add(Html.fromHtml(listElement));
                }
                if(chosenSet.contains("Debuff")){
                    if(chosenSet.contains("AoE")){
                        String listElement ="[Debuff+AoE]<br/><font color=#FFD700>Meteorite</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Supporter")){
                        String listElement ="[Debuff+Supporter]<br/><font color=#FFD700>Pramanix</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("DPS")){
                        String listElement ="[Debuff+DPS]<br/><font color=#CC99ff>Haze</font>, <font color=#CC99ff>Meteor</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Caster")){
                        String listElement ="[Debuff+Caster]<br/><font color=#CC99ff>Haze</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Ranged")){
                        String listElement ="[Debuff+Ranged]<br/><font color=#FFD700>Meteorite</font>, <font color=#FFD700>Pramanix</font>, <font color=#CC99ff>Haze</font>, <font color=#CC99ff>Meteor</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                }
                if(chosenSet.contains("Summon")){
                    String listElement ="[Summon]<br/><font color=#FFD700>Mayer</font> DO NOT USE WITH OTHER TAGS. OTHERWISE SUMMON TAG MIGHT DROP.";
                    guaranteedMobi.add(Html.fromHtml(listElement));
                }
                if(chosenSet.contains("Nuker")){
                    String listElement ="[Nuker]<br/><font color=#FFD700>Firewatch</font> DO NOT USE WITH OTHER TAGS. OTHERWISE NUKER TAG MIGHT DROP.";
                    guaranteedMobi.add(Html.fromHtml(listElement));
                }
                if(chosenSet.contains("Specialist")){
                    if(chosenSet.contains("DPS")){
                        String listElement ="[Specialist+DPS]<br/><font color=#FFD700>Manticore</font>, <font color=#FFD700>Cliffheart</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                }
                if(chosenSet.contains("Slow")){
                    if(chosenSet.contains("Specialist")){
                        String listElement ="[Slow+Specialist]<br/><font color=#FFD700>FEater</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Shift")){
                        String listElement ="[Slow+Shift]<br/><font color=#FFD700>FEater</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("DPS")){
                        String listElement ="[Slow+DPS]<br/><font color=#FFD700>Istina</font>, <font color=#CC99ff>Frostleaf</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Sniper")){
                        String listElement ="[Slow+Sniper]<br/><font color=#CC99ff>ShiraYuki</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("AoE")){
                        String listElement ="[Slow+AoE]<br/><font color=#CC99ff>ShiraYuki</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                }
                if(chosenSet.contains("Shift")){
                    if(chosenSet.contains("Defense")){
                        String listElement ="[Shift+Defense]<br/><font color=#CC99ff>Croissant</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Defender")){
                        String listElement ="[Shift+Defender]<br/><font color=#CC99ff>Croissant</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Specialist")){
                        String listElement ="[Shift+Specialist]<br/><font color=#CC99ff>FEater</font>, <font color=#CC99ff>Cliffheart</font>, <font color=#CC99ff>Shaw</font>, <font color=#CC99ff>Rope</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                }
                if(chosenSet.contains("Healing")){
                    if(chosenSet.contains("Defender")){
                        String listElement ="[Healing+Defender]<br/><font color=#CC99ff>Gummy</font>, <font color=#CC99ff>Nearl</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Melee")){
                        String listElement ="[Healing+Melee]<br/><font color=#CC99ff>Gummy</font>, <font color=#CC99ff>Nearl</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                }
                if(chosenSet.contains("Survival")){
                    if(chosenSet.contains("Sniper")){
                        String listElement ="[Survival+Sniper]<br/><font color=#CC99ff>Jessica</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Ranged")){
                        String listElement ="[Survival+Ranged]<br/><font color=#CC99ff>Jessica</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                }
                if(chosenSet.contains("AoE")){
                    if(chosenSet.contains("Melee")){
                        String listElement ="[AoE+Melee]<br/><font color=#CC99ff>Specter</font>, <font color=#CC99ff>Estelle</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                    if(chosenSet.contains("Survival")){
                        String listElement ="[AoE+Survival]<br/><font color=#CC99ff>Specter</font>, <font color=#CC99ff>Estelle</font>";
                        guaranteedMobi.add(Html.fromHtml(listElement));
                    }
                }
                if(guaranteedMobi.isEmpty()){
                    String listElement ="No guaranteed tag combinations found";
                    guaranteedMobi.add(Html.fromHtml(listElement));
                }

            }//end of onClickL of bGuaranteed6
        });


        bPrev = mFloatingView.findViewById(R.id.bPrevXML);
        bPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandedViewG.setVisibility(View.GONE);
                expandedView1.setVisibility(View.VISIBLE);
                currentView = expandedView1;
            }
        });



        //Start of widgetMainIcon functionality code: dragging trueIcon, auto expand and set collapsed view gone.
        mFloatingView.findViewById(R.id.widgetMainIcon).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            private static final int MAX_CLICK_DURATION = 200;
            private long startClickTime;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startClickTime = Calendar.getInstance().getTimeInMillis();
                        //break;
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;


                    case MotionEvent.ACTION_UP:
                        long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                        if(clickDuration < MAX_CLICK_DURATION) {

                            if (collapsedTrue==true){
                                //when the drag is ended switching the state of the widget
                                collapsedView.setVisibility(View.GONE);
                                collapsedTrue = false;
                                bCloseMin.setVisibility(View.VISIBLE);
                                //bCloseMin.setImageResource(R.drawable.ak_icon_min);
                                //expander1.setVisibility(View.VISIBLE);
                                //expandedView1.setVisibility(View.VISIBLE);
                                //currentViewAux.setVisibility(View.VISIBLE);
                                currentView.setVisibility(View.VISIBLE);
                                return true;
                            } else{
                                collapsedView.setVisibility(View.VISIBLE);
                                collapsedTrue = true;
                                bCloseMin.setVisibility(View.GONE);
                                //bCloseMin.setImageResource(R.drawable.ak_icon_off);
                                //expander1.setVisibility(View.VISIBLE);
                                //expandedView1.setVisibility(View.VISIBLE);
                                //currentViewAux.setVisibility(View.VISIBLE);
                                currentView.setVisibility(View.GONE);
                                return true;
                            }

                        }

                        /*
                        //when the drag is ended switching the state of the widget
                        collapsedView.setVisibility(View.GONE);
                        collapsedTrue = false;
                        bCloseMin.setImageResource(R.drawable.ak_icon_min);
                        //expander1.setVisibility(View.VISIBLE);
                        //expandedView1.setVisibility(View.VISIBLE);
                        //currentViewAux.setVisibility(View.VISIBLE);
                        currentView.setVisibility(View.VISIBLE);
                        return true;
                        */


                    case MotionEvent.ACTION_MOVE:
                        //this code is helping the widget to move around the screen with fingers
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bKofiXML:
                currentView.setVisibility(View.GONE);
                expandedViewKofi.setVisibility(View.VISIBLE);
                currentView = expandedViewKofi;
                break;
            case R.id.expanded1AUX:
                //switching views
                collapsedView.setVisibility(View.VISIBLE);
                currentView.setVisibility(View.GONE);
                //currentViewAux.setVisibility(View.GONE);
                break;

            case R.id.buttonClose:
                stopSelf();
                //closing the widget
                //if in expanded view -> collapse

                /*
                if(collapsedTrue == false){
                    collapsedView.setVisibility(View.VISIBLE);
                    currentView.setVisibility(View.GONE);
                    //currentViewAux.setVisibility(View.GONE);
                    collapsedTrue = true;
                    //bCloseMin.setImageResource(R.drawable.ak_icon_off);
                    break;
                } else{
                    //if not in expanded view -> self-destruct
                    stopSelf();
                    break;
                }

                 */
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        String cargo = intent.getStringExtra("ocrOutput");
        //Required for mobi.add to work
        adapter = new ArrayAdapter<CharSequence>(this, R.layout.activity_listview, mobi);
        ListView listView = (ListView) mFloatingView.findViewById(R.id.outputListView);
        listView.setAdapter(adapter);
        if (cargo == "textRecognizer is not operational"){
            mobi.clear();
            adapter.notifyDataSetChanged();
            mobi.add(Html.fromHtml("♜ Error: testRecognizer is not operational. This might mean Google playstore is still downloading the 'com.google.android.gms:play-services-vision:11.8.0' package for OCR function. Please either wait a while and try again or reinstall this app. In the meanwhile please use [manual] instead."));
            return START_STICKY;
        }

        if (cargo != null){

            List<String> outputL = new ArrayList<>();

            for(int i = 0; i < ocrListTags.size(); i++){
                String curr  = ocrListTags.get(i);
                if (cargo.indexOf(curr)!=-1){
                    if(curr=="Support"){
                        int letterE = cargo.indexOf(curr)+7;
                        if(cargo.charAt(letterE)!='e'){
                            outputL.add("Support");
                            chosenSet.add("Support");
                        }
                    } else {
                        if(curr=="Fast"){
                            outputL.add("Fast-Redeploy");
                            chosenSet.add("Fast-Redeploy");
                        }
                        else if(curr=="Recovery"){
                            outputL.add("DP-Recovery");
                            chosenSet.add("DP-Recovery");
                        }
                        else if (curr=="Crowd"){
                            outputL.add("Crowd-Control");
                            chosenSet.add("Crowd-Control");
                        }
                        else if (curr=="Starter"){
                            outputL.add("Starter");
                        }
                        else{
                            outputL.add(curr);
                            chosenSet.add(curr);
                        }
                    }
                }
            }//end of for loop

            TagsFilter tagsFilter = new TagsFilter();
            HashMap<List, List> output = tagsFilter.filterTags(chosenSet);
            Iterator it3 = output.entrySet().iterator();
            //ocrOutput.setText(chosenSet.toString());
            List<String> chosenHolder = new ArrayList<>();
            String h0 = "<span style =\"background-color:#363636;  color:black;  \"> ";
            //            String h0 = "<span style =\"background-color:#363636;\"> ";
            for (String curr : chosenSet) {
                //String newCurr = "."+h0+"<font color=#FFFFFF>"+curr+"</font> </span>";
                String newCurr = "<font color=#000000>.</font><font color=#FFFFFF>"+h0+curr+" </font>      </span> ";
                //String newCurr = ".<u>"+h0+curr+"</u></span>";
                chosenHolder.add(newCurr);
            }
            String joinedChosenTags = TextUtils.join("<font color=#000000>,</font>", chosenHolder);
            //setTextWithSpan(ocrOutput, android.graphics.Color.BLUE, "Hellhghghgh ghghghg gggghhhh   ghghghg  yy  tlll hello hogwatrs xion phantasy star ongline", 1.1f);
            //ocrOutput.setPaintFlags(ocrOutput.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            ocrOutput.setText(cargo.toString());
            mobi.clear();
            adapter.notifyDataSetChanged();
            mobi.add(Html.fromHtml("♜"+joinedChosenTags));
            //TODO:clear the mobi every scan



            while (it3.hasNext()) {
                Map.Entry ko3 = (Map.Entry) it3.next();

                //Filter good and bad tags here
                //Good tags: List.size() > 1, fastredeploy, summon, crowdcontrol, shift, nuker
                List<String> holder = new ArrayList<String>();
                List<String> holder2 = new ArrayList<String>();
                List<String> holderSpan = new ArrayList<>();

                holder = (List) ko3.getKey();
                holder2 = (List) ko3.getValue();

                String h = "<span style =\"background-color:#363636;\"> ";
                for(int i = 0; i < holder.size(); i++){
                    String curr = holder.get(i);
                    curr = "<font color=#000000>.</font>"+h+"<font color=#FFFFFF> "+curr+"</font> </span>";
                    holderSpan.add(curr);
                }


                String joinedKeyTags = TextUtils.join("<font color=#000000>,</font>", holderSpan);
                //Task: Make them go    | Support || Medic |


                String joinedValueOperators = TextUtils.join(",", holder2);
                //{Mayer(Purple), Gravel(Purple), Projekt Red(Gold), Siege(Red)
                // joinedValueOperators = "Gravel, Projekt Red"
                String[] uncoloredOps = joinedValueOperators.split(",");
                //StringJoiner sb = new StringJoiner(", ");
                //List<String> coloredList = new ArrayList<String>();

                List<String> redOps = new ArrayList<String>();
                List<String> goldOps = new ArrayList<String>();
                List<String> purpleOps = new ArrayList<String>();
                List<String> blueOps = new ArrayList<String>();


                for (int i = 0; i < uncoloredOps.length; i++) {
                    if (listTop.contains(uncoloredOps[i])) {
                        uncoloredOps[i] = "<font color=#FF3300>" + uncoloredOps[i] + "</font>";
                        redOps.add(uncoloredOps[i]);
                    } else if (listThreeStar.contains(uncoloredOps[i])) {
                        uncoloredOps[i] = "<font color=#0080FE>" + uncoloredOps[i] + "</font>";
                        blueOps.add(uncoloredOps[i]);
                    } else if (listSenior.contains(uncoloredOps[i])) {
                        uncoloredOps[i] = "<font color=#FFD700>" + uncoloredOps[i] + "</font>";
                        goldOps.add(uncoloredOps[i]);
                    } else {
                        uncoloredOps[i] = "<font color=#CC99ff>" + uncoloredOps[i] + "</font>";
                        purpleOps.add(uncoloredOps[i]);
                    }
                    //coloredList.add(uncoloredOps[i]);
                }

                List<String> coloredList = new ArrayList<String>();
                if (redOps.size() > 0) {
                    coloredList.addAll(redOps);
                } else {
                    coloredList.addAll(goldOps);
                    coloredList.addAll(purpleOps);
                    coloredList.addAll(blueOps);
                }
                String coloredOps = TextUtils.join(", ", coloredList);

                //TODO: This is what makes colored words work in HTML
                //String h = "<span style =\"background-color:#4295d6;\">";
                String listElement = joinedKeyTags +"<br/>" + coloredOps;
                mobi.add(Html.fromHtml(listElement));

            }//while loop
        } else{
            mobi.add(Html.fromHtml("When in Recruit page, press SCAN to detect the tags."));
            //mobi.add(Html.fromHtml("<script type='text/javascript' src='https://ko-fi.com/widgets/widget_2.js'></script><script type='text/javascript'>kofiwidget2.init('Support Me on Ko-fi', '#29abe0', 'B0B71OFO9');kofiwidget2.draw();</script> "));
            mobi.add(Html.fromHtml("♜ Scroll downwards to read unshown information."));
            mobi.add(Html.fromHtml("♜ Press MANUAL to manually add or select tags to filter."));
            mobi.add(Html.fromHtml("♜ Press GUARANTEED to see if any Guaranteed tag combinations exist"));
            mobi.add(Html.fromHtml("♜ Since the filter is default to 9hours timer, Starter and Robot tags would not be detected or filtered."));

            String text = "this is text";


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

/*
                Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(text)
                        .setAutoCancel(true);

                Notification notification = builder.build();

 */
                startMyOwnForeground();

            } else {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(text)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);

                Notification notification = builder.build();

                startForeground(1, notification);
            }


            // if (cargo!=null)
        }
        return START_STICKY;
    }

    public static void setTextWithSpan(final TextView textView, int backgroundColor, String text, float lineSpacingMultiplier) {
        class BackgroundColorSpanWithPaddingAndLineSpacing implements LineBackgroundSpan {
            private float roundedCornerSize;
            private int backgroundColor;
            private int paddingSize;
            private RectF rect;

            private BackgroundColorSpanWithPaddingAndLineSpacing(int backgroundColor, int paddingSize, float roundedCornerSize) {
                super();
                this.backgroundColor = backgroundColor;
                this.paddingSize = paddingSize;
                this.roundedCornerSize = roundedCornerSize;
                this.rect = new RectF();
            }

            @Override
            public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int currentLineNumber) {
                final int textWidth = Math.round(p.measureText(text, start, end));
                final int paintColor = p.getColor();

                rect.set(left - paddingSize / 2, top - paddingSize / 4, left + textWidth + paddingSize / 2, top + textView.getTextSize()+ paddingSize / 2 +6);
                p.setColor(backgroundColor);
                c.drawRoundRect(rect, roundedCornerSize, roundedCornerSize, p);
                p.setColor(paintColor);
            }
        }

        int padding = textView.getPaddingLeft();
        int radius = padding / 2;

        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        BackgroundColorSpanWithPaddingAndLineSpacing backgroundSpan = new BackgroundColorSpanWithPaddingAndLineSpacing(backgroundColor, padding, radius);
        builder.setSpan(backgroundSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setShadowLayer(padding, 0, 0, 0);
        textView.setLineSpacing(0, lineSpacingMultiplier);

        textView.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private Notification getNotification() {
        Intent intent = new Intent(this, ScreenCapture.class);

        CharSequence text = "chartexrt";

        // The PendingIntent that leads to a call to onStartCommand() in this service.
        PendingIntent servicePendingIntent = PendingIntent.getService(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // The PendingIntent to launch activity.
        PendingIntent activityPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .addAction(R.drawable.ak_widget_main_icon, "io",
                        activityPendingIntent)
                .addAction(R.drawable.ak_widget_main_icon, "io",
                        servicePendingIntent)
                .setContentText(text)
                .setContentTitle("Arknights Recruiter Widget")
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.arklauncher)
                .setTicker(text)
                .setWhen(System.currentTimeMillis());

        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID); // Channel ID
        }

        return builder.build();
    }

    private void startMyOwnForeground(){
        String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.ak_widget_main_icon)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

}
