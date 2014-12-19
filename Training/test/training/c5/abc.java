package training.c5;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

public class abc {

	public static void main(String[] args) {

		Zoo zoo = new Zoo("Bei Jing Zoo", "Bei Jing");
		
		try {
			zoo.add(new Animal("panda1", "F", "UN", 120));
			zoo.add(new Animal("panda2", "M", "CN", 189));
			
			XMLOutputter outputer = new XMLOutputter();
			
			Document d = abc.serializeObject(zoo);
			outputer.output(d, System.out);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static Field[] getInstanceVariables(Class cls) {
		LinkedList<Field> accum = new LinkedList<Field>();
		while(cls != null) {
			Field[] fields = cls.getDeclaredFields();
			for(int i = 0; i < fields.length; i++) {
				if(!Modifier.isStatic(fields[i].getModifiers())) {
					accum.add(fields[i]);
				}
			}
			cls = cls.getSuperclass();
		}
		
		return (Field[])accum.toArray(new Field[accum.size()]);
	}
	
	public static Document serializeObject(Object source) throws Exception {
		return serializeHelper(source, new Document(new Element("serialized")), new IdentityHashMap());
	}
	
	
	public static Document serializeHelper(Object source, Document target, Map table) throws Exception {
		String id = String.valueOf(table.size());
		table.put(source, id);
		
		Class sourceClass = source.getClass();
		Element oElt = new Element("object");
		oElt.setAttribute("class", sourceClass.getName());
		oElt.setAttribute("id", id);
		target.getRootElement().addContent(oElt);
		
		if(!sourceClass.isArray()) {
			Field[] fields = getInstanceVariables(sourceClass);
			for(int i = 0; i < fields.length; i++) {
				if(!Modifier.isPublic(fields[i].getModifiers())) {
					fields[i].setAccessible(true);
				}
				
				Element fElt = new Element("field");
				fElt.setAttribute("name", fields[i].getName());
				fElt.setAttribute("declaringClass", fields[i].getDeclaringClass().getName());
				Class fieldType = fields[i].getType();
				
				Object child = fields[i].get(source);
				if(Modifier.isTransient(fields[i].getModifiers())) {
					child = null;
				}
				
				fElt.addContent(serializeVariable(fieldType, child, target, table));
				oElt.addContent(fElt);
			}
		} else {
			Class componentType = sourceClass.getComponentType();
			int length = Array.getLength(source);
			oElt.setAttribute("length", Integer.toString(length));
			
			for(int i = 0; i < length; i++) {
				oElt.addContent(serializeVariable(componentType, Array.get(source, i), target, table));
			}
		}
		
		return target;
	}
	
	public static Element serializeVariable(Class fieldType, Object child, Document target, Map table) throws Exception {
		if(child == null) {
			return new Element("null");
		} else if(!fieldType.isPrimitive()) {
			Element reference = new Element("reference");
			if(table.containsKey(child)) {
				reference.setText(table.get(child).toString());
			} else {
				reference.setText(Integer.toString(table.size()));
				serializeHelper(child, target, table);
			}
			return reference;
		} else {
			Element ele = new Element("value");
			ele.setText(child.toString());
			return ele;
		}
	}

}
