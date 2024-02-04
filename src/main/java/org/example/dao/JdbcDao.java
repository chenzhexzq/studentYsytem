package org.example.dao;

import org.example.pojo.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;


public class JdbcDao {
    private static Properties properties=null;
    static { //静态代码块，类加载时自动执行
        InputStream dbProps = JdbcDao.class.getClassLoader().getResourceAsStream("db.properties");
        properties = new Properties();
        try {
            properties.load(dbProps);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String url = properties.getProperty("url");
    private static String user = properties.getProperty("user");
    private static String password = properties.getProperty("password");
    private static String driverClass = properties.getProperty("driverClass");
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    //获得数据库连接
    public static Connection getConnection(){
        try {
            Class.forName(driverClass);
            conn= DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //关闭数据库连接，释放资源
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
        try {
            if(rs!=null) {
                rs.close();
            }
            if(pstmt!=null) {
                pstmt.close();
            }
            if(conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void close() {
        close(rs, pstmt, conn);
    }
    public Users getUser(Users user) {
        Users oneUser = new Users();
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select * from Users where username=?");
            pstmt.setString(1, user.getUsername());
            rs= pstmt.executeQuery();
            if(rs.next()){
                oneUser.setUsername(rs.getString(2)); //设置用户名
                oneUser.setUserpwd(rs.getString(3)); //设置密码
                oneUser.setRole(rs.getString(4)); //设置权限角色
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oneUser;
    }

    public boolean setUser(Users users){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("insert into users(username, userpwd,role) VALUE (?,?,?)");
            pstmt.setString(1, users.getUsername());
            pstmt.setString(2, users.getUserpwd());
            pstmt.setString(3,users.getRole());
            if(pstmt.executeUpdate()!=1){
                b = false;
               }
            } catch (SQLException e) {
                  b = false;
                  e.printStackTrace();
           }
        return b;
    }
    /**
     * 根据 sql 语句返回特定的学生集合
     * @param sql sql 语句
     * @return 返回 Vector<Student>对象
     */
    public Vector<Student> getStudent(String sql){
        Vector<Student> students = new Vector<Student>();
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setSno(rs.getString(1));
                student.setSname(rs.getString(2));
                student.setSsex(rs.getString(3));
                student.setSage(rs.getString(4));
                student.setSdept(rs.getString(5));
                student.setClas(rs.getString(6));
                students.add(student);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return students;
    }
    /**
     * 添加学生
     * @param student 学生对象
     * @return 返回是否添加成功
     */
    public boolean addStudent(Student student){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("insert into student(Sno,Sname,Ssex,Sage,Sdept,clas) values(?,?,?,?,?,?)");
            pstmt.setString(1, student.getSno());
            pstmt.setString(2, student.getSname());
            pstmt.setString(3, student.getSsex());
            pstmt.setString(4, student.getSage());
            pstmt.setString(5, student.getSdept());
            pstmt.setString(6,student.getClas());
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }
    public boolean updateStudent(Student student){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("update student set Sname=?,Ssex=?,Sage=?,Sdept=?,clas=? where Sno = ?");
            pstmt.setString(1, student.getSname());
            pstmt.setString(2, student.getSsex());
            pstmt.setString(3, student.getSage());
            pstmt.setString(4, student.getSdept());
            pstmt.setString(5,student.getClas());
            pstmt.setString(6, student.getSno());
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }
    public boolean deleteStudent(String Son){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("delete from student where Sno=?");
            pstmt.setString(1, Son);
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public Vector<Course> getCourse(String sql){
        Vector<Course> courses = new Vector<Course>();
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Course course=new Course();
                course.setCno(rs.getString(1));
                course.setCname(rs.getString(2));
                course.setTeacher(rs.getString(3));
                course.setCredit(rs.getString(4));
                courses.add(course);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return courses;
    }
    public Course selectByCno(String cno) {
        Course course = new Course();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select * from course where cno = ?");
            pstmt.setString(1, cno);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                course.setCno(rs.getString(1));
                course.setCname(rs.getString(2));
                course.setTeacher(rs.getString(3));
                course.setCredit(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
    public Student selectSno(String sno) {
        Student student = new Student();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select * from student where Sno = ?");
            pstmt.setString(1, sno);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                student.setSno(rs.getString(1));
                student.setSname(rs.getString(2));
                student.setSsex(rs.getString(3));
                student.setSage(rs.getString(4));
                student.setSdept(rs.getString(5));
                student.setClas(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
    public SelectAvgScore selectAvgScore(String cname) {
        SelectAvgScore selectAvgScore = new SelectAvgScore();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select cname,course.cno,avg(score) from sc,course where sc.cno = course.cno and cname = ?;");
            pstmt.setString(1, cname);
            rs = pstmt.executeQuery();
            while(rs.next()) {
               selectAvgScore.setCname(rs.getString(1));
               selectAvgScore.setCno(rs.getString(2));
               selectAvgScore.setScore(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectAvgScore;
    }
    public selectAvgscoreBySno selectAvgscoreBySno(String sno){
        selectAvgscoreBySno selectAvgscoreBySno = new selectAvgscoreBySno();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select Student.sno,sname,avg(score) from sc,student where sc.sno=student.Sno and Student.sno = ? group by Student.sno, sname;");
            pstmt.setString(1, sno);
            rs = pstmt.executeQuery();
            while(rs.next()) {
               selectAvgscoreBySno.setSno(rs.getString(1));
               selectAvgscoreBySno.setSname(rs.getString(2));
               selectAvgscoreBySno.setAvgScore(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectAvgscoreBySno;
    }
    public Vector<SelectByNoGood> selectByNoGoods(String cname){
        Vector<SelectByNoGood> sbngs = new Vector<SelectByNoGood>();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select course.cname,course.cno,Student.Sno,Sname,score from course,student,sc where course.cno=sc.cno and Student.Sno=sc.sno and score<60 and cname= ?;");
            pstmt.setString(1,cname);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                SelectByNoGood selectByNoGood = new SelectByNoGood();
                selectByNoGood.setSno(rs.getString("course.cname"));
                selectByNoGood.setSname(rs.getString("course.cno"));
                selectByNoGood.setCno(rs.getString("Student.Sno"));
                selectByNoGood.setCname(rs.getString("Student.Sname"));
                selectByNoGood.setScore(rs.getString("Sc.score"));
                sbngs.add(selectByNoGood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sbngs;
    }

    public Vector<SelectByCredit> selectByCredits(String Sno){
        Vector<SelectByCredit> sbcs = new Vector<SelectByCredit>();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select Student.Sno ,Sname ,course.cno,cname,credit from Student,course,sc  where course.cno=sc.cno and Student.Sno=sc.sno and score>=60 and student.Sno =?;");
            pstmt.setString(1,Sno);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                SelectByCredit selectByCredit = new SelectByCredit();
                selectByCredit.setSno(rs.getString("Student.Sno"));
                selectByCredit.setSname(rs.getString("Student.Sname"));
                selectByCredit.setCno(rs.getString("course.cno"));
                selectByCredit.setCname(rs.getString("course.cname"));
                selectByCredit.setCredit(rs.getString("course.credit"));
                sbcs.add(selectByCredit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sbcs;
    }

    public Vector<SelectScoreBySno> selectScoreBySnos(String Sno){
        Vector<SelectScoreBySno> ssbs = new Vector<SelectScoreBySno>();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select Student.Sno, Sname, cname,score from course,student,sc where course.cno=sc.cno and Student.Sno=sc.sno and Student.Sno= ?;");
            pstmt.setString(1,Sno);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                SelectScoreBySno selectScoreBySno = new SelectScoreBySno();
                selectScoreBySno.setSno(rs.getString("Student.Sno"));
                selectScoreBySno.setSname(rs.getString("Student.Sname"));
                selectScoreBySno.setCname(rs.getString("Course.cname"));
                selectScoreBySno.setScore(rs.getString("Sc.score"));
                ssbs.add(selectScoreBySno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ssbs;
    }

    public Vector<Student> selectStuByClas(String Clas){
        Vector<Student> students = new Vector<Student>();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select * from student where clas= ?;");
            pstmt.setString(1,Clas);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Student student = new Student();
                student.setSno(rs.getString(1));
                student.setSname(rs.getString(2));
                student.setSsex(rs.getString(3));
                student.setSage(rs.getString(4));
                student.setSdept(rs.getString(5));
                student.setClas(rs.getString(6));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Vector<Student> selectStudentBySno(String QSno,String ZSno){
        Vector<Student> students = new Vector<Student>();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select Sno,Sname,Ssex,Sage,Sdept,clas from student where Sno between ? and ?;");
            pstmt.setString(1,QSno);
            pstmt.setString(2,ZSno);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Student student = new Student();
                student.setSno(rs.getString(1));
                student.setSname(rs.getString(2));
                student.setSsex(rs.getString(3));
                student.setSage(rs.getString(4));
                student.setSdept(rs.getString(5));
                student.setClas(rs.getString(6));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    public Vector<SelectByScale> selectByScales(String clas,String cname){
        Vector<SelectByScale> selectByScales = new Vector<SelectByScale>();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select count(*) as '人数',concat(substr((100*count(*)/(select count(*) from student where clas = ?)),1,instr((100*count(*)/(select count(*) from student where clas =?)),'.')+2),'%')as '占比' from student,sc,course where Student.Sno = sc.sno and course.cno = sc.cno  and score<60 and clas = ? and cname=?;");
            pstmt.setString(1,clas);
            pstmt.setString(2,clas);
            pstmt.setString(3,clas);
            pstmt.setString(4,cname);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                SelectByScale selectByScale = new SelectByScale();
                selectByScale.setPeople(rs.getString(1));
                selectByScale.setScale(rs.getString(2));
                selectByScales.add(selectByScale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectByScales;
    }

    public Vector<SelectByRoundScale> selectByRoundScales(String clas,String cname){
        Vector<SelectByRoundScale> selectByRoundScales = new Vector<SelectByRoundScale>();
        try {
            if (conn == null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("select count(*) as '人数',concat(substr((100*count(*)/(select count(*) from student where clas = ?)),1,instr((100*count(*)/(select count(*) from student where clas = ?)),'.')+2),'%')as '占比',\n" +
                    "       (case when score <60 then '0~60分段'\n" +
                    "           when score between 60 and 74 then '60~74分段'\n" +
                    "           when score between 75 and 89 then '75~89分段'\n" +
                    "           when score between 90 and 100 then '90~100分段' end ) as scoreRound\n" +
                    "from student,course,sc where Student.Sno = sc.sno and course.cno = sc.cno and clas = ? and cname= ? group by scoreRound order by scoreRound asc;");
            pstmt.setString(1,clas);
            pstmt.setString(2,clas);
            pstmt.setString(3,clas);
            pstmt.setString(4,cname);
            rs = pstmt.executeQuery();
            while(rs.next()) {
               SelectByRoundScale selectByRoundScale = new SelectByRoundScale();
               selectByRoundScale.setPeople(rs.getString(1));
               selectByRoundScale.setScale(rs.getString(2));
               selectByRoundScale.setScoreRound(rs.getString(3));
               selectByRoundScales.add(selectByRoundScale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectByRoundScales;
    }

    public boolean addCourse(Course course){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("insert into course(cno, cname, teacher, credit) values(?,?,?,?)");
            pstmt.setString(1, course.getCno());
            pstmt.setString(2, course.getCname());
            pstmt.setString(3, course.getTeacher());
            pstmt.setString(4, course.getCredit());
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public boolean updateCourse(Course course){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("update course set cname=?,teacher=?,credit=? where cno = ?");
            pstmt.setString(1, course.getCname());
            pstmt.setString(2, course.getTeacher());
            pstmt.setString(3, course.getCredit());
            pstmt.setString(4, course.getCno());
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public boolean deleteCourse(String cno){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("delete from course where cno=?");
            pstmt.setString(1, cno);
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public Vector<Sc> getSc(String sql){
        Vector<Sc> scs = new Vector<Sc>();
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Sc sc=new Sc();
                sc.setSno(rs.getString(1));
                sc.setCno(rs.getString(2));
                sc.setScore(rs.getString(3));
                scs.add(sc);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return scs;
    }

    public boolean addSc(Sc sc){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("insert into sc(sno, cno, score) values(?,?,?)");
            pstmt.setString(1, sc.getSno());
            pstmt.setString(2, sc.getCno());
            pstmt.setString(3, sc.getScore());
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public boolean updateSc(Sc sc){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("update sc set score=? where sno = ? and cno=?");
            pstmt.setString(1, sc.getScore());
            pstmt.setString(2, sc.getSno());
            pstmt.setString(3, sc.getCno());
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public boolean deleteSc(String sno,String cno){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("delete from sc where sno=? and  cno=?");
            pstmt.setString(1, sno);
            pstmt.setString(2,cno);
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public boolean updatePassword(Users users){
        boolean b = true;
        try {
            if(conn==null) {
                JdbcDao.getConnection();
            }
            pstmt = conn.prepareStatement("update users set userpwd = ? where username = ?");
            pstmt.setString(1, users.getUserpwd());
            pstmt.setString(2, users.getUsername());
            if(pstmt.executeUpdate()!=1){
                b = false;
            }
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }
}
