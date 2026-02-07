package sub17_array;

public class JavaArray {

    /**
     * effective java 에서 배열(Array) 보다는 List 를 사용하라고 함..
     */

    public static void main(String[] args) {

        int[] arr = {100, 200};

        // enhanced for loop 사용가능
        for (int a : arr) {
            System.out.println(a);
        }

        // 일반적인 for loop
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
