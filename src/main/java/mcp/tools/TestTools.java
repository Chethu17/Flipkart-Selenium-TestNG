package mcp.tools;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import io.modelcontextprotocol.spec.McpSchema.Tool;

public class TestTools {

    // Change this to your project folder
    private static final String PROJECT_DIR = "E:\\Automation Projects\\project_kart2";

    public static SyncToolSpecification runTestsTool() {

        Map<String, Object> inputSchema = Map.of(
                "type", "object",
                "properties", Map.of(),
                "additionalProperties", false
        );

        Tool tool = Tool.builder("run_tests", inputSchema)
                .description("Run the Flipkart TestNG suite in headless mode and return a pass/fail summary")
                .build();

        return SyncToolSpecification.builder()
                .tool(tool)
                .callHandler((exchange, request) -> {
                    try {
                        return CallToolResult.builder()
                                .content(List.of(new TextContent(runMaven())))
                                .isError(false)
                                .build();
                    } catch (Exception e) {
                        return CallToolResult.builder()
                                .content(List.of(new TextContent("Failed to run tests: " + e.getMessage())))
                                .isError(true)
                                .build();
                    }
                })
                .build();
    }

    private static String runMaven() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "mvn", "-B", "test", "-Dheadless=true");
        pb.directory(new File(PROJECT_DIR));
        pb.redirectErrorStream(true);

        Process process = pb.start();
        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        process.waitFor();

        StringBuilder summary = new StringBuilder();
        for (String line : output.split("\\R")) {
            if (line.contains("Tests run:") || line.contains("BUILD") || line.startsWith("[ERROR]")) {
                summary.append(line).append("\n");
            }
        }
        if (summary.length() == 0) {
            return "No test summary found.";
        }
        return summary.length() > 4000 ? summary.substring(0, 4000) : summary.toString();
    }
}