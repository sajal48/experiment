package com.sajal.experiment;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Example {
    public static void main(String[] args) {
//        UserService userService = new UserService();
//        Either<User, String> result = userService.getPerson(1);
//
//        result.fold(
//                user -> System.out.println("User: " + user.getName()),
//                error -> System.out.println("Error: " + error)
//        );

        Map<String,Object> test = new HashMap<>();
        Map<String,Object> inventory = new HashMap<>();
        test.put("variantId","123");
        test.put("count",1);
        inventory.put("variantMappedInventories",test);

        System.out.println(getQuantity(inventory,"123"));

    }
    public static int getQuantity(final Map<String, Object> inventory,
                            final String variantId) {

        return (int) Optional.ofNullable((Map) inventory.get("variantMappedInventories"))
                .map(it -> (Map) it.get(variantId))
                .map(it -> it.get("count"))
                .orElse(0);
    }
}
