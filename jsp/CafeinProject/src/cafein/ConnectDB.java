package cafein;

import java.sql.Connection;
import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConnectDB {
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public ConnectDB() {

    }

    /*private String jdbcUrl = "jdbc:mysql://localhost:3306/testcafein?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private String dbId = "testcafehyw";
    private String dbPw = "pw0785";*/
    
    private String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/cafein?zeroDateTimeBehavior=convertToNull";
    private String dbId = "cafein";
    private String dbPw = "";
    
    private String className = "org.gjt.mm.mysql.Driver";
    //private String className = "com.mysql.cj.jdbc.Driver";
    
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt2 = null;
    private ResultSet rs = null;
    private String sql = "";
    private String sql2 = "";

    //사용자 회원가입
    public String registerDB(String id, String pw, String name) {
    	String returns = "";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
        	Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select us_id from user where us_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("us_id").equals(id)) { // �̹� ���̵� �ִ� ���
                    returns = "hasId";
                }
            } else { // �Է��� ���̵� ���� ���
                sql2 = "insert into user (us_id, us_pw, us_name) values(?,?,?)";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, id);
                pstmt2.setString(2, pw);
                pstmt2.setString(3, name);
                pstmt2.executeUpdate();

                returns = "ok";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();} catch (SQLException ex) {}
            if (pstmt2 != null)try {pstmt2.close();} catch (SQLException ex) {}
            if (rs != null)try {rs.close();} catch (SQLException ex) {}
        }
        return returns;
    }
    
    //사용자 로그인
    public String loginDB(String id, String pw) {
    	String returns = "";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
        	Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select us_id, us_pw from user where us_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) { //아이디가 있는 경우.
				if (rs.getString("us_pw").equals(pw)) { //비밀번호가 일치하는 경우.
					returns = "true";// 로그인 가능
					System.out.println("성공");
				} else { //아이디는 있지만 비밀번호가 틀린 경우.
					returns = "false"; // 로그인 실패
				}
			} else { //아이디가 없는 경우.
				returns = "noId"; // 아이디 또는 비밀번호 존재 X
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace(); }
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace(); }
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace(); }
        }
        return returns;
    }
    
    //관리자 로그인
    public String loginEmployeeDB(String id, String pw) {
    	String returns = "";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select ep_id, ep_pw from employee where ep_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) { //아이디가 있는 경우.
				if (rs.getString("ep_pw").equals(pw)) { //비밀번호가 일치하는 경우.
					returns = "true";// 로그인 가능
					System.out.println("성공");
				} else { //아이디는 있지만 비밀번호가 틀린 경우.
					returns = "false"; // 로그인 실패
				}
			} else { //아이디가 없는 경우.
				returns = "noId"; // 아이디 또는 비밀번호 존재 X
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace(); }
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace(); }
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace(); }
        }
        return returns;
    }
    
    //직원 추가.
    public String regEmployeeDB(String id, String pw, String name, String pos) {
    	String returns = "";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select ep_id from employee where ep_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("ep_id").equals(id)) { // �̹� ���̵� �ִ� ���
                    returns = "hasId";
                }
            } else { // �Է��� ���̵� ���� ���
                sql2 = "insert into employee (ep_id, ep_pw, ep_name, ep_pos) values(?,?,?,?)";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, id);
                pstmt2.setString(2, pw);
                pstmt2.setString(3, name);
                pstmt2.setString(4, pos);
                pstmt2.executeUpdate();
                returns = "ok";
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (pstmt2 != null)try {pstmt2.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
		}
		return returns;
	}
    
    //직원 정보 가져오기.
    public JSONObject checkEmployeeDB(String id) {
    	JSONObject jsonObj = new JSONObject();
    	JSONArray employeeArr=new JSONArray();
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select ep_num, ep_name, ep_pos from employee where ep_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	JSONObject employeeObj = new JSONObject();
            	employeeObj.put("num", rs.getString("ep_num"));
            	employeeObj.put("name", rs.getString("ep_name"));
            	employeeObj.put("pos", rs.getString("ep_pos"));
            	employeeArr.add(employeeObj);
            	System.out.println(employeeObj);
            }
            jsonObj.put("employee", employeeArr);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
        }
        return jsonObj;
    }
   
    
    //사용자 정보 가져오기.
    public JSONObject checkUserDB(String id) {
    	JSONObject jsonObj = new JSONObject();
    	JSONArray userArr=new JSONArray();
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select us_num, us_name, e_money, e_stamp from user where us_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	JSONObject userObj = new JSONObject();
            	userObj.put("num", rs.getString("us_num"));
            	userObj.put("name", rs.getString("us_name"));
            	userObj.put("money", rs.getString("e_money"));
            	userObj.put("stamp", rs.getString("e_stamp"));
            	userArr.add(userObj);
            	System.out.println(userObj);
            }
            jsonObj.put("user", userArr);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
        }
        return jsonObj;
    }
    
    //비밀번호를 바꿈.
    public String changePwDB(String id, String pw) {
    	String returns = "";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "update user set us_pw = ? where us_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pw);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            
        } catch (Exception e) {

        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();} catch (SQLException ex) {}
        }
        return returns;
    }
    
    //직원 비밀번호를 바꿈.
    public String empChangePwDB(String id, String pw) {
    	String returns = "";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "update employee set ep_pw = ? where ep_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pw);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            
        } catch (Exception e) {

        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
            if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
        }
        return returns;
    }
    
    //컴플레인 작성.
    public String complainDB(String userNum, String cTitle, String cType, String cDate,
    		String cContent, String cWriteDate) {
    	String returns = "not ok";
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "insert into complain (us_num, cp_title, cp_type, cp_content, "
					+ "cp_date, cp_wdate) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userNum);
			pstmt.setString(2, cTitle);
			pstmt.setString(3, cType);
			pstmt.setString(4, cContent);
			pstmt.setString(5, cDate);
			pstmt.setString(6, cWriteDate);
			pstmt.executeUpdate();
			returns = "ok";
			System.out.println("컴플레인 넣기 성공!");
			if (rs.next()) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}	
		}
		return returns;
	}
    
    
    
    //출근 체크.
    /*public String attendanceCheckDB(String num, String status) {
    	String returns = "not ok";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "insert into attendance (ep_num, at_status) values (?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, num);
            pstmt.setString(2, status);
            pstmt.executeUpdate();
        
            returns = "ok";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
		}
		return returns;
	}*/
    
    //출근 체크
    public String attendanceCheckDB(String num) {
    	String returns = "not ok";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "insert into attendance (ep_num) values (?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, num);
            pstmt.executeUpdate();
        
            returns = "ok";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
		}
		return returns;
	}
    
    //메뉴 등록
    public String registerMenuDB(String name, String price, String type) {
    	String returns = "";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "insert into menu (mn_name, mn_price, mn_type) values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, price);
            pstmt.setString(3, type);
            pstmt.executeUpdate();
            returns = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();} catch (SQLException ex) {}
            if (rs != null)try {rs.close();} catch (SQLException ex) {}
        }
        return returns;
    }
    
    //주문 추가.
    public String insertOrderDB(String turn, String usNum, String menuNum, 
    		String quantity, String price, String payment,
    		String temperature, String cup, String size) {
    	int orderTurn = Integer.parseInt(turn);
    	int quan = Integer.parseInt(quantity);
    	int totalPrice = Integer.parseInt(price);
    	String returns = "";
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "insert into order_ (od_turn, us_num, mn_num, od_amnt, od_price, od_pay, od_tem, od_cup, od_size) "
					+ "values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderTurn);
			pstmt.setString(2, usNum);
			pstmt.setString(3, menuNum);
			pstmt.setInt(4, quan);
			pstmt.setInt(5, totalPrice);
			pstmt.setString(6, payment);
			pstmt.setString(7, temperature);
			pstmt.setString(8, cup);
			pstmt.setString(9, size);
			pstmt.executeUpdate();
			returns = "ok";
			System.out.println("주문 넣기 성공!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}	
		}
		return returns;
	}
    
    
    //complain json
    public JSONObject complainJsonDB(String userNum) {
    	JSONObject jsonObj = new JSONObject();
    	JSONArray complainArr=new JSONArray();
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select * from complain where us_num=? order by cp_id desc";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userNum);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	JSONObject complainObj = new JSONObject();
            	complainObj.put("userNum", rs.getString("us_num"));
            	complainObj.put("title", rs.getString("cp_title"));
            	complainObj.put("type", rs.getString("cp_type"));
            	complainObj.put("date", rs.getString("cp_date"));
            	complainObj.put("writeDate",rs.getString("cp_wdate"));
            	complainObj.put("content", rs.getString("cp_content"));
            	complainObj.put("reply", rs.getString("cp_reply"));
            	complainArr.add(complainObj);
            	System.out.println(complainObj);
            }
            jsonObj.put("complain", complainArr);
            //System.out.println(jsonObj);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
        }
        return jsonObj;
    }
    
    //select complain
    public JSONObject selectCsDB() {
    	JSONObject jsonObj = new JSONObject();
    	JSONArray complainArr=new JSONArray();
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select cp_id, us_id, cp_title, cp_type, cp_date, "
            		+ "cp_wdate, cp_content, cp_reply "
            		+ "from complain, user "
            		+ "where user.us_num = complain.us_num";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	JSONObject complainObj = new JSONObject();
            	complainObj.put("id", rs.getString("cp_id"));
            	complainObj.put("userID", rs.getString("us_id"));
            	complainObj.put("title", rs.getString("cp_title"));
            	complainObj.put("type", rs.getString("cp_type"));
            	complainObj.put("date", rs.getString("cp_date"));
            	complainObj.put("writeDate",rs.getString("cp_wdate"));
            	complainObj.put("content", rs.getString("cp_content"));
            	complainObj.put("reply", rs.getString("cp_reply"));
            	complainArr.add(complainObj);
            	System.out.println(complainObj);
            }
            jsonObj.put("complain", complainArr);
            //System.out.println(jsonObj);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
        }
        return jsonObj;
    }
    
  ///menuManage json
    public JSONObject menuManageJsonDB() {
        JSONObject jsonObj = new JSONObject();
        JSONArray menuArr=new JSONArray();

         try {
             Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "select * from menu";
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                JSONObject menuObj = new JSONObject();
                menuObj.put("name", rs.getString("mn_name"));
                menuObj.put("price", rs.getString("mn_price"));
                menuObj.put("type", rs.getString("mn_type"));
                menuObj.put("image", rs.getString("mn_img"));
                menuArr.add(menuObj);
                //System.out.println(menuArr);
             }
             jsonObj.put("menu", menuArr);
             //System.out.println(jsonObj);
         } catch (Exception e) {
            e.printStackTrace();
         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
             if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
         }
         return jsonObj;
     }
    
    //open Connection
    public Connection openConnection() {
	    	try {
				conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        return conn;
    }
    
    //close Connection
    public void closeConnection(Connection conn) {
    	if(conn != null){
			try {
				conn.close();
				System.out.println("DB Disconnect");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    
    //cs 답변 등록
    public String insertCSAnswerDB(String answer, String pCpID) {
    	int cpID = Integer.parseInt(pCpID);
    	String returns = "";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "update complain set cp_reply = ? where cp_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, answer);
            pstmt.setInt(2, cpID);
            pstmt.executeUpdate();
            returns = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();} catch (SQLException ex) {}
            if (rs != null)try {rs.close();} catch (SQLException ex) {}
        }
        return returns;
    }
    
    //emoney를 바꿈.
    public String updateEmoney(String eMoney, String usID) {
    	String returns = "";
    	int money = Integer.parseInt(eMoney);
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "update user set e_money = ? where us_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, usID);
            pstmt.executeUpdate();
            returns = "ok";
        } catch (Exception e) {

        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();} catch (SQLException ex) {}
        }
        return returns;
    }
    
    
    //employeeManage
    public JSONObject employeeManageDB() {
    	JSONObject jsonObj = new JSONObject();
    	JSONArray employeeArr=new JSONArray();

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            //sql = "select * from employee";
            sql = "select ep_num, ep_name, ep_id, ep_pw, "
            		+ "CASE "
            		+ "WHEN ep_pos ='o' "
            		+ "THEN '점주'"
            		+ "WHEN ep_pos ='e' "
            		+ "THEN '직원'"
            		+ "END AS pos, "
            		+ "ep_phone, ep_address, "
            		+ "DATE_FORMAT(ep_sdate, '%Y년 %m월 %d일') AS sdate, "
            		+ "DATE_FORMAT(ep_stime, '%H시 %i분') AS stime, "
            		+ "DATE_FORMAT(ep_etime, '%H시 %i분') AS etime "
            		+ "from employee";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	JSONObject employeeObj = new JSONObject();
            	employeeObj.put("num", rs.getInt("ep_num"));
            	employeeObj.put("name", rs.getString("ep_name"));
            	employeeObj.put("id", rs.getString("ep_id"));
            	employeeObj.put("pw", rs.getString("ep_pw")); 
            	employeeObj.put("pos", rs.getString("pos"));
            	
            	if (rs.getString("ep_phone").equals("")) {
            		employeeObj.put("phone", "");
            	} else {
            		employeeObj.put("phone", rs.getString("ep_phone"));
            	}
            	
            	if (rs.getString("ep_address").equals("")) {
            		employeeObj.put("address", "");
            	} else {
            		employeeObj.put("address", rs.getString("ep_address"));
            	}
            	
            	if (rs.getString("sdate").equals("")) {
            		employeeObj.put("date", "");
            	} else {
            		employeeObj.put("date", rs.getString("sdate"));
            	}
            	
            	
            	if (rs.getString("stime").equals("00:00:00")) {
            		employeeObj.put("part1", "");
            	} else {
            		employeeObj.put("part1", rs.getString("stime"));
            	}
            	
            	if (rs.getString("etime").equals("00:00:00")) {
            		employeeObj.put("part2", "");
            	} else {
            		employeeObj.put("part2", rs.getString("etime"));
            	}
            	employeeArr.add(employeeObj);
            	//System.out.println(menuArr);
            }
            jsonObj.put("employee", employeeArr);
            //System.out.println(jsonObj);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
        }
        return jsonObj;
    }
    
    //메뉴 주문 승인/거절.
    public JSONObject orderARJsonDB() {
       JSONObject jsonObj = new JSONObject();
       JSONArray orderArr = new JSONArray();

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select od_num,us_id, mn_name, od_date,od_cup,od_tem,od_size "
                  + "from order_, user, menu "
                  + "where order_.us_num = user.us_num "
                  + "and order_.mn_num = menu.mn_num "
                  + "and od_status = '요청' "
                  + "and date(od_date) = date(now())"
                  + "order by od_num";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
               JSONObject orderObj = new JSONObject();
               orderObj.put("num", rs.getString("od_num"));
               orderObj.put("userId", rs.getString("us_id"));
               orderObj.put("menuName", rs.getString("mn_name"));
               orderObj.put("date", rs.getString("od_date"));
               orderObj.put("cup", rs.getString("od_cup"));
               orderObj.put("tem", rs.getString("od_tem"));
               orderObj.put("size", rs.getString("od_size"));
               orderArr.add(orderObj);
               //System.out.println(menuArr);
            }
            jsonObj.put("order", orderArr);
            //System.out.println(jsonObj);
        } catch (Exception e) {
           e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
        }
        return jsonObj;
    }
    
    //select salesManage
    /*public JSONObject salesManageDB() {
        JSONObject jsonObj = new JSONObject();
        JSONArray salesArr = new JSONArray();

         try {
             Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "select * from order_";
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                JSONObject salesObj = new JSONObject();
                salesObj.put("name", rs.getString("mn_name"));
                salesObj.put("amnt", rs.getString("od_amnt"));
                salesArr.add(salesObj);
                //System.out.println(menuArr);
             }
             jsonObj.put("sales", salesArr);
             //System.out.println(jsonObj);
         } catch (Exception e) {
            e.printStackTrace();
         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
             if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
         }
         return jsonObj;
     }*/
    
    //날짜별로 매출관리.
    public JSONObject salesManageDB(String date) {
    	JSONObject jsonObj = new JSONObject();
    	JSONArray salesArr = new JSONArray();
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "SELECT mn_name, date( od_date ) AS orderDate, "
             		+ "sum( od_amnt ) AS orderAmnt, sum( od_price ) AS totalPrice "
            		+ "FROM order_, menu "
             		+ "WHERE menu.mn_num = order_.mn_num "
            		+ "AND od_status = '완료' or od_status = '승인' "
             		+ "AND date( od_date ) = ? "
            		+ "GROUP BY orderDate, mn_name "
             		+ "ORDER BY orderAmnt DESC ";

             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, date);
             rs = pstmt.executeQuery();
             int rank = 0;
             while (rs.next()) {
             	JSONObject salesObj = new JSONObject();
             	rank += 1;
             	String strRank = Integer.toString(rank);
             	salesObj.put("rank", strRank);
             	salesObj.put("name", rs.getString("mn_name"));
             	salesObj.put("date", rs.getString("orderDate"));
             	salesObj.put("amount", rs.getString("orderAmnt"));
             	salesObj.put("price", rs.getString("totalPrice"));
             	salesArr.add(salesObj);
             	//System.out.println(menuArr);
             }
             jsonObj.put("sales", salesArr);
      

         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return jsonObj;
     }
     
   

    //직원 정보를 갱신. 
    public String updateEmployeeInfoDB(String phone, String address, 
    		String date, String part1, String part2, String num) {
    	int number = Integer.parseInt(num);
    	String returns = "not ok";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "update employee set ep_phone = ?, ep_address = ?, "
            		+ "ep_sdate = ?, ep_stime = ?, ep_etime = ? "
            		+ "where ep_num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setString(2, address);
            pstmt.setString(3, date);
            pstmt.setString(4, part1);
            pstmt.setString(5, part2);
            pstmt.setInt(6, number);
            pstmt.executeUpdate();
            returns = "ok";
        } catch (Exception e) {

        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();} catch (SQLException ex) {}
        }
        return returns;
    }
    
    //주문관리 스탬프
    public String orderListDB(String num) {
         int userNum = Integer.parseInt(num);
         String returns = null;
	     try {
	         Class.forName(className);
	         conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	         sql = "select sum(od_amnt) as amnt_sum "
	         		+ "from order_ "
	         		+ "where od_status = '승인' "
	         		+ "group by us_num = ?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, userNum);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	 returns = Integer.toString(rs.getInt("amnt_sum"));
	         }
	         
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
       }
       return returns;
    }
    
    //주문번호를 받아서 '승인' 상태로 바꿈. 
    public String okOrderDB(String num) {
    	int number = Integer.parseInt(num);
        String returns = "not ok";

         try {
             Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "update order_ set od_status = '승인' where od_num = ?";
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, number);
             pstmt.executeUpdate();
             returns = "ok";
             
         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return returns;
     }
    
  //주문번호를 받아서 '거절' 상태로 바꿈. 
    public String refuseOrderDB(String num) {
    	int number = Integer.parseInt(num);
        String returns = "not ok";

         try {
             Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "update order_ set od_status = '거부' where od_num = ?";
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, number);
             pstmt.executeUpdate();
             returns = "ok";
             
         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return returns;
     }
    
    //거부 목록.
    public JSONObject refuseManageDB(String name, String reason) {
    	 JSONObject jsonObj = new JSONObject();
    	 JSONArray refuseArr = new JSONArray();
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "select od_num, mn_name, od_status, od_reason, od_date "
             		+ "from order_, menu "
             		+ "where order_.mn_num = menu.mn_num and "
             		+ "od_status = '거부' "
             		+ "and date(od_date) = date(now()) "
             		+ "order by od_date DESC";
             
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
             
             while (rs.next()) {
             	JSONObject refuseObj = new JSONObject();
             	refuseObj.put("num", rs.getString("od_num"));
             	refuseObj.put("menuName", rs.getString("mn_name"));
             	refuseObj.put("status", rs.getString("od_status"));
             	refuseObj.put("reason", rs.getString("od_reason"));
             	refuseObj.put("date", rs.getString("od_date"));
             	refuseArr.add(refuseObj);
             	//System.out.println(menuArr);
             }
             jsonObj.put("refuse", refuseArr);


         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return jsonObj;
     }
    
    
    //전체 승인
    public String allOkOrderDB() {
        String returns = "not ok";

         try {
             Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql= "update order_ set od_status = '승인' where od_status = '요청'";
             pstmt = conn.prepareStatement(sql);
             pstmt.executeUpdate();
             returns = "ok";
             
         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return returns;
     }
    
    //전체 거절
    public String allRefuseOrderDB(String reasonR) {
        String returns = "not ok";

         try {
             Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "update order_, user set od_status = '거부', od_reason = ?, "
             		+ "e_money = e_money + od_price "
             		+ "where od_status = '요청' "
             		+ "and order_.us_num = user.us_num";
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1,reasonR);
             pstmt.executeUpdate();
             returns = "ok";
             
         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return returns;
     }
    
    //접수 요망 카운트 
    public String orderARCntDB() {
         String returns = null;
	     try {
	         Class.forName(className);
	         conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	         sql = "select count(*) as cnt "
	         		+ "from order_ "
	         		+ "where od_status = '요청' "
	         		+ "and date(od_date) = date(now()) ";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	 returns = rs.getString("cnt");
	         }
	         
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
       }
       return returns;
    }
    
    
    //거절 카운트 
    public String orderRLCntDB() {
         String returns = null;
	     try {
	         Class.forName(className);
	         conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	         sql = "select count(*) as refuse "
	         		+ "from order_ "
	         		+ "where od_status = '거부' "
	         		+ "and date(od_date) = date(now()) ";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	 returns = rs.getString("refuse");
	         }
	         
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
       }
       return returns;
    }
    
    //총 주문 카운트
    public String orderAllDB() {
         String returns = null;
	     try {
	         Class.forName(className);
	         conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	         sql = "select count(*) as all "
	         		+ "from order_ "
	         		+ "where date(od_date) = date(now()) ";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	 returns = Integer.toString(rs.getInt("all"));
	         }
	         
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
       }
       return returns;
    }
    
    //근태 읽어오기.
    /*public JSONObject attendanceInfoDB(String num) {
    	JSONObject jsonObj = new JSONObject();
    	JSONArray attendArr = new JSONArray();
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "select min(at_num), max(at_num) at_date "
             		+ "from attendance "
             		+ "where ep_num = ?";
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, num);
             rs = pstmt.executeQuery();
             int rank = 0;
             while (rs.next()) {
             	JSONObject attendObj = new JSONObject();
             	attendObj.put("date", rs.getString("at_date"));
             	attendArr.add(attendObj);
             	//System.out.println(menuArr);
             }
             jsonObj.put("attendance", attendArr);
      

         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return jsonObj;
     }*/
    
    //근태 읽어오기.
    public JSONObject attendanceInfoDB(String num) {
    	int number = Integer.parseInt(num);
    	JSONObject jsonObj = new JSONObject();
    	JSONArray attendArr = new JSONArray();
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             /*sql = "select date(at_date) as date "
             		+ "from attendance "
             		+ "where ep_num = ? "
             		+ "group by date(at_date)";*/
             sql = "select date_format(at_date, '%Y-%m-%d %h:%i:%s') as date, "
             		+ "case "
             		+ "when at_status = 's' "
             		+ "then '출근' "
             		+ "when at_status = 'e' "
             		+ "then '퇴근' "
             		+ "end as status "
              		+ "from attendance "
              		+ "where ep_num = ? ";
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, number);
             rs = pstmt.executeQuery();
             int rank = 0;
             while (rs.next()) {
             	JSONObject attendObj = new JSONObject();
             	attendObj.put("date", rs.getString("date"));
             	attendObj.put("status", rs.getString("status"));
             	attendArr.add(attendObj);
             	//System.out.println(menuArr);
             }
             jsonObj.put("attendance", attendArr);
      

         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return jsonObj;
     }
    
    //주문내역
    public JSONObject userOrderListDB(String usNum, String sDate, String eDate) {
        int userNum = Integer.parseInt(usNum);
        JSONObject jsonObj = new JSONObject();
        JSONArray orderArr = new JSONArray();
         try {
             Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "select od_num, menu.mn_num, mn_name, mn_price, mn_img, mn_type, "
             		+ "date_format(od_date, '%Y-%m-%d %h:%i:%s') as date, "
             		+ "od_amnt, od_price, od_cup, od_size "
                  + "from order_, menu "
                  + "where us_num = ? and "
                  + "od_status = '승인' and "
                  + "date(od_date) >= ?  and " //큰 날짜
                  + "date(od_date) <= ? and "  //작은 날짜.
                  + "order_.mn_num = menu.mn_num "
                  + "order by od_num asc";
             
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, userNum);
             pstmt.setString(2, sDate);
             pstmt.setString(3, eDate);
             rs = pstmt.executeQuery();
             
             while (rs.next()) {
                 JSONObject orderObj = new JSONObject();
                 orderObj.put("orderNum", rs.getString("od_num"));
                 orderObj.put("menuNum", rs.getString("mn_num"));
                 orderObj.put("menuName", rs.getString("mn_name"));
                 orderObj.put("price", rs.getString("mn_price"));
                 orderObj.put("img", rs.getString("mn_img"));
                 orderObj.put("type", rs.getString("mn_type"));
                 orderObj.put("date", rs.getString("date"));
                 orderObj.put("amount", rs.getString("od_amnt"));
                 orderObj.put("size", rs.getString("od_size"));
                 orderObj.put("cup", rs.getString("od_cup"));
                 orderObj.put("odprice", rs.getString("od_price"));
                 orderArr.add(orderObj);
                 //System.out.println(menuArr);
              }
              jsonObj.put("order", orderArr);
              

       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
          if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
       }
       return jsonObj;
    }
    
    
    //특정 직원 상세 정보 읽어오기
  //employeeManage
    public JSONObject employeeManageDB(String epNum) {
    	int empNumber = Integer.parseInt(epNum);
    	JSONObject jsonObj = new JSONObject();
    	JSONArray employeeArr=new JSONArray();

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            //sql = "select * from employee";
            sql = "select "
            		+ "CASE "
            		+ "WHEN ep_pos ='o' "
            		+ "THEN '점주'"
            		+ "WHEN ep_pos ='e' "
            		+ "THEN '직원'"
            		+ "END AS pos, "
            		+ "ep_phone, ep_address, "
            		+ "DATE_FORMAT(ep_sdate, '%Y년 %m월 %d일') AS sdate, "
            		+ "DATE_FORMAT(ep_stime, '%H시 %i분') AS stime, "
            		+ "DATE_FORMAT(ep_etime, '%H시 %i분') AS etime "
            		+ "from employee "
            		+ "where ep_num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empNumber);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	JSONObject employeeObj = new JSONObject();
            	if (rs.getString("ep_phone").equals("")) {
            		employeeObj.put("phone", "");
            	} else {
            		employeeObj.put("phone", rs.getString("ep_phone"));
            	}
            	
            	if (rs.getString("ep_address").equals("")) {
            		employeeObj.put("address", "");
            	} else {
            		employeeObj.put("address", rs.getString("ep_address"));
            	}
            	
            	if (rs.getString("sdate").equals("")) {
            		employeeObj.put("date", "");
            	} else {
            		employeeObj.put("date", rs.getString("sdate"));
            	}
            	
            	
            	if (rs.getString("stime").equals("00:00:00")) {
            		employeeObj.put("part1", "");
            	} else {
            		employeeObj.put("part1", rs.getString("stime"));
            	}
            	
            	if (rs.getString("etime").equals("00:00:00")) {
            		employeeObj.put("part2", "");
            	} else {
            		employeeObj.put("part2", rs.getString("etime"));
            	}
            	employeeArr.add(employeeObj);
            	//System.out.println(menuArr);
            }
            jsonObj.put("employee", employeeArr);
            //System.out.println(jsonObj);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
        }
        return jsonObj;
    }
    
    //거절된 목록
    public String refuseOrderDB(String reasonR,String num) {
        String returns = "not ok";

         try {
             Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "update order_,user set od_status='거부',e_money=e_money+od_price,od_reason=? where od_num=? and order_.us_num=user.us_num";
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1,reasonR);
             pstmt.setString(2,num);
             pstmt.executeUpdate();
             returns = "ok";
             
         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return returns;
     }
    
    //결제시 주문번호 큰 번호 가져오기.
    public String paymentManageDB() {
        String returns = "";
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "select max(od_num) as num from order_ ";
             
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
      
             while (rs.next()) {
	        	 returns = Integer.toString(rs.getInt("num"));
	         }
	         

         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return returns;
     }
    
    //해당 번호에 대한 현황 정보 가져오기.
    public String statusManageDB(String num) {
        String returns = "";
        int number = Integer.parseInt(num);
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "select od_status from order_ WHERE od_num = ?";
             
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, number);
             rs = pstmt.executeQuery();
      
             while (rs.next()) {
	        	 returns = rs.getString("od_status");
	         }

         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return returns;
     }
    
    //재고 정보 가져오기.
    public JSONObject stockDB() {
       JSONObject jsonObj = new JSONObject();
       JSONArray stockArr = new JSONArray();
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "select in_num,in_name,in_amnt from ingredient";
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                JSONObject stockObj = new JSONObject();
                stockObj.put("num", rs.getString("in_num"));
                stockObj.put("name", rs.getString("in_name"));
                stockObj.put("amnt", rs.getString("in_amnt"));
                stockArr.add(stockObj);
             }
             jsonObj.put("stock", stockArr);
      

         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return jsonObj;
     }
    
    //해당 주문번호를 완료 상태로 만들기.
    public String completeOrderDB(String num) {
        String returns = "not ok";
        int number = Integer.parseInt(num);

         try {
             Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "update order_ set od_status = '완료' where od_num = ?";
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1,number);
             pstmt.executeUpdate();
             returns = number + " ok";
             
         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return returns;
     }
    
    //메뉴 주문 승인/거절.
    public JSONObject readOrderDB() {
       JSONObject jsonObj = new JSONObject();
       JSONArray orderArr = new JSONArray();

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            sql = "select od_num, mn_name, od_amnt,od_cup,od_size,od_tem from order_, menu where order_.mn_num = menu.mn_num and od_status = '승인' order by od_date limit 1";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
               JSONObject orderObj = new JSONObject();
               orderObj.put("num", rs.getString("od_num"));
               orderObj.put("menuName", rs.getString("mn_name"));
     orderObj.put("mn_amnt", rs.getString("od_amnt"));
     orderObj.put("cup", rs.getString("od_cup"));
               orderObj.put("size", rs.getString("od_size"));
               orderObj.put("tem", rs.getString("od_tem"));
               orderArr.add(orderObj);
               //System.out.println(menuArr);
            }
            jsonObj.put("order", orderArr);
            //System.out.println(jsonObj);
        } catch (Exception e) {
           e.printStackTrace();
        } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
            if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
        }
        return jsonObj;
    }
    
    //스탬프사용
    public String useStampDB(String num) {
        int userNum = Integer.parseInt(num);
        String returns = null;
       try {
           Class.forName(className);
           conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
           sql = "update user, order_ set e_stamp=e_stamp-12 "
                 + "where od_pay = 'E-스탬프' and user.us_num = ? ";
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, userNum);
           pstmt.executeUpdate();
          
           
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (conn != null)try {conn.close();} catch (SQLException ex) {ex.printStackTrace();}
         if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {ex.printStackTrace();}
         if (rs != null)try {rs.close();} catch (SQLException ex) {ex.printStackTrace();}
      }
      return returns;

    }
    
    //커피 랭킹 정보 가져오기.
    public JSONObject coffeeRankingDB() {
        String returns = "";
         JSONObject jsonObj = new JSONObject();
         JSONArray orderArr = new JSONArray();
         
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "SELECT menu.mn_num, mn_name, mn_img, mn_price, SUM( od_amnt ) AS order_sum, mn_type FROM order_, menu "
                   + "WHERE mn_type = 'c' "
                   + "AND menu.mn_num = order_.mn_num AND od_status = '완료' "
                   + "GROUP BY mn_name ORDER BY order_sum DESC LIMIT 3";
             
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                 JSONObject orderObj = new JSONObject();
                 orderObj.put("menuNum", rs.getString("mn_num"));
                 orderObj.put("menuName", rs.getString("mn_name"));
                 orderObj.put("menuImg", rs.getString("mn_img"));
                 orderObj.put("menuType", rs.getString("mn_type"));
                 orderObj.put("menuPrice", rs.getString("mn_price"));
                 orderArr.add(orderObj);
              }
              jsonObj.put("order", orderArr);

      
             
         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return jsonObj;
     }
    
     //논커피 랭킹 가져오기.
     public JSONObject nonCoffeeRankingDB() {
        String returns = "";
         JSONObject jsonObj = new JSONObject();
         JSONArray orderArr = new JSONArray();
         
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "SELECT menu.mn_num, mn_name, mn_img, mn_price, SUM( od_amnt ) AS order_sum, mn_type FROM order_, menu "
                   + "WHERE mn_type = 'n' "
                   + "AND menu.mn_num = order_.mn_num AND od_status = '완료' "
                   + "GROUP BY mn_name ORDER BY order_sum DESC LIMIT 3";
             
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                 JSONObject orderObj = new JSONObject();
                 orderObj.put("menuNum", rs.getString("mn_num"));
                 orderObj.put("menuName", rs.getString("mn_name"));
                 orderObj.put("menuImg", rs.getString("mn_img"));
                 orderObj.put("menuType", rs.getString("mn_type"));
                 orderObj.put("menuPrice", rs.getString("mn_price"));
                 orderArr.add(orderObj);
              }
              jsonObj.put("order", orderArr);

      
             
         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return jsonObj;
     }
     
     //베이커리 랭킹 가져오기.
     public JSONObject bakeryRankingDB() {
        String returns = "";
         JSONObject jsonObj = new JSONObject();
         JSONArray orderArr = new JSONArray();
         
         try {
            Class.forName(className);
             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
             sql = "SELECT menu.mn_num, mn_name, mn_img, mn_price, SUM( od_amnt ) AS order_sum, mn_type FROM order_, menu "
                   + "WHERE mn_type = 'b' "
                   + "AND menu.mn_num = order_.mn_num AND od_status = '완료' "
                   + "GROUP BY mn_name ORDER BY order_sum DESC LIMIT 3";
             
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                 JSONObject orderObj = new JSONObject();
                 orderObj.put("menuNum", rs.getString("mn_num"));
                 orderObj.put("menuName", rs.getString("mn_name"));
                 orderObj.put("menuImg", rs.getString("mn_img"));
                 orderObj.put("menuType", rs.getString("mn_type"));
                 orderObj.put("menuPrice", rs.getString("mn_price"));
                 orderArr.add(orderObj);
              }
              jsonObj.put("order", orderArr);

      
             
         } catch (Exception e) {

         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
             if (conn != null)try {conn.close();} catch (SQLException ex) {}
         }
         return jsonObj;
     }
     
     //승인된 주문 불러오기
     public JSONObject recognizeDB() {
        JSONObject jsonObj = new JSONObject();
        JSONArray recognizeArr = new JSONArray();
          try {
             Class.forName(className);
              conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
              sql = "select od_num, od_status, manufacture.mn_num, manufacture.in_num, od_amnt " + 
                    "from manufacture,order_, ingredient " + 
                    "where od_status='승인' " + 
                    "and order_.mn_num=manufacture.mn_num " + 
                    "and manufacture.in_num=ingredient.in_num";
              pstmt = conn.prepareStatement(sql);
              rs = pstmt.executeQuery();
              while (rs.next()) {
                 JSONObject recognizeObj = new JSONObject();
                 recognizeObj.put("num", rs.getString("od_num"));
                 recognizeArr.add(recognizeObj);
              }
              jsonObj.put("recognize", recognizeArr);
       

          } catch (Exception e) {

          } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
              if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
              if (conn != null)try {conn.close();} catch (SQLException ex) {}
          }
          return jsonObj;
      }
     
     //재고 업데이트
     public String updatestockDB(String odnum) {
    	 int orderNum = Integer.parseInt(odnum);
         String returns = "not ok";

          try {
              Class.forName(className);
              conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
              sql = "update ingredient,order_,manufacture " +
              "set in_amnt = in_amnt - (od_amnt*mf_amnt) " + 
              "where order_.od_num = ? " +
              "and ingredient.in_num = manufacture.in_num " + 
              "and order_.mn_num = manufacture.mn_num ";
              pstmt = conn.prepareStatement(sql);
              pstmt.setInt(1, orderNum);
              pstmt.executeUpdate();
              returns = " ok";
              
          } catch (Exception e) {

          } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
              if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
              if (conn != null)try {conn.close();} catch (SQLException ex) {}
          }
          return returns;
      }
    
     public String countAcceptDB() {
         String returns = "not ok";

          try {
              Class.forName(className);
              conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
              sql = "SELECT COUNT(od_num) as ac_cnt FROM order_ WHERE od_status='요청' and date(od_date) = date(now())";
              pstmt = conn.prepareStatement(sql);
              rs = pstmt.executeQuery();
              while (rs.next()) {
                  returns = rs.getString("ac_cnt");
              }
              
          } catch (Exception e) {

          } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
              if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
              if (conn != null)try {conn.close();} catch (SQLException ex) {}
          }
          return returns;
      }

	  public String countRefuseDB() {
	         String returns = "not ok";
	
	          try {
	              Class.forName(className);
	              conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	              sql = "SELECT COUNT(od_num) as re_cnt FROM order_ WHERE od_status='거부' and date(od_date) = date(now())";
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery();
	              while (rs.next()) {
	                  returns = rs.getString("re_cnt");
	              }
	              
	          } catch (Exception e) {
	
	          } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
	              if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
	              if (conn != null)try {conn.close();} catch (SQLException ex) {}
	          }
	          return returns;
	  }

      //총 주문 수 세기.
	  public String countDB() {
	         String returns = "not ok";
	
	          try {
	              Class.forName(className);
	              conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	              sql = "SELECT COUNT(od_num) as cnt FROM order_ where date(od_date) = date(now())";
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery();
	              while (rs.next()) {
	                  returns = rs.getString("cnt");
	              }
	              
	          } catch (Exception e) {
	
	          } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
	              if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
	              if (conn != null)try {conn.close();} catch (SQLException ex) {}
	          }
	          return returns;
	  }
	  
	    //해당 사용자의 메뉴 현황 정보 가져오기.
	    public JSONObject statusManage2DB(String num) {
	        String returns = "";
	        JSONObject jsonObj = new JSONObject();
	        JSONArray statusArr = new JSONArray();
	        int number = Integer.parseInt(num);
	         try {
	            Class.forName(className);
	             conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	             sql = "SELECT order_.mn_num, od_num, od_status, us_num, od_price, mn_name, od_date, od_pay, od_amnt, od_reason, od_tem, od_size, od_cup "+
	                   "FROM order_, menu "+
	                   "WHERE order_.mn_num = menu.mn_num "+
	                   "AND us_num =? "+
	                   "ORDER BY od_num DESC "+
	                   "LIMIT 1 ";
	             
	             pstmt = conn.prepareStatement(sql);
	             pstmt.setInt(1, number);
	             rs = pstmt.executeQuery();
	      
	             while (rs.next()) {
	                 JSONObject statusObj = new JSONObject();
	                 statusObj.put("menuNum", rs.getString("mn_num"));
	                 statusObj.put("orderNum", rs.getString("od_num"));
	                 statusObj.put("name", rs.getString("mn_name"));
	                 statusObj.put("price", rs.getString("od_price"));
	                 statusObj.put("status", rs.getString("od_status"));
	                 statusObj.put("date", rs.getString("od_date"));
	                 statusObj.put("pay", rs.getString("od_pay"));
	                 statusObj.put("amnt", rs.getString("od_amnt"));
	                 statusObj.put("reason", rs.getString("od_reason"));
	                 statusObj.put("tem", rs.getString("od_tem"));
	                 statusObj.put("size", rs.getString("od_size"));
	                 statusObj.put("cup", rs.getString("od_cup"));
	                 statusArr.add(statusObj);
	              }
	              jsonObj.put("status", statusArr);

	         } catch (Exception e) {

	         } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
	             if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
	             if (conn != null)try {conn.close();} catch (SQLException ex) {}
	         }
	         return jsonObj;
	     }
      
      
	  //커피 정보 가져오기.
      public JSONObject readCoffeeDB() {
          JSONObject jsonObj = new JSONObject();
          JSONArray coffeeArr = new JSONArray();
            try {
               Class.forName(className);
                conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
                sql = "select menu.mn_num, mn_name, mn_price, mn_type, mn_img, "
                   + "in_amnt, mf_amnt from manufacture, menu, ingredient "
                  + "where menu.mn_num=manufacture.mn_num "
                   + "and manufacture.in_num=ingredient.in_num "
                   + "and mn_type='c' "
                   + "order by mn_num, in_amnt desc";
   
   
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                   JSONObject coffeeObj = new JSONObject();
                   coffeeObj.put("num", rs.getString(1));
                   coffeeObj.put("name", rs.getString(2));
                   coffeeObj.put("price", rs.getString(3));
                   coffeeObj.put("type", rs.getString(4));
                   coffeeObj.put("img", rs.getString(5));
                   coffeeObj.put("amnt", rs.getString(6));
                   coffeeObj.put("mamnt", rs.getString(7));

                   coffeeArr.add(coffeeObj);
                 
                }
                jsonObj.put("coffee", coffeeArr);
         

            } catch (Exception e) {

            } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
                if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
                if (conn != null)try {conn.close();} catch (SQLException ex) {}
            }
            return jsonObj;
        }
      
      //품절을 생각하는 논커피 메뉴.
      public JSONObject readnoncoffeeDB() {
          JSONObject jsonObj = new JSONObject();
          JSONArray noncoffeeArr = new JSONArray();
            try {
               Class.forName(className);
                conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
                sql = "select menu.mn_num, mn_name, mn_price, mn_type, mn_img, "
                   + "in_amnt, mf_amnt from manufacture, menu, ingredient "
                   + "where menu.mn_num=manufacture.mn_num "
                   + "and manufacture.in_num=ingredient.in_num "
                   + "and mn_type='n' "
                   + "order by mn_num, in_amnt desc";
   
   
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                   JSONObject noncoffeeObj = new JSONObject();
                   noncoffeeObj.put("num", rs.getString(1));
                   noncoffeeObj.put("name", rs.getString(2));
                   noncoffeeObj.put("price", rs.getString(3));
                   noncoffeeObj.put("type", rs.getString(4));
                   noncoffeeObj.put("img", rs.getString(5));
                   noncoffeeObj.put("amnt", rs.getString(6));
                   noncoffeeObj.put("mamnt", rs.getString(7));

                   noncoffeeArr.add(noncoffeeObj);
                 
                }
                jsonObj.put("noncoffee", noncoffeeArr);
         

            } catch (Exception e) {

            } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
                if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
                if (conn != null)try {conn.close();} catch (SQLException ex) {}
            }
            return jsonObj;
        }
    
      //베이커리 정보 가져오기.
      public JSONObject readbakeryDB() {
          JSONObject jsonObj = new JSONObject();
          JSONArray bakeryArr = new JSONArray();
            try {
               Class.forName(className);
                conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
                sql = "select menu.mn_num, mn_name, mn_price, mn_type, mn_img, "
                   + "in_amnt,mf_amnt from manufacture, menu, ingredient "
                  + "where menu.mn_num=manufacture.mn_num "
                   + "and manufacture.in_num=ingredient.in_num "
                   + "and mn_type='b' "
                   + "order by mn_num, in_amnt desc";
   
   
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                   JSONObject bakeryObj = new JSONObject();
                   bakeryObj.put("num", rs.getString(1));
                   bakeryObj.put("name", rs.getString(2));
                   bakeryObj.put("price", rs.getString(3));
                   bakeryObj.put("type", rs.getString(4));
                   bakeryObj.put("img", rs.getString(5));
                   bakeryObj.put("amnt", rs.getString(6));
                   bakeryObj.put("mamnt", rs.getString(7));

                   bakeryArr.add(bakeryObj);
                 
                }
                jsonObj.put("bakery", bakeryArr);
         

            } catch (Exception e) {

            } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
                if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
                if (conn != null)try {conn.close();} catch (SQLException ex) {}
            }
            return jsonObj;
        }
      
      //커피 랭킹에 대한 정보 가져오기.
      public JSONObject coffeeRankingDetailDB(String num) {
          String returns = "";
          int number = Integer.parseInt(num);
	       JSONObject jsonObj = new JSONObject();
	       JSONArray orderArr = new JSONArray();
	       
	       try {
	          Class.forName(className);
	           conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	           sql = "SELECT menu.mn_num, mn_name, mn_type, mn_price, mn_img FROM menu "
	                 + "WHERE mn_num=?";
	           
	           pstmt = conn.prepareStatement(sql);
	           pstmt.setInt(1, number);
	           rs = pstmt.executeQuery();
	           while (rs.next()) {
	               JSONObject orderObj = new JSONObject();
	               orderObj.put("num", rs.getString("mn_num"));
	               orderObj.put("name", rs.getString("mn_name"));
	               orderObj.put("type", rs.getString("mn_type"));
	               orderObj.put("price", rs.getString("mn_price"));
	               orderObj.put("img", rs.getString("mn_img"));
	               orderArr.add(orderObj);
	            }
	            jsonObj.put("order", orderArr);

        
               
           } catch (Exception e) {

           } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
               if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
               if (conn != null)try {conn.close();} catch (SQLException ex) {}
           }
           return jsonObj;
       }
      
      //메뉴에 대한 타입 정보 가져오기.
      public String showTypeDB(String menuNum) {
          String returns = "not ok";
          int number = Integer.parseInt(menuNum);
           try {
               Class.forName(className);
               conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
               sql = "select mn_type from menu where mn_num=?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1,number);
               rs = pstmt.executeQuery();
               while(rs.next()) {
            	   returns = rs.getString("mn_type");
               }
               
           } catch (Exception e) {

           } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
               if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
               if (conn != null)try {conn.close();} catch (SQLException ex) {}
           }
           return returns;
       }
      
      //총주문내역
      public JSONObject totalOrderDB(String sDate, String eDate) {
          JSONObject jsonObj = new JSONObject();
          JSONArray totalArr = new JSONArray();
          
          try {
             Class.forName(className);
              conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
              sql = "select od_num, mn_name, od_date,od_amnt, od_price, od_reason, od_status, od_tem, od_size, od_cup "
                    + "from order_, menu "
                    + "where date(od_date) >= ?  "
                    + "and date(od_date) <= ? "
                    + "and menu.mn_num = order_.mn_num ";
              
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, sDate);
              pstmt.setString(2, eDate);
              rs = pstmt.executeQuery();
              while (rs.next()) {
                  JSONObject totalObj = new JSONObject();
                  totalObj.put("num", rs.getString("od_num"));
                  totalObj.put("menuName", rs.getString("mn_name"));
                  totalObj.put("date", rs.getString("od_date"));
                  totalObj.put("amount", rs.getString("od_amnt"));
                  totalObj.put("price", rs.getString("od_price"));
                  totalObj.put("status", rs.getString("od_status"));
                  totalObj.put("tem", rs.getString("od_tem"));
                  totalObj.put("size", rs.getString("od_size"));
                  totalObj.put("cup", rs.getString("od_cup"));
                  totalArr.add(totalObj);
               }
               jsonObj.put("order", totalArr);

        
               
           } catch (Exception e) {

           } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
               if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
               if (conn != null)try {conn.close();} catch (SQLException ex) {}
           }
           return jsonObj;
       }
      
      //총주문내역2
      public JSONObject totalOrdertypeDB(String sDate, String eDate, String status) {
          JSONObject jsonObj = new JSONObject();
          JSONArray totaltypeArr = new JSONArray();
          
          if (status.equals("1")) {
        	  status = "요청";
          } else if (status.equals("2")) {
        	  status = "거부";
          } else if (status.equals("3")) {
        	  status = "승인";
          } else if (status.equals("4")) {
        	  status = "완료";
          }
          
          try {
             Class.forName(className);
              conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
              sql = "select od_num, mn_name, od_date,od_amnt, od_price, od_reason, od_status, od_tem, od_size, od_cup "
                    + "from order_, menu "
                    + "where date(od_date) >= ?  "
                    + "and date(od_date) <= ? "
                    + "and od_status = ? "
                    + "and menu.mn_num = order_.mn_num ";
              
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, sDate);
              pstmt.setString(2, eDate);
              pstmt.setString(3, status);
              rs = pstmt.executeQuery();
              while (rs.next()) {
                  JSONObject totaltypeObj = new JSONObject();
                  totaltypeObj.put("num", rs.getString("od_num"));
                  totaltypeObj.put("menuName", rs.getString("mn_name"));
                  totaltypeObj.put("date", rs.getString("od_date"));
                  totaltypeObj.put("amount", rs.getString("od_amnt"));
                  totaltypeObj.put("price", rs.getString("od_price"));
                  totaltypeObj.put("status", rs.getString("od_status"));
                  totaltypeObj.put("tem", rs.getString("od_tem"));
                  totaltypeObj.put("size", rs.getString("od_size"));
                  totaltypeObj.put("cup", rs.getString("od_cup"));
                  totaltypeArr.add(totaltypeObj);
               }
               jsonObj.put("order", totaltypeArr);

        
               
           } catch (Exception e) {

           } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
               if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
               if (conn != null)try {conn.close();} catch (SQLException ex) {}
           }
           return jsonObj;
       }
      
      //요청으로 된 주문 번호 가져오기
      public JSONObject getOrderNumDB() {
	       JSONObject jsonObj = new JSONObject();
	       JSONArray totalArr = new JSONArray();
	       
	       try {
	          Class.forName(className);
	           conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	           sql = "select od_num from order_ where od_status='요청'";
	           pstmt = conn.prepareStatement(sql);
	           rs = pstmt.executeQuery();
	           while (rs.next()) {
	               JSONObject totalObj = new JSONObject();
	               totalObj.put("num", rs.getString("od_num"));
	               totalArr.add(totalObj);
	            }
	            jsonObj.put("order", totalArr);

        
               
           } catch (Exception e) {

           } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
               if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
               if (conn != null)try {conn.close();} catch (SQLException ex) {}
           }
           return jsonObj;
       }
      
      //전체 승인시 재료 차감.
      public String allRLStuckDB(String num) { 
    	   String returns = "";
    	   int number = Integer.parseInt(num);
	       try {
	          Class.forName(className);
	           conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	           sql = "update ingredient,order_,manufacture " +
	                   "set in_amnt = in_amnt - (od_amnt*mf_amnt) " + 
	                   "where order_.od_num = ? " +
	                   "and ingredient.in_num = manufacture.in_num " + 
	                   "and order_.mn_num = manufacture.mn_num ";
	           pstmt = conn.prepareStatement(sql);
	           pstmt.setInt(1, number);
	           pstmt.executeUpdate();

        
               
           } catch (Exception e) {

           } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
               if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
               if (conn != null)try {conn.close();} catch (SQLException ex) {}
           }
           return returns;
       }
      
      //총매출을 한 날짜에 대한 총 가격 계산.
      public JSONObject totalSalesManageDB(String date) {
          String returns = "";
          JSONObject jsonObj = new JSONObject();
          JSONArray totalArr = new JSONArray();
           try {
              Class.forName(className);
               conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
               sql = "SELECT date( od_date ) AS orderDate, sum( od_price ) AS totalPrice " + 
                     "FROM order_ " + 
                     "WHERE od_status = '승인' or od_status = '완료' "
                     + "and date( od_date ) = ? " + 
                     "GROUP BY orderDate";
               
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, date);
               rs = pstmt.executeQuery();
        
               while (rs.next()) {
                   JSONObject totalObj = new JSONObject();
                   totalObj.put("date", rs.getString("orderDate"));
                   totalObj.put("price", rs.getString("totalPrice"));
                   totalArr.add(totalObj);
                }
                jsonObj.put("total", totalArr);
             

           } catch (Exception e) {

           } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
               if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
               if (conn != null)try {conn.close();} catch (SQLException ex) {}
           }
           return jsonObj;
       }
      
      //메뉴 이름에 대한 메뉴 타입 가져오기.
      public String showDetailTypeDB(String name) {
          String returns = "not ok";
           try {
               Class.forName(className);
               conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
               sql = "select mn_type from menu where mn_name=?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1,name);
               rs = pstmt.executeQuery();
               while(rs.next()) {
                  returns = rs.getString("mn_type");
               }
            
               
           } catch (Exception e) {

           } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
               if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
               if (conn != null)try {conn.close();} catch (SQLException ex) {}
           }
        
           return returns;
       }
      
      //스탬프 찍기.
      public String addEstampDB(String num) {
          String returns = "not ok";
          int number = Integer.parseInt(num);
           try {
               Class.forName(className);
               conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
               sql = "update order_, user "
               		+ "set e_stamp = e_stamp+od_amnt "
               		+ "where od_num = ? and  user.us_num = order_.us_num";
               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1,number);
               pstmt.executeUpdate();

               
           } catch (Exception e) {

           } finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
               if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
               if (conn != null)try {conn.close();} catch (SQLException ex) {}
           }
           return returns;
       }
      
      //메뉴 주문 승인/거절.
      public JSONObject odHDB(String num) {
         JSONObject jsonObj = new JSONObject();
         JSONArray odhArr = new JSONArray();
         int number = Integer.parseInt(num);

          try {
              Class.forName(className);
              conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
              sql = "select * from menu where mn_num=?";
              pstmt = conn.prepareStatement(sql);
              pstmt.setInt(1,number);
              rs = pstmt.executeQuery();
              while (rs.next()) {
                 JSONObject odhObj = new JSONObject();
                 odhObj.put("img", rs.getString("mn_img"));
                 odhObj.put("price", rs.getString("mn_price"));
                 odhObj.put("type", rs.getString("mn_type"));
                 odhArr.add(odhObj);
                 //System.out.println(odhArr);
              }
              jsonObj.put("orderh",odhArr);
              //System.out.println(jsonObj);
          } catch (Exception e) {
             e.printStackTrace();
          } finally {if (rs != null)try {rs.close();} catch (SQLException ex) { ex.printStackTrace();}
              if (pstmt != null)try {pstmt.close();} catch (SQLException ex) { ex.printStackTrace();}
              if (conn != null)try {conn.close();} catch (SQLException ex) { ex.printStackTrace();}
          }
          return jsonObj;
      }
      
      
    
    
      

}
