package Java_Advanced.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//По запросу гет можно обращаться к Енумам
@AllArgsConstructor
//Даёт установить значение каждому Енуму
public enum Gender {

    MALE("Мужчина"),
    FEMALE("Женщина");

    private final String description;


}
