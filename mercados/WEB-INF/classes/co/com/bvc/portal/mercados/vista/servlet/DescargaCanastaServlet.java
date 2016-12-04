package co.com.bvc.portal.mercados.vista.servlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class DescargaCanastaServlet.
 */
public class DescargaCanastaServlet extends HttpServlet {

	/** The Constant serialVersionUID. */     
	private static final long serialVersionUID = 8100652803298874616L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getParameter("path");
		try {
			FileInputStream archivo = new FileInputStream(path);
			int longitud = archivo.available();
			byte[] datos = new byte[longitud];
			archivo.read(datos);
			archivo.close();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment;filename=" + getNombreArchivo(path));
			ServletOutputStream ouputStream = resp.getOutputStream();
			ouputStream.write(datos);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the nombre archivo.
	 * 
	 * @param path the path
	 * 
	 * @return the nombre archivo
	 */
	private String getNombreArchivo(String path){
		String[] partes = path.split("[/]");
		if (partes != null && partes.length > 0){
			return partes[partes.length - 1];
		}
		return "";
	}
	
}
