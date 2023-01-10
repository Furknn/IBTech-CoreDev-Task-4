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
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long addressId = Long.parseLong(request.getParameter("addressid"));
		CDBag bag = new CDBag();
		bag.put("command", "address_get");
		bag.put("id", addressId);
		try {
			CDBag outBag = CommandExecuter.getInstance().execute(bag);
			Document doc = outBag.toDocument();
			XmlHelper.dump(doc, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document document;
		try {
			document = XmlHelper.parse(request.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		CDBag inBag = CDBag.fromDocument(document);
		inBag.put("command","address_create");
		
		System.out.println(inBag.toString());
		
		CDBag outbag;
		try {
			outbag =CommandExecuter.getInstance().execute(inBag);
			XmlHelper.dump(outbag.toDocument(), response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
