package com.bandtec.br.finoban.service;

//import com.bandtec.br.finoban.dominio.DAO.TestResultReportDAO;
//import com.bandtec.br.finoban.dominio.TestResult;
//import com.google.common.io.Files;
//import com.google.gson.Gson;
//import com.opencsv.CSVWriter;
//import com.opencsv.bean.StatefulBeanToCsv;
//import com.opencsv.bean.StatefulBeanToCsvBuilder;
//import com.opencsv.exceptions.CsvDataTypeMismatchException;
//import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
//import org.apache.commons.exec.CommandLine;
//import org.apache.commons.exec.DefaultExecutor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;

//import java.io.File;
//import java.io.IOException;
//import java.io.Writer;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;

//@Service
public class TestResultService {

//    private static final String MAVEN_TEST_COMMAND = "mvn test";
//    private static final Path PATH = Paths.get("target", "allure-results");
//    private static final String REPORT_RESULT_FILENAME = "./testsresults.csv";
//    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();
//    private static final String TESTING_MAVEN_PYSCRIPT = "exectests.py";
//    private String pythonExec;
//    private Gson gson;
//    private List<File> filesTestResult;
//    private List<TestResult> fileResults;
//    private File folder;
//    private Integer runCount;
//
//    public TestResultService() {
//        this.runCount = 1;
//        this.gson = new Gson();
//        this.filesTestResult = new ArrayList<>();
//        this.fileResults = new ArrayList<>();
//        this.folder = PATH.toFile();
//        this.pythonExec = OS_NAME.contains("win") ? "py" : "python3";
//    }
//
//    //@Scheduled(fixedDelay = 1_200_000)
//    @Scheduled(fixedDelay = 75_000)
//    public void executar() throws InterruptedException, IOException {
//
//        printMessageWithCount("Testing started.");
//        this.filesTestResult.clear();
//        this.fileResults.clear();
//
//        printMessageWithCount("Maven Execution Tests started.");
//
//        // EXECUTAR TESTES DO MAVEN
//        Runtime runtime = Runtime.getRuntime();
//        Process process = runtime.exec(MAVEN_TEST_COMMAND);
//        process.waitFor();
//        process.destroy();
//        process.destroyForcibly();
////         CommandLine exec = CommandLine.parse(pythonExec+" "+TESTING_MAVEN_PYSCRIPT);
////         DefaultExecutor executor = new DefaultExecutor();
////         executor.execute(exec);
//
//        printMessageWithCount("Maven Execution Tests finalized.");
//
//        // VERIFICA SE PASTA 'allure-results' ESTA VAZIA
//        if (folder.listFiles() == null) {
//            printMessageWithCount("Folder 'allure-results' is Empty.");
//            return;
//        }
//
//        printMessageWithCount("Reading tests results files.");
//        this.preencherArquivosResultadosTestes();
//
//        printMessageWithCount("Serializing JSON tests results to Java Objects.");
//        this.obterConteudoResultadosTestes();
//
//        printMessageWithCount("Generating CSV tests report started.");
//        this.gerarCsvDeConteudosResultadosTestes();
//        printMessageWithCount("Finished generating CSV with tests report.");
//
//        this.filesTestResult.forEach(File::delete);
//
//        printMessageWithCount("Testing finished.");
//        this.runCount++;
//    }
//
//    private void printMessageWithCount(String message) {
//        // RTT = REAL-TIME TESTING
//        System.out.printf("[FINOBAN RTT] #%d - %s\n", this.runCount, message);
//    }
//
//    private void preencherArquivosResultadosTestes() {
//        for (File file : Objects.requireNonNull(folder.listFiles())) {
//            String fileName = file.getName();
//            if (!fileName.endsWith("container.json")
//                    && !fileName.equalsIgnoreCase("threat.py")
//                    && !fileName.equalsIgnoreCase("result.csv"))
//            {
//                this.filesTestResult.add(file);
//            }
//        }
//    }
//
//    private void obterConteudoResultadosTestes() {
//        this.filesTestResult.forEach(file -> {
//            try {
//                String conteudo = Files.asCharSource(file, StandardCharsets.UTF_8).read();
//                this.fileResults.add(new TestResult(gson.fromJson(conteudo, TestResultReportDAO.class)));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    private void gerarCsvDeConteudosResultadosTestes() {
//        try (
//                Writer writer = java.nio.file.Files.newBufferedWriter(Paths.get(REPORT_RESULT_FILENAME));
//        ) {
//            StatefulBeanToCsv<TestResult> beanToCsv = new StatefulBeanToCsvBuilder<TestResult>(writer)
//                    .withSeparator(';')
//                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
//                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
//                    .build();
//
//            beanToCsv.write(fileResults);
//
//        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
//            e.printStackTrace();
//        }
//    }
} 
