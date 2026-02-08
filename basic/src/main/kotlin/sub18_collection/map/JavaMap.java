package sub18_collection.map;

import java.util.HashMap;
import java.util.Map;

public class JavaMap {

    public static void main(String[] args) {

        Map<Integer, String> immutableMap = Map.of(1, "one", 2, "two", 3, "three"); // 불변

        HashMap<Integer, String> mutableMap = new HashMap<>(); // 가변
        mutableMap.put(1, "one");
        mutableMap.put(2, "two");


        // key
        for (int key : immutableMap.keySet()) {
            System.out.println("key : " + key + ", value : " + immutableMap.get(key));
        }

        // entry
        for (Map.Entry<Integer, String> entry : immutableMap.entrySet()) {
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
        }
    }
}
