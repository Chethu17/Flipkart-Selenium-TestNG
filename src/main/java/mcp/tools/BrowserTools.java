
package mcp.tools;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import base.DriverFactory;

import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import io.modelcontextprotocol.spec.McpSchema.Tool;

public class BrowserTools {

    public static SyncToolSpecification openBrowserTool() {

        // 1. Define the input schema
        Map<String, Object> inputSchema = Map.of(
                "type", "object",
                "properties", Map.of(),
                "additionalProperties", false
        );

        // 2. Define the MCP tool
        Tool tool = Tool.builder("open_browser", inputSchema).description("Launch Google Chrome browser using Selenium").build();

        // 3. Define tool execution logic
        return SyncToolSpecification.builder()
                .tool(tool)
                .callHandler((exchange, request) -> {

                    try {

                        WebDriver driver = DriverFactory.getDriver();

                        return CallToolResult.builder()
                                .content(List.of(
                                        new TextContent(
                                        		"Chrome browser launched successfully. Current URL: "
                                        		        + driver.getCurrentUrl()
                                        )
                                ))
                                .isError(false)
                                .build();

                    } catch (Exception e) {

                        return CallToolResult.builder()
                                .content(List.of(
                                        new TextContent(
                                                "Failed to launch browser: "
                                                        + e.getMessage()
                                        )
                                ))
                                .isError(true)
                                .build();
                    }
                })
                .build();
    }
}
