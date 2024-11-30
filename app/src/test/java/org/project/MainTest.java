package org.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue; // 추가된 import

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTest {
    @Test
    void testMainMethodOutput() {
        // 출력 스트림 캡처용
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Main 클래스의 main 메서드 실행
        String[] args = {}; // main 메서드는 매개변수를 받지만, 이 테스트에서는 필요 없음
        Main.main(args);

        // 출력 내용이 예상과 일치하는지 확인
        String output = outContent.toString();
        assertTrue(output.contains("Trade History"));
        assertTrue(output.contains("UserA"));
        assertTrue(output.contains("UserB"));
    }
}
