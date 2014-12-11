package training.c5;

import java.util.LinkedHashMap;

public class ReflectionMainTest {

	public static void main(String[] args) {

		ReflectionMain reflectionMain = new ReflectionMain();

		// reflectionMain.getClassInfo(String.class);
		 reflectionMain.getClassInfo(LinkedHashMap.class);
		// reflectionMain.getClassInfo(training.c3.Company.class);
		//reflectionMain.getClassInfo("training.c5.ReflectionMainTest");


		/*URLClassLoader tmp;
		try {
			tmp = new URLClassLoader(new URL[] { new URL("D:\\Workspace\\Training\\bin") }) {
				@Override
				public synchronized Class<?> loadClass(String name)
						throws ClassNotFoundException {
					if ("training.c4.MyExample".equals(name))
						return findClass(name);
					return super.loadClass(name);
				}
			};
			reflectionMain.getClassInfo(tmp.loadClass("training.c4.MyExample"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		


	}

}
