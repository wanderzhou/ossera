package training.c5;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import training.c2.Staff;

public class OtherTest {

	public static void main(String[] args) {
		
		try {
			Class<?> c = ReflectionMain.class;
			Object obj = c.newInstance();
			
			Method m = c.getDeclaredMethod("print");
			//change permission
			m.setAccessible(true);
			
			m.invoke(obj);
			
			
			m = c.getDeclaredMethod("add", new Class[]{int.class, int.class});
			m.setAccessible(true);
			
			Object result = m.invoke(obj, new Object[]{new Integer(1), new Integer(2)});
			System.out.println(result);
			
			Object arr = Array.newInstance(String.class, 10);
			Array.set(arr, 5, "abcde");
			System.out.println(Array.get(arr, 5));
			
			//field
			Class<?> cc = Staff.class;
			Staff staff = new Staff(10, "old");
			Field name = cc.getDeclaredField("name");
			name.setAccessible(true);
			name.set(staff, "new");
			System.out.println(staff.getName());
			
			//
			ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
			URL url = new URL("file:///D:\\document\\testfolder\\test.jar");
			
			System.out.println(url.getProtocol());
			System.out.println(url.getHost());
			System.out.println(url.getFile());
			
			ClassLoader loader = new URLClassLoader(new URL[]{url}, systemClassLoader);
			loader.loadClass("training.c4.MyExample");
			
			Class<?> myExample = loader.loadClass("training.c4.OtherObject");
			Object o1 = myExample.newInstance();
			Object o1_2 = myExample.newInstance();
			
			//load with another class load
			URL url2 = new URL("file:///D:\\document\\testfolder\\jar\\test2.jar");
			ClassLoader loader2 = new URLClassLoader(new URL[]{url2});
			Class<?> myExample2 = loader2.loadClass("training.c4.OtherObject");
			Object o2 = myExample2.newInstance();
			//true
			System.out.println(o1.equals(o1_2));
			
			//java.lang.ClassCastException
			System.out.println(o1.equals(o2));
			
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
