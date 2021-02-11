package racingcar.domain;

import racingcar.utils.RandomNumberGenerator;
import racingcar.view.RacingCarError;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cars {
    private final List<Car> carList = new ArrayList<>();

    public Cars(List<String> carNameList) {
        checkOverlappedNames(carNameList);
        checkNull(carNameList);
        carNameList.stream()
                .forEach(carName -> carList.add(new Car(carName)));
    }

    private static int findMaxPosition(List<Car> carList) {
        int maxPosition = 0;
        for (Car car : carList) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }
        return maxPosition;
    }

    private static void checkOverlappedNames(List<String> nameCandidates) {
        Set<String> targetSet = new HashSet<>(nameCandidates);
        if (nameCandidates.size() != targetSet.size()) {
            RacingCarError.overlapped();
        }
    }

    private static void checkNull(List<String> nameCandidates) {
        if (nameCandidates.size() == 0) {
            RacingCarError.nullCar();
        }
    }

    public void processOneTurn() {
        carList.forEach(car -> car.oneTurn(RandomNumberGenerator.turnNumber()));
    }

    public List<Car> getList() {
        return this.carList;
    }

    public String findWinners() {
        int maxPosition = findMaxPosition(this.carList);
        List<String> winnerList = new ArrayList<>();
        carList.stream()
                .filter(carElement -> (carElement.checkPosition(maxPosition)))
                .map(Car::getName)
                .forEach(winnerList::add);
        return String.join(", ", winnerList);
    }
}