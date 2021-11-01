package com.bandtec.br.finoban.infraestrutura.helpers;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.io.IOException;

public class ProcessHelper {

    public static DefaultExecutor executarComando(String shellCommand) throws IOException {
        CommandLine exec = CommandLine.parse(shellCommand);
        DefaultExecutor executor = new DefaultExecutor();
        executor.execute(exec);
        return executor;
    }
}
