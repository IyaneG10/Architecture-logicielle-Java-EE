package com.al.project.SysCo.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;




public class DataTopic {


    public int getRpiId() {
        return rpiId;
    }

    public boolean isState() {
        return state;
    }

    public String getTopicName() {
        return topicName;
    }

    public double getTopicValue() {
        return topicValue;
    }

    public Date getDate() {
        return date;
    }

    public void setRpiId(int rpiId) {
        this.rpiId = rpiId;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTopicValue(double topicValue) {
        this.topicValue = topicValue;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private int rpiId;
    private boolean state;
    private String topicName;
    private double topicValue;
    private Date date;


    public DataTopic xmlStringToData(String dataString){

        DataTopic newDataObj = new DataTopic();

        //Document xml = convertStringToDocument( dataString );
        try{

            String xmlStr = dataString;

            System.out.println("Sample.xml contents = "+ xmlStr);

            org.w3c.dom.Document doc = toXmlDocument(xmlStr);
            return newDataObj;
        }
        catch(Exception e ){
            e.printStackTrace();
            return null;
        }

        //final Element[] rootNodes = xml.getRootElements();
        //System.out.println(rootNodes.toString());

        /*for(Element el : list){
            System.out.println("Nom : " + el.getNodeName() + " - Valeur : " + el.getTextContent());
        }

        newDataObj.setDate(getDate());
        newDataObj.setRpiId(id);
        newDataObj.setState(State());
        newDataObj.setTopicName(exchangeNameList[topicNumber]);
        newDataObj.setTopicValue(listExchangeName.get(topicNumber).getValue());
        */
    }


    protected String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }

        return null;
    }


    public String dataToXmlString() {
        try {
            return String.format("<RPi><Id>%s</Id><Topic><Name>%s</Name><Value>%s</Value></Topic><State>%s</State><Date>%s</Date></RPi>",
                    this.getRpiId(),
                    this.getTopicName(),
                    this.getTopicValue(),
                    this.isState(),
                    this.getDate());
        }
        catch (Exception e){
            return null;
        }
    }
}
