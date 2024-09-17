package com.sajal.experiment;

import java.util.function.Consumer;

record Success<T, E>(T data) implements Either<T, E> {
        @Override
        public void fold(Consumer<? super T> successConsumer,
                         Consumer<? super E> failureConsumer) {
            successConsumer.accept(data());
        }
    }