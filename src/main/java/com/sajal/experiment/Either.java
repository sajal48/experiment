package com.sajal.experiment;


import java.util.function.Consumer;

sealed interface Either<T, E> permits Success, Failure {
    void fold(Consumer<? super T> successConsumer,
              Consumer<? super E> failureConsumer);
}