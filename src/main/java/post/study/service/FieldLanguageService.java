package post.study.service;

import org.springframework.stereotype.Service;
import post.study.norm.field;
import post.study.norm.language;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FieldLanguageService {
    public List<String> fieldList() {
        ArrayList<String> fieldList = new ArrayList<>();
        for (field f : field.values()) {
            fieldList.add(f.name());
        }
        return fieldList;
    }

    public List<String> languageList() {
        ArrayList<String> languageList = new ArrayList<>();
        for (language l : language.values()) {
            languageList.add(l.name());
        }
        return languageList;
    }

    public List<String> getFieldList(String field) {
        if (field == null)
            return null;
        ArrayList<String> fieldList = new ArrayList<>();
        String[] fList = field.split(",");
        for (String s : fList)
            fieldList.add(s);

        return fieldList;

    }

    public List<String> getLanguageList(String language) {
//        new ArrayList<>(Arrays.asList(language.split(",")))
        if (language == null)
            return null;
        ArrayList<String> languageList = new ArrayList<>();
        String[] lList = language.split(",");
        for (String l : lList)
            languageList.add(l);
        return languageList;

    }

    public String getUrlParam(String field, String language) throws UnsupportedEncodingException {
        String urlParam="&";

        if (field == null && language == null)
            return null;
        if(field!=null) {
            String[] fList = field.split(",");

            for (String f : fList)
                urlParam += "field=" + f + "&";
        }

        if(language!=null){
            String[] lList = language.split(",");

            for(String l: lList)
                urlParam+="language="+l+"&";

        }
        return URLDecoder.decode(urlParam.substring(0,urlParam.length()-1),"UTF-8");

    }
}
