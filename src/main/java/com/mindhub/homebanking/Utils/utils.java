package com.mindhub.homebanking.Utils;

import java.time.LocalDateTime;

public class utils {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


}
