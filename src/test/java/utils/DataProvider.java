package utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "data-provider")
    public Object[][] getData() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {

        Data data = XMLWorker.getData();
        Object[][] array = new Object[data.getList().size()][2];

        for(int i=0; i <data.getList().size(); i++){
            array[i][0]=data.getList().get(i);
            array[i][1]=data.getMaxPrice();
        }

        return array;
    }


}
