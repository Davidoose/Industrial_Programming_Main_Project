package org.example.problem;

import java.util.List;

public class Problem {
   private List<String> problems;


    public Problem(List<String> problems) {
        this.problems = problems;
    }

    public List<String> getProblems() {
        return problems;
    }

    public void setProblems(List<String> problems) {
        this.problems = problems;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "problems=" + problems +
                '}';
    }
}
