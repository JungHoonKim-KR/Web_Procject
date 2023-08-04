package post.study.service;

import org.springframework.stereotype.Service;
import post.study.norm.field;
import post.study.norm.language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FieldLanguageService {
    public List<String> fieldList(){
        ArrayList<String> fieldList = new ArrayList<>();
        for (field f : field.values()) {
            fieldList.add(f.name());
        }
    return fieldList;
    }

    public List<String> languageList(){
        ArrayList<String> languageList = new ArrayList<>();
        for(language l: language.values()){
            languageList.add(l.name());
        }
        return  languageList;
    }

    public List<String> getFieldList(String field) {
        ArrayList<String> fieldList = new ArrayList<>();
        String [] fList =field.split(",");
        for(String s: fList)
            fieldList.add(s);

        return fieldList;

    }

    public List<String> getLanguageList(String language){
//        new ArrayList<>(Arrays.asList(language.split(",")))
        if(language==null)
            return null;
        ArrayList<String> languageList = new ArrayList<>();
        String [] lList=language.split(",");
        for(String l: lList)
            languageList.add(l);
        return languageList;

    }
}
