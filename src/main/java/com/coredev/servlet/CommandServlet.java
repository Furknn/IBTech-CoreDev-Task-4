package com.coredev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;

import com.coredev.types.CDBag;
import com.coredev.utils.CommandExecuter;
import com.coredev.utils.XmlHelper;

/**
 * Servlet implementation class AddressServlet
 */
public class CommandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Document document = XmlHelper.parse(request.getInputStream());
			CDBag inBag = CDBag.fromDocument(document);
			System.out.println(inBag.toString());
			CDBag outbag = CommandExecuter.getInstance().execute(inBag);
			XmlHelper.dump(outbag.toDocument(), response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
