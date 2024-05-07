package com.sajal.experiment;

public class UserService {
    Either<User,String> getPerson(int id){
        if (id == 1) {
            return new Success<>(new User("John Doe",20));
        } else {
            return new Failure<>("User not found");
        }
    }
}
