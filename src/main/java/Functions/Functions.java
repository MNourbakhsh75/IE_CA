package Functions;

import AuctionData.Skills;
import itemException.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Functions {

    public static void checkForEnoughSkills(ArrayList<Skills> uSkills, ArrayList<Skills> pSkills) throws NotEnoughSkillsException {
        ArrayList<Skills> u = uSkills;
        ArrayList<Skills> p = pSkills;
        ArrayList<Skills> t = new ArrayList<Skills>();
        for (Skills pkey : p) {
            for(Skills ukey : u){
                if (pkey.getName().equals(ukey.getName())) {
                    if(pkey.getPoint() <= ukey.getPoint()){
                        t.add(ukey);
                    }
                }
            }
        }
        if(t.size() != p.size())
            throw new NotEnoughSkillsException();
    }
}
