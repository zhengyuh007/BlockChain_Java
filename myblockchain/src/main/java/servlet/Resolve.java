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

@WebServlet("/nodes/resolve")
public class Resolve extends HttpServlet {
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	BlockChain blockChain = BlockChain.getInstance();
    	// call resolve conflict method -> consensus algorithm
    	// to make sure the longest valid block chain is for all nodes
    	boolean success =  blockChain.resolveConflicts();
    	Map<String, Object> response = new HashMap<String, Object>();
    	response.put("chain", blockChain.getChain());
    	response.put("length", blockChain.getChain().size());
    	response.put("Update Success?", success);
    	
    	JSONObject jsonResponse = new JSONObject(response);
    	resp.setContentType("application/json");
    	PrintWriter printWriter = resp.getWriter();
    	printWriter.println(jsonResponse);
    	printWriter.close();
    }
    //***********************************************************************************************************
	
}
