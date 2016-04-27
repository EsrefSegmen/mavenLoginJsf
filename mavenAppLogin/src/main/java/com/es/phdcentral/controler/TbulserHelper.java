package com.es.phdcentral.controler;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import com.es.phdcentral.beans.Tbluser;
import com.es.phdcentral.database.DataBase;
//import com.mkyong.customer.model.Customer;
@ManagedBean(name = "TblUserHelper")
@SessionScoped

public class TbulserHelper implements Serializable {

    public String userName = "";
    public String password = "";
    public String session1 = "";
    public List<Tbluser> Selecteduser;
    private Long idtblUser;
    public String espar;

    public String getEspar() {
        return espar;
    }

    public void setEspar(String espar) {
        this.espar = espar;
    }

    public Tbluser selectTblUser;

    String msg = "";

    public Tbluser getSelectTblUser() {
        return selectTblUser;
    }

    public void setSelectTblUser(Tbluser selectTblUser) {
        this.selectTblUser = selectTblUser;
    }

    public Long getIdtblUser() {
        return idtblUser;
    }

    public void setIdtblUser(Long idtblUser) {
        this.idtblUser = idtblUser;
    }

    public List<Tbluser> getSelecteduser() {
        return Selecteduser;
    }

    public void setSelecteduser(List<Tbluser> Selecteduser) {
        this.Selecteduser = Selecteduser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void insertData() {
        
        String SqlInsert = "INSERT INTO TBULSER (name,surname,userName,password,email,classId,isActive,usertype)   VALUES('" + selectTblUser.getName() + "','" + selectTblUser.getSurname()+"','"+ selectTblUser.getUserName() + "','" + selectTblUser.getPassword() + "','" + selectTblUser.getEmail() + "'," + selectTblUser.getClassId() + "," + selectTblUser.getIsActive() +"," + selectTblUser.getUserType()+  ")";

        DataBase db = new DataBase();
        boolean ret = db.InsertSql(SqlInsert);
        if (ret) {
            setEspar("OK");
        } else {
            setEspar("FAIL");
        }
    }

    public List<Tbluser> getUsers() {
        ResultSet result = null;
        String stm = "Select * from tbulser";
        DataBase db = new DataBase();
        result = db.getResultSet(stm);

//        PreparedStatement pst = null;
//        Connection con = getConnection();
        List<Tbluser> records = new ArrayList<Tbluser>();
        try {
//            pst = con.prepareStatement(stm);
//            pst.execute();
//            result = pst.getResultSet();

            while (result.next()) {
                Tbluser cust = new Tbluser();

                cust.setIdtblUser(result.getLong("id_tblUser"));
                cust.setName(result.getString("name"));
                cust.setSurname(result.getString("surname"));
                cust.setUserName(result.getString("userName"));
                cust.setPassword(result.getString("password"));
                cust.setEmail(result.getString("email"));
                cust.setClassId(result.getInt("classId"));
                cust.setIsActive(result.getInt("isActive"));
                cust.setUserType(result.getInt("userType"));
                records.add(cust);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public String loginControl() throws SQLException {
        int userCount = 0;
        int statIsActive = 0;
        String stm = "SELECT userName,password,isActive FROM tbulser where userName ='" + getUserName() + "' and password='" + getPassword() + "'";
        DataBase db = new DataBase();
        ResultSet result = db.getResultSet(stm);
        List<Tbluser> list = new ArrayList<Tbluser>();
        addMessage("Welcome to Primefaces!!");
        try {

            while (result.next()) {
                userCount++;
                Tbluser cust = new Tbluser();
                cust.setUserName(result.getString("userName"));
                cust.setPassword(result.getString("password"));
                statIsActive = result.getInt("isActive");
                cust.setIsActive(statIsActive);

                list.add(cust);
            }
        } catch (SQLException ex) {
            //e.printStackTrace();

            setMsg("SEVERE ERROR SQL  :" + ex.toString());
            return "login";
        }

        switch (userCount) {
            case 0:
                setMsg("NO USER FOUND");

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please Try Again!"));
                return "home";
            // break;
            case 1:

                if (statIsActive == 1) {
                    setMsg("OK   User Active ");
                    /*
                    HttpSession session1 = Util.getSession();
                     */
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    session.setAttribute("username", userName);
                    session1 = session.getAttribute("username").toString();
                    return "home";

                } else {
                    setMsg("OK   User inActive ");

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please Try Again!"));

                    // invalidate session, and redirect to other pages
                    //message = "Invalid Login. Please Try Again!";
                    return "home";
                }

            // break;
            default:
                setMsg("SEVERE ERROR Call Database Admin..... ");

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please Try Again!"));
                setMsg("Hatali password ");
                return "home";
            // break;
        }

    }

    public String logout() {
//        HttpSession session = Util.getSession();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "login";
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String gettsession() {
//        HttpSession session = Util.getSession();
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session1 = session.getAttribute("username").toString();

            return session1;
        } catch (Exception ex) {
            return "No Session";
        }
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg1 = new FacesMessage("Selected", ((Tbluser) event.getObject()).getIdtblUser().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg1);
        selectTblUser = (Tbluser) event.getObject();
        String a = selectTblUser.getName();
        setEspar(a);

    }

//    public void onRowUnselect(UnselectEvent event) {
//        FacesMessage msg1 = new FacesMessage("Unselected", ((Tbulser) event.getObject()).getIdtblUser().toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg1);
//    }
}
