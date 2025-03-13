package ro.scoala_informala.javaComplete1.model;

import lombok.Data;

import java.util.List;

@Data
public class Idd {
    private String root;
    private List<String> suffixes;
}
