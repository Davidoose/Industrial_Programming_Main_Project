package org.example.theBestUIEver;
import java.util.ArrayList;

import static org.example.expProcessing.ProccesExp.solvingExpression;

public class ProccesExpStrategy implements Strategy {
    @Override
    public ArrayList<String> getProcessedArray(ArrayList<String> exp) {
        ArrayList<String> ans = new ArrayList<>();

        for (String expk : exp) {
            ans.add(solvingExpression(expk));
        }
         return ans;
    }
}
