import javax.swing.text.Document;
import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class FontBase {

    public static ArrayList<String> ListInputFonts = new ArrayList<String>();
    public static ArrayList<String> ListBaseFonts = new ArrayList<String>();

    public static ArrayList<String> ListDepenaliseFonts = new ArrayList<String>();


    private static ArrayList<String> GetListBaseFonts()
    {

        SAXBuilder parser = new SAXBuilder();
        org.jdom2.Document xmlDoc;

        try {
            xmlDoc = parser.build(new File("xml\\listfonts.xml"));


            List elements = xmlDoc.getRootElement().getContent(new ElementFilter("list"));
            Iterator iterator = elements.iterator();
            while (iterator.hasNext())
            {
                Element list = (Element)iterator.next();
                ListBaseFonts.add(String.valueOf(list.getChild("font")));
            }


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ListBaseFonts;
    }

    public static void addFontToXML(String nameNewFont)
    {
        ArrayList<String> OldFonts = new ArrayList<>();

        OldFonts = GetListBaseFonts();

        org.jdom2.Document xmlDoc =new org.jdom2.Document();

        Element root = new Element("list");
        xmlDoc.setRootElement(root);

        Element font = new Element("font");

        for(int i =0;i<ListBaseFonts.size();i++)
        {
            font.addContent(ListBaseFonts.get(i));
            root.addContent(font);
        }

        font.addContent(nameNewFont);
        root.addContent(font);


        try {

            Format fmt = Format.getPrettyFormat();
            XMLOutputter serializer = new XMLOutputter(fmt);

            serializer.output(xmlDoc, new FileOutputStream(new File("xml\\listfonts.xml")));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void PowerXml()
    {
        ListBaseFonts = GetListBaseFonts();
        int checkNumberDepenalToClientFonts =0;



    }

    private static int CheckEqualNumberFonts()
    {
        return ListBaseFonts.size()-ListInputFonts.size();
    }


}
