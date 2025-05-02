package lotto;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    public void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    @Test
    public void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    // TODO: 추가 기능 구현
}
