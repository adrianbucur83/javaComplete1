package ro.scoala_informala.javaComplete1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Prefix {
    private Name name;
    private Idd idd;

    public String getSimplifiedPrefix() {

        List<String> suffixes = idd.getSuffixes() == null? new ArrayList<>() : idd.getSuffixes();
        String firstSuffix =  suffixes.isEmpty()? "": suffixes.get(0);

        return idd.getRoot() + firstSuffix + " " +  name.getCommon();
    }

}
