package zkgbai.military;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haplo on 11/14/2015.
 */
public class UnitClasses {
    public List<String> raiders;
    public List<String> assaults;
    public List<String> arties;
    public List<String> striders;
    public List<String> mobSupports;
    public List<String> loners;

    public UnitClasses(){
        this.raiders = new ArrayList<String>();
        this.assaults = new ArrayList<String>();
        this.arties = new ArrayList<String>();
        this.striders = new ArrayList<String>();
        this.mobSupports = new ArrayList<String>();
        this.loners = new ArrayList<String>();

        // raiders
        raiders.add("armpw");
        raiders.add("spherepole");
        raiders.add("corak");
        raiders.add("armkam");

        // assaults; stuff that attacks in mobs
        assaults.add("armzeus");
        assaults.add("armwar");
        assaults.add("armrock");
        assaults.add("gunshipsupport");
        assaults.add("armbrawl");
        assaults.add("blackdawn");

        // mobSupport: things that increase the strength of mobs
        mobSupports.add("spherecloaker");
        mobSupports.add("core_spectre");

        // striders; stuff that can dgun
        striders.add("dante");
        striders.add("scorpion");

        // strider-like stuff that does better on its own than in mobs
        loners.add("armsnipe");
    }
}
