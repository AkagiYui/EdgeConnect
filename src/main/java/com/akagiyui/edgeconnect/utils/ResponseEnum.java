package com.akagiyui.edgeconnect.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author AkagiYui
 */

@Getter
@AllArgsConstructor
public enum ResponseEnum {
    SUCCESS(10000, ""),
    GENERAL_ERROR(10001, "General error"),
    INTERNAL_ERROR(10002, "Internal error"),
    NOT_FOUND(10003, "Not found");

    private final Integer code;
    private final String msg;
}
