package org.example.dao;

import org.example.pojo.*;

import java.util.Vector;

//数据库业务处理类
public class JdbcDaoService {
    //private JdbcDao jdbcDao;

    /*public void JdbcDaoService() {
       this.jdbcDao = new JdbcDao(); //创建与数据库访问对象
    }*/
    JdbcDao jdbcDao=new JdbcDao();
    /**
     * 登陆处理
     *
     * @param user 根据登录输入信息封装的用户对象
     * @return 返回 0 表示成功，返回-1 表示用户名不存在，返回-2 表示密码错误
     */
    public int loginService(Users user) {
        Users newUser = jdbcDao.getUser(user);//根据用户名提取数据库中的用户信息对象
        if (newUser == null || newUser.getUsername() == null || newUser.getUsername().trim().equals("")) {
            return -1;
        } else {
            if (newUser.getUserpwd().equals(user.getUserpwd())) {
                return 0;
            } else {
                return -2;
            }
        }
    }
    public boolean setUser(Users users){
        return jdbcDao.setUser(users);
    }
    /**
     * 根据 sql 语句返回特定的学生对象集合
     * @param sql sql 语句
     * @return 返回学生集合
     */
    public Vector<Student> getStudent(String sql) {
        return jdbcDao.getStudent(sql);
    }
    public Users getUser(Users users){return jdbcDao.getUser(users);}
    /**
     * 添加学生业务
     * @param student 学生对象
     * @return 返回是否添加成功
     */
    public boolean addStudent(Student student){
        return jdbcDao.addStudent(student);
    }
    public boolean updateStudent(Student student){
        return jdbcDao.updateStudent(student);
    }
    public boolean deleteStudent(String Son){
        return jdbcDao.deleteStudent(Son);
    }

    public Vector<Course> getCourse(String sql) {
        return jdbcDao.getCourse(sql);
    }
    public Course selectByCno(String cno){
        return jdbcDao.selectByCno(cno);
    }
    public Student selectSno(String sno){
        return jdbcDao.selectSno(sno);
    }
    public SelectAvgScore selectAvgScore(String cname){
        return jdbcDao.selectAvgScore(cname);
    }
    public selectAvgscoreBySno selectAvgscoreBySno(String sno){
        return jdbcDao.selectAvgscoreBySno(sno);
    }
    public Vector<SelectByNoGood> selectByNoGoods(String cname){
        return jdbcDao.selectByNoGoods(cname);
    }
    public Vector<SelectByCredit> selectByCredits(String Sno){
        return jdbcDao.selectByCredits(Sno);
    }
    public Vector<Student> selectStuByClas(String Clas){
        return jdbcDao.selectStuByClas(Clas);
    }
    public Vector<Student> selectStudentBySno(String QSno,String ZSno){
        return jdbcDao.selectStudentBySno(QSno,ZSno);
    }
    public Vector<SelectByScale> selectByScales(String clas,String cname){
        return jdbcDao.selectByScales(clas,cname);
    }
    public Vector<SelectByRoundScale> selectByRoundScales(String clas,String cname){
        return jdbcDao.selectByRoundScales(clas,cname);
    }
    public Vector<SelectScoreBySno> selectScoreBySnos(String Sno){
        return jdbcDao.selectScoreBySnos(Sno);}
    public boolean addCourse(Course course){
        return jdbcDao.addCourse(course);
    }
    public boolean updateCourse(Course course){
        return jdbcDao.updateCourse(course);
    }
    public boolean deleteCourse(String cno){
        return jdbcDao.deleteCourse(cno);
    }
    public Vector<Sc> getSc(String sql) {
        return jdbcDao.getSc(sql);
    }
    public boolean addSc(Sc sc){
        return jdbcDao.addSc(sc);
    }
    public boolean updateSc(Sc sc){
        return jdbcDao.updateSc(sc);
    }
    public boolean deleteSc(String sno,String cno){
        return jdbcDao.deleteSc(sno,cno);
    }
    public boolean updatePassword(Users users){
        return jdbcDao.updatePassword(users);
    }
}

