package training.c6;

import java.util.Iterator;
import java.util.Set;

import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

public class JpsTool {

	public static void main(String[] args) {
		//Jps.main(new String[0]);
		
		System.out.println(javaProcessExists("abc"));
		
		System.out.println(javaProcessExists("training.c3.Server"));
	}
	
	public static boolean javaProcessExists(String mainClass) {
		String hostName = null; //default localhost
				
		try {
			HostIdentifier hostId = new HostIdentifier(hostName);
			
			MonitoredHost monitoredHost =
			        MonitoredHost.getMonitoredHost(hostId);
			
			Set<Integer> jvms = monitoredHost.activeVms();
			for(Iterator<Integer> iterator = jvms.iterator(); iterator.hasNext();) {
				int lvmid = ((Integer)iterator.next()).intValue();
				
				MonitoredVm vm;
				String vmidString = "//" + lvmid + "?mode=r";
				
				VmIdentifier id = new VmIdentifier(vmidString);
                vm = monitoredHost.getMonitoredVm(id, 0);
                
                String runMainClass = MonitoredVmUtil.mainClass(vm, true);
                System.out.println(runMainClass);
                if(mainClass.equals(runMainClass)) {
                	return true;
                }
				
			}
		} catch (MonitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
