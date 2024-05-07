package com.sajal.experiment;

public class Example {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Either<User, String> result = userService.getPerson(1);

        result.fold(
                user -> System.out.println("User: " + user.getName()),
                error -> System.out.println("Error: " + error)
        );
    }
}
