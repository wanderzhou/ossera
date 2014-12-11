package training.c3;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * converter between object and xml
 * 
 * @author admin
 *
 */
public class XmlUtil {
	
	public static String  objectToXml(Object object) throws JAXBException {
		//default encoding
		return objectToXml(object, "UTF-8");
	}

	public static String objectToXml(Object object, String encoding) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
		
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
		
		StringWriter writer = new StringWriter();
		marshaller.marshal(object, writer);
		String str = writer.toString();
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T xmlToObject(String xml, Class<T> c) throws JAXBException {
		T t = null;
		
		JAXBContext jaxbContext = JAXBContext.newInstance(c);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		t = (T)unmarshaller.unmarshal(new StringReader(xml));
		
		return t;		
	}
}
