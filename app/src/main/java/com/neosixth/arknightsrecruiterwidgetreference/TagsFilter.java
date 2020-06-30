package com.neosixth.arknightsrecruiterwidgetreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class TagsFilter {

    //TODO: listTags must align perfectly with listListTags and listTopListTags
    List<String> listTags = Arrays.asList("Senior", "Top", "Melee", "Ranged", "Guard", "Medic", "Vanguard", "Caster", "Sniper", "Defender", "Supporter", "Specialist", "Healing", "Support", "DPS", "AoE", "Slow", "Survival", "Defense", "Debuff", "Shift", "Crowd-Control", "Nuker", "Summon", "Fast-Redeploy", "DP-Recovery");

    List<String> listSenior = Arrays.asList("Texas", "Zima", "Ptilopsis", "Silence", "Warfarin", "Projekt Red", "Manticore", "Cliffheart", "FEater", "Provence", "Blue Poison", "Firewatch", "Meteorite", "Platinum", "Pramanix", "Istina", "Mayer", "Specter", "Indra", "Nearl", "Liskarm", "Vulcan", "Croissant");
    List<String> listMelee = Arrays.asList("Texas", "Zima", "Projekt Red", "Manticore", "Cliffheart", "FEater", "Specter", "Indra", "Nearl", "Liskarm", "Vulcan", "Croissant", "Scavenger", "Vigna", "Gravel", "Rope", "Shaw", "Dobermann", "Estelle", "Mousse", "Frostleaf", "Matoimaru", "Cuora", "Gummy", "Matterhorn", "Fang", "Vanilla", "Plume", "Melantha", "Beagle");
    List<String> listRanged = Arrays.asList("Ptilopsis", "Silence", "Warfarin", "Provence", "Blue Poison", "Firewatch", "Meteorite", "Platinum", "Pramanix", "Istina", "Mayer", "Myrrh", "Perfumer", "Haze", "Gitano", "ShiraYuki", "Meteor", "Jessica", "Earthspirit", "Ansel", "Lava", "Steward", "Kroos", "Adnachiel", "Orchid");
    List<String> listGuard = Arrays.asList("Specter", "Indra", "Dobermann", "Estelle", "Mousse", "Frostleaf", "Matoimaru", "Melantha");
    List<String> listMedic = Arrays.asList("Ptilopsis", "Silence", "Warfarin", "Myrrh", "Perfumer", "Hibiscus", "Ansel");
    List<String> listVanguard = Arrays.asList("Texas", "Zima", "Scavenger", "Vigna", "Fang", "Vanilla", "Plume");
    List<String> listCaster = Arrays.asList("Haze", "Gitano", "Lava", "Steward");
    List<String> listSniper = Arrays.asList("Provence", "Blue Poison", "Firewatch", "Meteorite", "Platinum", "ShiraYuki", "Meteor", "Jessica", "Kroos", "Adnachiel");
    List<String> listDefender = Arrays.asList("Nearl", "Liskarm", "Vulcan", "Croissant", "Cuora", "Gummy", "Matterhorn", "Beagle");
    List<String> listSupporter = Arrays.asList("Pramanix", "Istina", "Mayer", "Earthspirit", "Orchid");
    List<String> listSpecialist = Arrays.asList("Projekt Red", "Manticore", "Cliffheart", "FEater", "Gravel", "Rope", "Shaw");
    List<String> listHealing = Arrays.asList("Ptilopsis", "Silence", "Warfarin", "Nearl", "Myrrh", "Perfumer", "Gummy", "Hibiscus", "Ansel");
    List<String> listSupport = Arrays.asList("Warfarin", "Zima", "Ptilopsis", "Dobermann");
    List<String> listDPS = Arrays.asList("Manticore", "Cliffheart", "Provence", "Blue Poison", "Firewatch", "Platinum", "Istina", "Indra", "Liskarm", "Vulcan", "Scavenger", "Vigna", "Haze", "Meteor", "Jessica", "Dobermann", "Mousse", "Frostleaf", "Matoimaru", "Plume", "Steward", "Kroos", "Adnachiel", "Melantha");
    List<String> listAOE = Arrays.asList("Meteorite", "Specter", "Gitano", "ShiraYuki", "Estelle", "Lava");
    List<String> listSlow = Arrays.asList("FEater", "Istina", "ShiraYuki", "Earthspirit", "Frostleaf", "Orchid");
    List<String> listSurvival = Arrays.asList("Manticore", "Specter", "Indra", "Vulcan", "Jessica", "Estelle", "Matoimaru", "Melantha");
    List<String> listDefense = Arrays.asList("Nearl", "Liskarm", "Vulcan", "Croissant", "Gravel", "Cuora", "Gummy", "Matterhorn", "Beagle");
    List<String> listDebuff = Arrays.asList("Meteorite", "Pramanix", "Haze", "Meteor");
    List<String> listShift = Arrays.asList("Cliffheart", "FEater", "Croissant", "Rope", "Shaw");
    List<String> listCrowdControl = Arrays.asList("Texas", "Projekt Red", "Mayer");
    List<String> listNuker = Arrays.asList("Firewatch");
    List<String> listSummon = Arrays.asList("Mayer");
    List<String> listFastRedeploy = Arrays.asList("Projekt Red", "Gravel");
    List<String> listDPRecovery = Arrays.asList("Texas", "Zima", "Scavenger", "Vigna", "Fang", "Vanilla", "Plume");
    List<String> listThreeStar = Arrays.asList("Beagle", "Melantha", "Hibiscus", "Ansel", "Lava", "Steward", "Kroos", "Adnachiel", "Fang", "Vanilla", "Plume", "Orchid");


    //Shared between top and bottom list section
    List<String> listTop = Arrays.asList("Siege", "Shining", "Nightingale", "Ifrit", "Exusiai", "SilverAsh", "Hoshiguma", "Saria");

    //Tags only for top operators
    List<String> listTopSenior = Arrays.asList();
    List<String> listTopMelee = Arrays.asList("Siege", "SilverAsh", "Hoshiguma", "Saria");
    List<String> listTopRanged = Arrays.asList("Ifrit", "Exusiai", "Shining", "Nightingale");
    List<String> listTopGuard = Arrays.asList("SilverAsh");
    List<String> listTopMedic = Arrays.asList("Shining", "Nightingale");
    List<String> listTopVanguard = Arrays.asList("Siege");
    List<String> listTopCaster = Arrays.asList("Ifrit");
    List<String> listTopSniper = Arrays.asList("Exusiai");
    List<String> listTopDefender = Arrays.asList("Hoshiguma", "Saria");
    List<String> listTopSupporter = Arrays.asList();
    List<String> listTopSpecialist = Arrays.asList();
    List<String> listTopHealing = Arrays.asList("Shining", "Nightingale", "Saria");
    List<String> listTopSupport = Arrays.asList("Shining", "Nightingale", "Saria", "SilverAsh");
    List<String> listTopDPS = Arrays.asList("Siege", "Exusiai", "SilverAsh", "Hoshiguma");
    List<String> listTopAOE = Arrays.asList("Ifrit");
    List<String> listTopSlow = Arrays.asList();
    List<String> listTopSurvival = Arrays.asList();
    List<String> listTopDefense = Arrays.asList("Hoshiguma", "Saria");
    List<String> listTopDebuff = Arrays.asList("Ifrit");
    List<String> listTopShift = Arrays.asList();
    List<String> listTopCrowdControl = Arrays.asList();
    List<String> listTopNuker = Arrays.asList();
    List<String> listTopSummon = Arrays.asList();
    List<String> listTopFastRedeploy = Arrays.asList();
    List<String> listTopDPRecovery = Arrays.asList("Siege");


    //Rare tags: fastredeploy, summon, crowdcontrol, shift, nuker,
    List<List<String>> listListTags;
    //listMedic, listVanguard, listCaster, listSniper, listDefender, listSupporter, listSpecialist, listHealing

    //Top operator only version of listListTags. share use of listTop
    List<List<String>> listTopListTags;
    //roster is the calculated output of chosen tags, like Zima, [support, vanguard]
    HashMap<String, List> roster = new HashMap<String, List>();
    HashMap<List, List> output = new HashMap<List, List>();
    Set<String> chosenSet = new HashSet<>();


    public HashMap<List, List> filterTags(Set<String> chosenSet0) {
        chosenSet = chosenSet0;
        listListTags = Arrays.asList(listSenior, listTop, listMelee, listRanged, listGuard, listMedic, listVanguard, listCaster, listSniper, listDefender
                , listSupporter, listSpecialist, listHealing, listSupport, listDPS, listAOE, listSlow, listSurvival, listDefense, listDebuff, listShift, listCrowdControl, listNuker, listSummon, listFastRedeploy, listDPRecovery);

        listTopListTags = Arrays.asList(listTopSenior, listTop, listTopMelee, listTopRanged, listTopGuard, listTopMedic, listTopVanguard, listTopCaster, listTopSniper, listTopDefender
                , listTopSupporter, listTopSpecialist, listTopHealing, listTopSupport, listTopDPS, listTopAOE, listTopSlow, listTopSurvival, listTopDefense, listTopDebuff, listTopShift, listTopCrowdControl, listTopNuker, listTopSummon, listTopFastRedeploy, listTopDPRecovery);

        String listElement = "";
        if (chosenSet.contains("Top")) {
            //chosenSet contains 'top operator' tag
            //hence, manually check if other present tags will help narrow down possible top operator
            //only 6* will be shown
            for (int i = 0; i < listTopListTags.size(); i++) {
                if (chosenSet.contains(listTags.get(i))) {
                    for (int j = 0; j < listTopListTags.get(i).size(); j++) {
                        if (!roster.containsKey(listTopListTags.get(i).get(j))) {
                            List<String> newL = new ArrayList<String>();
                            newL.add(listTags.get(i));
                            roster.put(listTopListTags.get(i).get(j), newL);
                        } else {
                            List<String> existedL = new ArrayList<String>();
                            existedL = roster.get(listTopListTags.get(i).get(j));
                            existedL.add(listTags.get(i));
                            roster.put(listTopListTags.get(i).get(j), existedL);
                        }
                    }
                }
            }
        } else {
            //Do calculations here: for non 6*
            for (int i = 0; i < listListTags.size(); i++) {
                if (chosenSet.contains(listTags.get(i))) {

                    //looping through respective tag lists, like listDPS = {"Manticore", "Cliffheart"...}
                    for (int j = 0; j < listListTags.get(i).size(); j++) {
                        if (!roster.containsKey(listListTags.get(i).get(j))) {
                            List<String> newL = new ArrayList<String>();
                            newL.add(listTags.get(i));
                            roster.put(listListTags.get(i).get(j), newL);
                        } else {
                            List<String> existedL = new ArrayList<String>();
                            existedL = roster.get(listListTags.get(i).get(j));
                            existedL.add(listTags.get(i));
                            roster.put(listListTags.get(i).get(j), existedL);
                        }
                    }
                }
            }
        }
        //Reverse roster<String:OperatorName, List:Tags> into output<List:TagCombinations, List:QualifiedOperators>
        for (Map.Entry<String, List> entry : roster.entrySet()) {
            String key = entry.getKey();
            List<String> value = new ArrayList<String>();
            value = entry.getValue();
            //if output has not have this tag yet
            //note, value can be ["aoe", "caster", "ranged"]
            List<List<String>> valueCombi = getAllCombinations(value);
            for (int i = 0; i < valueCombi.size(); i++) {
                if (!output.containsKey(valueCombi.get(i))) {
                    //note, value can be ["aoe", "caster", "ranged"]
                    List<String> newL = new ArrayList<String>();
                    newL.add(key); //add operator to a newly created array
                    //put operator list into this tag
                    //check if filtering 6*
                    if (chosenSet.contains("Top")) {
                        List<String> topL = new ArrayList<>();
                        topL.add("Top");
                        //if (valueCombi.get(i).equals(topL) || !valueCombi.get(i).contains("Top")) {
                        if (!valueCombi.get(i).contains("Top")) {
                        } else {
                            output.put(valueCombi.get(i), newL);
                        }
                    } else {
                        output.put(valueCombi.get(i), newL);
                    }
                } else {
                    List<String> existedL = new ArrayList<String>();
                    existedL = output.get(valueCombi.get(i));
                    existedL.add(key);
                    //output.put(valueCombi.get(i), existedL);

                    if (chosenSet.contains("Top")) {
                        List<String> topL = new ArrayList<>();
                        topL.add("Top");
                        //if (valueCombi.get(i).equals(topL) || !valueCombi.get(i).contains("Top")) {
                        if (!valueCombi.get(i).contains("Top")) {

                        } else {
                            output.put(valueCombi.get(i), existedL);
                        }
                    } else {
                        output.put(valueCombi.get(i), existedL);
                    }
                }
            }
        }//End of for(Map.Entry...

        return output;
        //ArrayList<String> listOutputGood = new ArrayList<>();
        /*
        Iterator it3 = output.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry ko3 = (Map.Entry) it3.next();

            //Filter good and bad tags here
            //Good tags: List.size() > 1, fastredeploy, summon, crowdcontrol, shift, nuker
            List<String> holder = new ArrayList<String>();
            List<String> holder2 = new ArrayList<String>();

            holder = (List) ko3.getKey();
            holder2 = (List) ko3.getValue();


            String joinedKeyTags = TextUtils.join("+", holder);
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
            listElement = "[" + joinedKeyTags + "]<br/>" + coloredOps;

        }//end of while loop

        return listElement;
        */
    }

    public List<List<String>> getAllCombinations(List<String> elements) {
        List<List<String>> combinationList = new ArrayList<List<String>>();
        for (long i = 1; i < Math.pow(2, elements.size()); i++) {
            List<String> list = new ArrayList<String>();
            for (int j = 0; j < elements.size(); j++) {
                if ((i & (long) Math.pow(2, j)) > 0) {
                    list.add(elements.get(j));
                }
            }
            combinationList.add(list);
        }
        return combinationList;
    }

}
