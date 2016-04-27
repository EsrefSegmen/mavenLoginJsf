package com.es.phdcentral.controler;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import com.es.phdcentral.beans.Tbluser;
import com.es.phdcentral.database.DataBase;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "TbluserManagementControler")
@SessionScoped

public class TbluserManagementControler implements Serializable {

    private Long id_tblUser;
    private String name;
    private String surname;
    private String userName;
    private String password;
    private String email;
    private Long classId;
    private Integer isActive;
    private Integer userType;

    private String session1 = "";
    private List<Tbluser> SelecteduserList;
    private Long idtblUser;
    private String msg = "";
    private Tbluser selectTbluser = null;
    private String result = "";

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getId_tblUser() {
        return id_tblUser;
    }

    public void setId_tblUser(Long id_tblUser) {
        this.id_tblUser = id_tblUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSession1() {
        return session1;
    }

    public void setSession1(String session1) {
        this.session1 = session1;
    }

    public List<Tbluser> getSelecteduserList() {
        return SelecteduserList;
    }

    public void setSelecteduserList(List<Tbluser> SelecteduserList) {
        this.SelecteduserList = SelecteduserList;
    }

    public Long getIdtblUser() {
        return idtblUser;
    }

    public void setIdtblUser(Long idtblUser) {
        this.idtblUser = idtblUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Tbluser getSelectTbluser() {
        return selectTbluser;
    }

    public void setSelectTbluser(Tbluser selectTbluser) {
        this.selectTbluser = selectTbluser;
    }

    public String find() throws SQLException {
        int userCount = 0;
        int statIsActive = 0;
        String stm = "SELECT userName,password,isActive FROM tbluser where userName ='" + getUserName() + "'";
        DataBase db = new DataBase();
        ResultSet result = db.getResultSet(stm);
        List<Tbluser> list = new ArrayList<Tbluser>();
        addMessage("Hos Geldiniz.....!!    " + getUserName());
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
            return "TbluserManagementControler";
        }

        switch (userCount) {
            case 0:
                setMsg("NO USER FOUND");

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please Try Again!"));
                return "TbluserManagementControler";
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
                    return "TbluserManagementControler";

                } else {
                    setMsg("OK   User inActive ");

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please Try Again!"));

                    // invalidate session, and redirect to other pages
                    //message = "Invalid Login. Please Try Again!";
                    return "TbluserManagementControler";
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

    public void insertData() {

        String SqlInsert = "INSERT INTO TBLUSER (name,surname,userName,password,email,classId,isActive,usertype)   VALUES('" + selectTbluser.getName() + "','" + selectTbluser.getSurname() + "','" + selectTbluser.getUserName() + "','" + selectTbluser.getPassword() + "','" + selectTbluser.getEmail() + "'," + selectTbluser.getClassId() + "," + selectTbluser.getIsActive() + "," + selectTbluser.getUserType() + ")";

        DataBase db = new DataBase();
        boolean ret = db.InsertSql(SqlInsert);
        if (ret) {
            setResult("OK");
        } else {
            setResult("FAIL");
        }
    }

    public List<Tbluser> getUsers() {
        ResultSet result = null;
        String stm = "Select * from tbluser";
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
        selectTbluser = (Tbluser) records.get(0);
        return records;
    }

    public String refresh() {


        return "TbluserManagementControler";
    }

    public String newUser() {
//        HttpSession session = Util.getSession();
        selectTbluser = null;
        return "TbluserManagementControler";
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
        selectTbluser = (Tbluser) event.getObject();
        String a = selectTbluser.getName();

        addMessage("USER NAME " + a);
        setResult(a);
        

    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg1 = new FacesMessage("Unselected", ((Tbluser) event.getObject()).getIdtblUser().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg1);
    }
}
