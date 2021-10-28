package com.bandtec.br.finoban.dominio;

import lombok.Getter;
import lombok.Setter;

public class TestReport {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String stage;

    @Getter
    @Setter
    private Long start;

    @Getter
    @Setter
    private Long stop;

    @Getter
    @Setter
    private String fullName;

}
