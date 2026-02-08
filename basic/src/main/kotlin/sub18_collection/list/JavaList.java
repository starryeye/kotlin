package sub18_collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaList {

    public static void main(String[] args) {

        List<Integer> immutableList = Arrays.asList(1, 2, 3); // 불변, List.of(1, 2, 3); 와 동일
        ArrayList<Integer> mutableList = new ArrayList<>(); // 가변

        // index 로 access
        System.out.println(immutableList.get(0));


        // enhanced for loop
        for (Integer e : immutableList) {
            System.out.println(e);
        }
        
        
        // for loop
        for (int i = 0; i < immutableList.size(); i++) {
            System.out.println(immutableList.get(i));
        }


        // forEach
        immutableList.forEach(System.out::println);

    }
}
