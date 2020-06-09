<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="ko">
<%@ include file ="header.jsp" %>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
<%@ include file ="nav_header.jsp" %>  
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
	<%@ include file ="aside/menu_aside_all.jsp" %> 
  </aside>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">메뉴관리</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">홈</a></li>
              <li class="breadcrumb-item active">메뉴관리</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

	<!-- Main content -->
    <div class="content">
      <div class="container-fluid">
        <div class="row">
      <!-- content 시작 -->
      <div class="col-12">
      
      
      
      
      <div class="card">
        <div class="card-header">
          <button type="button" class="btn btn-primary float-right" 
          data-toggle="modal" data-target="#menu_insert"
          onclick="menu_insert">등록하기</button>
        </div>
        <!-- /.card-header -->
        <div class="card-body">
          <table id="menuTBL" class="table table-bordered table-striped">
            <thead>
            <tr>
              <th>메뉴번호</th>
              <th>메뉴종류</th>
              <th>메뉴이름</th>
              <th>메뉴가격</th>
              <th>수정/삭제</th>
            </tr>
            </thead>
            <tbody>
            
		 <%
             //메뉴에 대한 것.
             int menuNum, menuPrice;
             String menuName, menuType, menuImg, menuTem;
             ConnectDB connectDB = ConnectDB.getInstance();
             Connection con = connectDB.openConnection();
             Statement stmt = con.createStatement();
             String sql = "select mn_num, mn_name, mn_price, " +
                       "(CASE mn_Type " +
                     "WHEN 'c' THEN '커피' " +
                     "WHEN 'n' THEN '논커피' " +
                     "WHEN 'b' THEN '베이커리' " +
                     "END " +
                     ") AS type, mn_img, mn_tem " +
                     "from menu";
             ResultSet rs = stmt.executeQuery(sql);
              
             while(rs.next()) {
                menuNum = rs.getInt(1);
                menuName = rs.getString(2);
                menuPrice = rs.getInt(3);
                menuType = rs.getString(4);
                menuImg = rs.getString(5);
                menuTem = rs.getString(6);	    
	    		    
          %>   
             <tr>
                <td><%= menuNum %></td>
                <td><%= menuType %></td>
                <td><%= menuName %></td>
                <td><%= menuPrice %></td>
                <td>
                <button type="button" class="btn btn-primary" 
                 data-toggle="modal" data-target="#menu_edit"
                 onclick="menu_edit('<%=menuNum%>', '<%=menuName%>', '<%=menuPrice%>', 
                 '<%=menuType%>', '<%=menuImg%>', '<%=menuTem%>')">수정</button>
                <button type="button" class="btn btn-danger" 
                onclick="location.href='menu_delete.jsp?mn_num=<%= menuNum %>'">삭제</button>
                </td>
             </tr>
           <%
              }
              rs.close();
              stmt.close();
              connectDB.closeConnection(con);
           %> 
 
            </tbody>
            <tfoot>
            <tr>
              <th>메뉴번호</th>
              <th>메뉴종류</th>
              <th>메뉴이름</th>
              <th>메뉴가격</th>
              <th>수정/삭제</th>
            </tr>
            </tfoot>
          </table>
        </div>
        <!-- /.card-body -->
      </div>
      <!-- /.card -->
      
      <!-- 메뉴 등록 모달 -->
      <div class="modal fade" id="menu_insert">
       <div class="modal-dialog">
         <div class="modal-content">
           <div class="modal-header">
                           메뉴 등록
             <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
             </button>
           </div>
           <div class="modal-body">
             <form name="menuInsert" method="post" action="" enctype="Multipart/form-data">
                <input type="hidden" class="form-control" id="mn_num" name="mn_num">
                <div class="form-group">
                  <label for="mn_Type" class="control-label">메뉴 종류 : &nbsp;</label>
                  <input type = "radio" name = "mn_Type" value = "c" checked> &nbsp; 커피 &nbsp;&nbsp;
                  <input type = "radio" name = "mn_Type" value = "n"> &nbsp; 논커피 &nbsp;&nbsp;
                  <input type = "radio" name = "mn_Type" value = "b"> &nbsp; 베이커리 &nbsp;&nbsp;
                </div>
                
              <div class="form-group">
               <label for="mn_img" class="control-label">메뉴 사진 : &nbsp;</label>
               <input id="fileInput" type="file" name="mn_img" data-class-button="btn btn-default" 
               data-class-input="form-control" data-button-text="" 
               data-icon-name="fa fa-upload" class="form-control" tabindex="-1" 
               style="position: absolute; clip: rect(0px 0px 0px 0px);" accept="image/*">
               <div class="bootstrap-filestyle input-group">
                <input type="text" id="userfile" class="form-control" name="userfile">
                <span class="group-span-filestyle input-group-btn" tabindex="0">
                <label for="fileInput" class="btn btn-default ">
                <span class="glyphicon fa fa-upload"></span>
                </label>
               </span>
               </div>
              </div>

                <div class="form-group">
                  <label for="mn_name" class="control-label">메뉴 이름 : &nbsp;</label>
                  <input type="text" class="form-control" id="mn_name" name="mn_name" maxlength="11">
                </div>
                <div class="form-group">
                  <label for="mn_price" class="control-label">메뉴 가격 : &nbsp;</label>
                  <input type="text" class="form-control" id="mn_price" name="mn_price" 
                  maxlength="5" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                </div>
                
                <div class="form-group">
                  <label for="mn_tem" class="control-label">온도 : &nbsp;</label><br>
                  <input type = "checkbox" name = "mn_tem" id = "h" value = "h">
                  <label for="mn_tem" class="control-label" id = "h"> &nbsp; Hot </label><br>
                  <input type = "checkbox" name = "mn_tem" id ="i" value = "i">
                  <label for="mn_tem" class="control-label" id = "i"> &nbsp; Ice </label>
                </div>
                
                <p><b>재료 : &nbsp;</b></p>
                <%@ include file="ingredient_menu.jsp" %>  
             </form>
             
             
           </div>
           <div class="modal-footer justify-content-between">
             <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
             <button type="button" class="btn btn-primary" onClick="mnInsert();">등록</button>
           </div>
         </div>
         <!-- /.modal-content -->
       </div>
       <!-- /.modal-dialog -->
     </div>
     <!-- /.modal -->
     
    <!-- 메뉴 수정 모달 -->
      <div class="modal fade" id="menu_edit">
       <div class="modal-dialog">
         <div class="modal-content">
           <div class="modal-header">
                           메뉴 수정
             <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
             </button>
           </div>
           <div class="modal-body">
             <form name="menuUpdate" method="post" action="" enctype="Multipart/form-data">
                <input type="hidden" class="form-control" id="mn_num_edit" name="mn_num_edit">
                <div class="form-group">
                  <label for="mn_Type_edit" class="control-label">메뉴 종류 : &nbsp;</label>
                  <input type = "radio" name = "mn_Type_edit" id = "cRadio" value = "c"> &nbsp; 커피 &nbsp;&nbsp;
                  <input type = "radio" name = "mn_Type_edit" id = "nRadio" value = "n"> &nbsp; 논커피 &nbsp;&nbsp;
                  <input type = "radio" name = "mn_Type_edit" id = "bRadio" value = "b"> &nbsp; 베이커리 &nbsp;&nbsp;
                </div>
                
              <div class="form-group">
               <label for="mn_img_edit" class="control-label">메뉴 사진 : &nbsp;</label>
               <input id="fileInput_edit" type="file" name="mn_img_edit" data-class-button="btn btn-default" 
               data-class-input="form-control" data-button-text="" 
               data-icon-name="fa fa-upload" class="form-control" tabindex="-1" 
               style="position: absolute; clip: rect(0px 0px 0px 0px);" accept="image/*">
               <div class="bootstrap-filestyle input-group">
                <input type="text" id="userfile_edit" class="form-control" name="userfile_edit">
                <span class="group-span-filestyle input-group-btn" tabindex="0">
                <label for="fileInput_edit" class="btn btn-default ">
                <span class="glyphicon fa fa-upload"></span>
                </label>
               </span>
               </div>
              </div>

                <div class="form-group">
                  <label for="mn_name_edit" class="control-label">메뉴 이름 : &nbsp;</label>
                  <input type="text" class="form-control" id="mn_name_edit" name="mn_name_edit" maxlength="11">
                </div>
                <div class="form-group">
                  <label for="mn_price_edit" class="control-label">메뉴 가격 : &nbsp;</label>
                  <input type="text" class="form-control" id="mn_price_edit" name="mn_price_edit" 
                  maxlength="5" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                </div>
                
                <div class="form-group">
                  <label for="mn_tem_edit" class="control-label">온도 : &nbsp;</label><br>
                  <input type = "checkbox" name = "mn_tem_edit" id = "h_edit" value = "h">
                  <label for="mn_tem" class="control-label" id = "h_edit"> &nbsp; Hot </label><br>
                  <input type = "checkbox" name = "mn_tem_edit" id ="i_edit" value = "i">
                  <label for="mn_tem_edit" class="control-label" id = "i_edit"> &nbsp; Ice </label>
                </div>
                
                <p><b>재료 : &nbsp;</b></p>
             </form>           
              
			 <%@ include file ="ingredient_menu2.jsp" %>  
             
             
           </div>
           <div class="modal-footer justify-content-between">
             <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
             <button type="button" class="btn btn-primary" onClick="mnUpdate();">수정</button>
           </div>
         </div>
         <!-- /.modal-content -->
       </div>
       <!-- /.modal-dialog -->
     </div>
     <!-- /.modal -->
      
      
      
      
      
        
         
      </div>
      <!-- /.col-12 -->  

      <!-- content 끝 -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
<%@ include file="footer.jsp" %>
</div>
<!-- ./wrapper -->    


  
