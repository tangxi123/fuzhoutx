package org.cechc.etl.test.testcase.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DataProviderUtil {
    public static Iterator<Object[]> getDataProvider(ArrayList<HashMap<String, Object>> list){


        List<Object[]> result = new ArrayList<>();
        Iterator<HashMap<String, Object>> iterator = list.iterator();

        while (iterator.hasNext()) {
            result.add(new Object[]{iterator.next()});
        }
        return result.iterator();

    }

    public static Iterator<Object[]> getDataProvider2(ArrayList<ArrayList<HashMap<String, Object>>> list){

        List<Object[]> result = new ArrayList<>();
        Iterator<ArrayList<HashMap<String, Object>>> iterator = list.iterator();
        while (iterator.hasNext()) {
            result.add(new Object[]{iterator.next()});
        }
        return result.iterator();

    }

    public static Iterator<Object[]> getDataProvider3(ArrayList<HashMap<String,ArrayList<HashMap<String, Object>>>> list){

        List<Object[]> result = new ArrayList<>();
        Iterator<HashMap<String,ArrayList<HashMap<String, Object>>>> iterator = list.iterator();
        while (iterator.hasNext()) {
            result.add(new Object[]{iterator.next()});
        }
        return result.iterator();

    }

    public static Iterator<Object[]> getDataProvider4(ArrayList<HashMap<String, Object>> list){
        ArrayList<ArrayList<HashMap<String, Object>>> tempList = new ArrayList<>();
        tempList.add(list);

        List<Object[]> result = new ArrayList<>();
        Iterator<ArrayList<HashMap<String, Object>>> iterator = tempList.iterator();

        while (iterator.hasNext()) {
            result.add(new Object[]{iterator.next()});
        }
        return result.iterator();

    }


}
