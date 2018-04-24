package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.BlockChain;

@WebServlet("/nodes/seeAll")
public class CheckAllNodes extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	BlockChain blockChain = BlockChain.getInstance();
    	Map<String, Object> response = new HashMap<String, Object>();
    	response.put("Nodes", blockChain.getNodes());
    	response.put("Total Node Number", blockChain.getNodes().size());
    	
    	JSONObject jsonResponse = new JSONObject(response);
    	resp.setContentType("application/json");
    	PrintWriter printWriter = resp.getWriter();
    	printWriter.println(jsonResponse);
    	printWriter.close();
    }
    //***********************************************************************************************************

}
