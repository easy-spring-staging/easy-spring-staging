/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.utils;

/**
 * UUID唯一键生成工具类  .
 *
 * <p>
 * UUID唯一键生成工具类
 *
 * @author caobaoyu
 * @date 2020/11/2 9:37
 */

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
public class UuidUtil {
    public static String generateRandomUuid() {
        final Random rnd = ThreadLocalRandom.current();
        long mostSig  = rnd.nextLong();
        long leastSig = rnd.nextLong();

        // Identify this as a version 4 UUID, that is one based on a random value.
        mostSig &= 0xffffffffffff0fffL;
        mostSig |= 0x0000000000004000L;

        // Set the variant identifier as specified for version 4 UUID values.  The two
        // high order bits of the lower word are required to be one and zero, respectively.
        leastSig &= 0x3fffffffffffffffL;
        leastSig |= 0x8000000000000000L;

        return new UUID(mostSig, leastSig).toString();
    }

    public static void main(String[] args) {
        for (int i=0;i<1000;i++){
            System.out.println(generateRandomUuid());
        }
    }
}
