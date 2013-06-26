package cn.meebox.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList idc = new ArrayList();
		idc.add("huamu");
		idc.add("ningbo");
		idc.add("nujiang");
		idc.add("taiyuan");
		idc.add("quanhua");
		idc.add("guangzhou");
		
		String dc = null;
		Iterator it = idc.iterator();
		
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		

	}

}
