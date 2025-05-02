package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ApplicationTest {
    private final java.io.InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws Exception {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8.name()));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void 정상적인_로또_시뮬레이션_실행_테스트() throws Exception {
        String input = "8000\n1,2,3,4,5,6\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        Application.main(new String[]{});

        String output = outputStream.toString(StandardCharsets.UTF_8.name());
        assertThat(output).contains("구입금액을 입력해 주세요.");
        assertThat(output).contains("8개를 구매했습니다.");
        assertThat(output).contains("[");
        assertThat(output).contains("당첨 번호를 입력해 주세요.");
        assertThat(output).contains("보너스 번호를 입력해 주세요.");
        assertThat(output).contains("당첨 통계");
        assertThat(output).contains("3개 일치 (5,000원)");
        assertThat(output).contains("4개 일치 (50,000원)");
        assertThat(output).contains("5개 일치 (1,500,000원)");
        assertThat(output).contains("5개 일치, 보너스 볼 일치 (30,000,000원)");
        assertThat(output).contains("6개 일치 (2,000,000,000원)");
        assertThat(output).contains("총 수익률은");
        assertThat(output).contains("%");
    }

    @ParameterizedTest
    @CsvSource({"8500", "1000j"})
    public void 구입금액이_1000원_단위가_아니면_예외_발생_테스트(String input) throws Exception {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        Application.main(new String[]{});

        String output = outputStream.toString(StandardCharsets.UTF_8.name());
        assertThat(output).contains("[ERROR]");
    }
}
