package com.krymlov.xmlparser.parsers;

import com.krymlov.xmlparser.object.Inhabitant;

import java.util.List;

public class ParserXML {

    private ParserXML(){}

    public static List<Inhabitant> parseXML(String method, String filePath, String keyword){
        List<Inhabitant> inhabitants = null;
        if (method.equals("DOM")){
            inhabitants = DOMParserXML.parseXML(filePath, keyword);
        }else if (method.equals("SAX")){
            inhabitants = SAXParserXML.parseXML(filePath, keyword);
        }
        return inhabitants;
    }

}

