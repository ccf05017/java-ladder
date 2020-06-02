package nextstep.ladder.domain;

import nextstep.ladder.domain.exceptions.InvalidRewardArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RewardTests {
    private Player testPlayer;

    @BeforeEach
    public void setup() {
        testPlayer = new Player("poppo", new HorizontalLocation(0, 3));
    }

    @DisplayName("상품명과 상품 당첨 Player를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        assertThat(new Reward("100", testPlayer)).isNotNull();
    }

    @DisplayName("상품명이 null이거나 빈 값일 경우 객체를 생성 할 수 없다.")
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"  ", ""})
    void createEmptyValidationTest(String invalidRewardName) {
        assertThatThrownBy(() -> new Reward(invalidRewardName, testPlayer))
                .isInstanceOf(InvalidRewardArgumentException.class);
    }

    @DisplayName("상품명은 5글자를 넘을 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = { "123456", "12345567" })
    void createTooLongNameValidationTest(String tooLongName) {
        assertThatThrownBy(() -> new Reward(tooLongName, testPlayer))
                .isInstanceOf(InvalidRewardArgumentException.class);
    }
}
