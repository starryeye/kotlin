package dev.practice.sub11_use.java;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TryWithResources {

    public static void main(String[] args) {


        /**
         * try-with-resources 구문으로 예외 처리
         *
         * try 괄호 안에 effectively final 변수를 넣을 수 있다.
         * 여기서 PrintWriter 는 Closeable 인터페이스를 구현하는 클래스이며, close 메서드를 오버라이딩 하였다.
         * close 를 자동으로 호출해준다. (개발자는 finally 에서 따로 close 를 직접 코딩할 필요 없음)
         */
        try (PrintWriter writer = new PrintWriter("test.txt")) {
            writer.println("Hello, world!!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
