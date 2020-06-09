/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.96
 * Generated at: 2019-10-17 13:36:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.view.include;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <meta charset=\"UTF-8\">\r\n");
      out.write("  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("  <title>로그인</title>\r\n");
      out.write("  <!-- Tell the browser to be responsive to screen width -->\r\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("\r\n");
      out.write("  <!-- Font Awesome -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"../..assets/plugins/fontawesome-free/css/all.min.css\">\r\n");
      out.write("  <!-- Ionicons -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css\">\r\n");
      out.write("  <!-- icheck bootstrap -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"../../assets/plugins/icheck-bootstrap/icheck-bootstrap.min.css\">\r\n");
      out.write("  <!-- Theme style -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"../../assets/dist/css/adminlte.min.css\">\r\n");
      out.write("  <!-- Google Font: Source Sans Pro -->\r\n");
      out.write("  <link href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700\" rel=\"stylesheet\">\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"hold-transition login-page\">\r\n");
      out.write("<div class=\"login-box\">\r\n");
      out.write("  <div class=\"login-logo\">\r\n");
      out.write("    <a href=\"#\"><b>Cafe-in</b> Admin</a>\r\n");
      out.write("  </div>\r\n");
      out.write("  <!-- /.login-logo -->\r\n");
      out.write("  <div class=\"card\">\r\n");
      out.write("    <div class=\"card-body login-card-body\">\r\n");
      out.write("   \r\n");
      out.write("      <form action=\"loginUser.jsp\" method=\"post\">\r\n");
      out.write("        <div class=\"input-group mb-3\">\r\n");
      out.write("          <input type=\"text\" class=\"form-control\" name=\"usID\" placeholder=\"아이디\">\r\n");
      out.write("          <div class=\"input-group-append\">\r\n");
      out.write("            <div class=\"input-group-text\">\r\n");
      out.write("              <span class=\"fas fa-envelope\"></span>\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"input-group mb-3\">\r\n");
      out.write("          <input type=\"password\" class=\"form-control\" name=\"usPw\" placeholder=\"비밀번호\">\r\n");
      out.write("          <div class=\"input-group-append\">\r\n");
      out.write("            <div class=\"input-group-text\">\r\n");
      out.write("              <span class=\"fas fa-lock\"></span>\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("          <div class=\"col-8\">\r\n");
      out.write("            <div class=\"icheck-primary\">\r\n");
      out.write("              <input type=\"checkbox\" id=\"remember\">\r\n");
      out.write("              <label for=\"remember\">\r\n");
      out.write("            \t비밀번호 기억하기\r\n");
      out.write("              </label>\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("          <!-- /.col -->\r\n");
      out.write("          <div class=\"col-4\">\r\n");
      out.write("            <button type=\"submit\" class=\"btn btn-primary btn-block btn-flat\">로그인</button>\r\n");
      out.write("          </div>\r\n");
      out.write("          <!-- /.col -->\r\n");
      out.write("        </div>\r\n");
      out.write("      </form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("      <p class=\"mb-1\">\r\n");
      out.write("        <a href=\"#\">비밀번호 찾기</a>\r\n");
      out.write("      </p>\r\n");
      out.write("      <p class=\"mb-0\">\r\n");
      out.write("        <a href=\"register.html\" class=\"text-center\">회원가입</a>\r\n");
      out.write("      </p>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- /.login-card-body -->\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- /.login-box -->\r\n");
      out.write("\r\n");
      out.write("<!-- jQuery -->\r\n");
      out.write("<script src=\"../../assets/plugins/jquery/jquery.min.js\"></script>\r\n");
      out.write("<!-- Bootstrap 4 -->\r\n");
      out.write("<script src=\"../../assets/plugins/bootstrap/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
