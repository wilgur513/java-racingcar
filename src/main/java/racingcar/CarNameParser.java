package racingcar;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class CarNameParser {

    public List<String> parse(String names) {
        validateCarNames(names);

        return Arrays.stream(splitByComma(names))
            .collect(toList());
    }

    private void validateCarNames(String names) {
        if (isInvalidLength(names)) {
            throw new IllegalArgumentException("자동차 이름은 5글자 이하여야 합니다.");
        }
        if (isEmptyName(names)) {
            throw new IllegalArgumentException("자동차 이름은 공백일 수 없습니다.");
        }
        if (isDuplicateName(names)) {
            throw new IllegalArgumentException("자동차 이름은 중복일 수 없습니다.");
        }
    }

    private boolean isEmptyName(String names) {
        return Arrays.stream(splitByComma(names)).anyMatch(String::isEmpty);
    }

    private boolean isInvalidLength(String names) {
        return Arrays.stream(splitByComma(names))
            .anyMatch(n -> n.length() > 5);
    }

    private boolean isDuplicateName(String names) {
        return Arrays.stream(splitByComma(names)).distinct().count() != splitByComma(names).length;
    }

    private String[] splitByComma(String names) {
        return names.split(",");
    }
}
