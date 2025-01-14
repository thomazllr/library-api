package com.thomaz.library.model.dto;

import java.util.List;

public record Error(int status, String message, List<ErrorField> erros) {

}
