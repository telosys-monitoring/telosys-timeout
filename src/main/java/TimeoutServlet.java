

import java.io.IOException;
import java.io.Writer;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TimeoutServlet
 */
public class TimeoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		timeout(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		timeout(request, response);
	}

	public void timeout(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		System.out.println("timeout -- begin");

		String t = request.getParameter("t");
		if(t == null) {
			t = (String)request.getAttribute("t");
		}
		Long time = null;
		if(t != null) {
			time = Long.valueOf(t);
		} else {
			time = 20000L;
		}
		System.out.println("wait for "+time+" ms");
		try {
			Thread.sleep(time);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		final Writer w = response.getWriter();
		final String str = "Test Timeout - time = "+time+" - random : "+UUID.randomUUID();
		w.write(str);
		System.out.println(str);
		w.flush();
		System.out.println("timeout -- end");
	}

}
