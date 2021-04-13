package io.github.siaust.code_sharer.Error;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String info) {
        super(info);
    }
}
