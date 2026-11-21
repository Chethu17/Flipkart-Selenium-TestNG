
package mcp;

import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import base.DriverFactory;
import io.modelcontextprotocol.json.McpJsonDefaults;
import io.modelcontextprotocol.spec.McpSchema.ServerCapabilities;

import mcp.tools.BrowserTools;
import mcp.tools.TestTools;

public class MCPServer {

    public static void main(String[] args) {

        // 1. Create STDIO transport
        StdioServerTransportProvider transportProvider = new StdioServerTransportProvider(McpJsonDefaults.getMapper());

        // 2. Initialize MCP server  // 3. Declare server capabilities // 4. Register selenium browser tool // 5. Build server
        McpSyncServer server = McpServer.sync(transportProvider).serverInfo("flipkart-selenium-mcp", "1.0.0").capabilities(ServerCapabilities.builder()
                                .tools(true).build()).tools(BrowserTools.openBrowserTool(),TestTools.runTestsTool()).build();

        // 6. Graceful shutdown
        Runtime.getRuntime().addShutdownHook(
        	    new Thread(() -> {
        	        DriverFactory.quitDriver();
        	        server.close();
        	    })
        	);

        System.err.println("Flipkart Selenium MCP Server is ready.");

        // 7. Keep server alive
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    }
}
