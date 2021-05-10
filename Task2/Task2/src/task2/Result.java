package task2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// DO NOT MODIFY THIS CLASS
// YOUR PROGRAM WILL BE TESTED WITH THIS VERSION OF THE CLASS

public class Result {

    private final String symbolName;
    private Set<String> firstSet;
    private Set<String> followSet;

    public Result(String symbolName) {
        this.symbolName = symbolName;
        this.firstSet = new HashSet<String>();
        this.followSet = new HashSet<String>();
    }

    public Result(String symbolName, Set<String> firstSet, Set<String> followSet) {
        this.symbolName = symbolName;
        this.firstSet = firstSet;
        this.followSet = followSet;
    }

    public Result(String symbolName, String stringFirstSet, String stringFollowSet) {
        this.symbolName = symbolName;
        this.firstSet = new HashSet<String>(Arrays.asList(stringFirstSet.trim().split(" ")));
        this.followSet = new HashSet<String>(Arrays.asList(stringFollowSet.trim().split(" ")));
        if (stringFirstSet.trim().equals(""))
            this.firstSet = new HashSet<String>();
        if (stringFollowSet.trim().equals(""))
            this.followSet = new HashSet<String>();
    }

    public void setFirstSet(Set<String> firstSet) {
        this.firstSet = firstSet;
    }

    public void setFollowSet(Set<String> followSet) {
        this.followSet = followSet;
    }

    public Set<String> getFirstSet() {
        return this.firstSet;
    }

    public Set<String> getFollowSet() {
        return this.followSet;
    }

    public String getSymbolName() {
        return symbolName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Result)) {
            return false;
        }
        Result resObj =  (Result) obj;
        Boolean sameName = (this.symbolName.equals(resObj.symbolName));
        Boolean sameFirst = (this.firstSet.equals(resObj.firstSet));
        Boolean sameFollow = (this.followSet.equals(resObj.followSet));
        return sameName && sameFirst && sameFollow;
    }
    
    public boolean equalsFirstSet(Object obj) {
        if (!(obj instanceof Result)) {
            return false;
        }
        Result resObj =  (Result) obj;
        Boolean sameName = (this.symbolName.equals(resObj.symbolName));
        Boolean sameFirst = (this.firstSet.equals(resObj.firstSet));
        return sameName && sameFirst ;
    }
    
    public boolean equalsFollowSet(Object obj) {
        if (!(obj instanceof Result)) {
            return false;
        }
        Result resObj =  (Result) obj;
        Boolean sameName = (this.symbolName.equals(resObj.symbolName));
        Boolean sameFollow = (this.followSet.equals(resObj.followSet));
        return sameName && sameFollow;
    }

    public String toString() {
        return "SYMBOL: " + this.symbolName +
            " FIRSTSET: " + this.firstSet +
            " FOLLOWSET: " + this.followSet;
    }


}
