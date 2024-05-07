package com.sajal.experiment;

import java.util.function.Consumer;

// Failure case
    record Failure<T, E>(E error) implements Either<T, E> {
        @Override
        public void fold(Consumer<? super T> successConsumer,
                         Consumer<? super E> failureConsumer) {
            failureConsumer.accept(error());
        }
    }