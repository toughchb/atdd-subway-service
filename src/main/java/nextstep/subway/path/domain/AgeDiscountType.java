package nextstep.subway.path.domain;

import java.util.Arrays;
import nextstep.subway.path.domain.policy.ChildrenDiscountPolicy;
import nextstep.subway.path.domain.policy.DiscountPolicy;
import nextstep.subway.path.domain.policy.NonDiscountPolicy;
import nextstep.subway.path.domain.policy.TeenagerDiscountPolicy;

public enum AgeDiscountType {
    CHILDREN_DISCOUNT(6, 13, new ChildrenDiscountPolicy()),
    TEENAGER_DISCOUNT(13, 19, new TeenagerDiscountPolicy());

    private int minAge;
    private int maxAge;
    private DiscountPolicy discountPolicy;

    AgeDiscountType(int minAge, int maxAge, DiscountPolicy discountPolicy) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.discountPolicy = discountPolicy;
    }

    public static DiscountPolicy findDiscountPolicyByAge(int age) {
        AgeDiscountType ageDiscountType = Arrays.stream(values()).filter(type -> type.containAgeGroup(age)).
                findFirst().orElse(null);
        if (ageDiscountType == null) {
            return new NonDiscountPolicy();
        }
        return ageDiscountType.discountPolicy;

    }

    private boolean containAgeGroup(int age) {
        return minAge <= age && age < maxAge;
    }
}
