package com.akagiyui.edgeconnect.exception;

import com.akagiyui.edgeconnect.utils.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author AkagiYui
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private ResponseEnum status;
}
