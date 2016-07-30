package com.xtronlabs.koochooloo.util.network.response_models;

public interface ProcessResponseInterface<T> {

    <T> void processResponse(T response);

}
