package org.example.theBestUIEver;

import java.util.ArrayList;

import static org.example.expProcessing.ProccesExpByLib.solvingExpressionByLib;

public class ProccesExpByLibStrategy implements Strategy {
    @Override
    public ArrayList<String> getProcessedArray(ArrayList<String>  exp) {
        ArrayList<String> ans = new ArrayList<>();

        for (String expk : exp) {
           ans.add(solvingExpressionByLib(expk));
        }
        return ans;
    }
}
