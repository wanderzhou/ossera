package training.c5;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import training.c2.Staff;

public class ReflectionMain {

	public static void main(String[] args) {
		InputStreamReader reader = new InputStreamReader(System.in);

		BufferedReader bufferedReader = new BufferedReader(reader);
		try {
			System.out.println("Please input extra path for searching class(jar file path, please enter for none): ");
			String searchPath = bufferedReader.readLine();

			System.out.println("Please input the class name to reflection: ");
			String className = bufferedReader.readLine();

			try {
				ReflectionMain reflectionMain = new ReflectionMain();
				
				Class<?> c;
				if(!searchPath.trim().equals("")) {
					c = reflectionMain.loadClass(searchPath, className);
				} else {
					c = Class.forName(className);
				}
				reflectionMain.showClassInfo(c);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Class<?> loadClass(String path, String  className) throws Exception {
		File dir = new File(path);
		if(!dir.exists()) {
			throw new Exception("input path does not exist.");
		} else {
			List<URL> urlList = new ArrayList<URL>();
			File[] files = dir.listFiles();
			for(File f : files) {
				if(f.isFile() && f.getName().endsWith(".jar")) {
					URL url = new URL("file", null, f.getAbsolutePath());
					urlList.add(url);
				}
			}
			
			if(urlList.size() > 0) {
				ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
				URL[] urls = new URL[urlList.size()];
				ClassLoader loader = new URLClassLoader((URL[])urlList.toArray(urls),
						systemClassLoader);
				return loader.loadClass(className);
			}
		}
		return null;
	}

	public void showClassInfo(String className) {
		try {
			Class<?> c = Class.forName(className);
			showClassInfo(c);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void showClassInfo(Class<?> c) {
		System.out.println(c);
		
		//annotations
		System.out.println("list annotations:");
		Annotation[] annotations = c.getDeclaredAnnotations();
		for (Annotation n : annotations) {
			System.out.println(n.toString());
		}
		System.out.println();

		// list all constructor methods
		System.out.println("list constructors:");
		Constructor<?>[] cts = c.getConstructors();
		for (Constructor<?> item : cts) {
			System.out.println(item.toString());
		}
		System.out.println();

		// super class
		Type typeSuper = c.getGenericSuperclass();
		if(typeSuper != null) {
			System.out.println("super class: " + typeSuper.toString());
			System.out.println();
		}

		// interfaces
		System.out.println("list interfaces:");
		Type[] typeInterfaces = c.getGenericInterfaces();
		for (Type t : typeInterfaces) {
			System.out.println(t.toString());
		}
		System.out.println();

		// list all fields
		Field[] fields = c.getDeclaredFields();
		System.out.println("list fields:");
		for (Field f : fields) {
			System.out.println(f.toString());
			//generic information
			Type genType = f.getGenericType();
			if(genType instanceof ParameterizedType) {
				Type[] tParaTypes = ((ParameterizedType) genType).getActualTypeArguments();
				int i = 0;
				int j = 0;
				for(Type tmpType : tParaTypes) {
					System.out.println("\t[" + i + "," + j + "]" + tmpType.toString());
				}
			}
		}
		System.out.println();

		// list all methods
		Method[] methods = c.getDeclaredMethods();
		System.out.println("list methods:");
		for (Method m : methods) {
			System.out.println(m.toString());
			//generic information for return
			Type retType = m.getGenericReturnType();
			if(retType instanceof ParameterizedType) {
				Type[] tParaTypes = ((ParameterizedType) retType).getActualTypeArguments();
				int i = 0;
				int j = 0;
				for(Type tmpType : tParaTypes) {
					System.out.println("\treturn[" + i + "," + j + "]" + tmpType.toString());
				}
			}
			
			//generic information for parameter
			Type[] types = m.getGenericParameterTypes();
			int i = 0;
			for(Type t : types) {
				if(t instanceof ParameterizedType) {
					Type[] tParaTypes = ((ParameterizedType) t).getActualTypeArguments();
					int j = 0;
					for(Type tmpType : tParaTypes) {						
						System.out.println("\tpara[" + i + "," + j + "]" + tmpType.toString());
						j++;
					}
				}
				i++;
			}
			
		}
		System.out.println();

		// class
		System.out.println("list classes:");
		Class<?>[] cs = c.getDeclaredClasses();
		for (Class<?> tc : cs) {
			System.out.println(tc.toString());
		}

		// interfaces
		/*
		 * System.out.println("list interfaces:"); Class[] cis =
		 * c.getInterfaces(); for(Class tcis : cis) {
		 * System.out.println(tcis.toString()); } System.out.println();
		 */

		Method m = c.getEnclosingMethod();
		if (m != null) {
			System.out.println(m.toString());
		}

	}

	/**
	 * for other test reflection
	 */
	@SuppressWarnings("unused")
	private void print() {
		System.out.println("print test from private method");
	}

	/**
	 * for other test reflection
	 */
	@SuppressWarnings("unused")
	private int add(int a, int b) {
		return a + b;
	}

	/**
	 * for other test reflection
	 */	
	public String[] getTempNames(boolean showAll, List<Integer> ids, int flag, List<String> arr, Long[] data) {
		return null;
	}
	
	/**
	 * for other test reflection
	 */		
	public List<Staff> getSomeStaff(List<Integer> ids) {
		return null;
	}
	
	//fields for other test
	public List<String> departments;
}
