package main;

/**
 * Created by hexagon13 on 9/7/2016.
 */

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.HexagonEmployeeTable;
import util.HibernateUtil;

/*

Main App Test Started from here
This is Hibernate Console test

 */
public class AppTest {

    public static void main(String[] args) {


        /*
        Apptest  test Object  Created Here
         */
        AppTest test = new AppTest();

        //Save Employee parameters employee name, Job ,  Salary Of the employee, department number
        test.saveEmployee("Balram", "JavaDeveloper", 20000, 10);

        //Save Employee parameters employee name, Job ,  Salary Of the employee, department number
        test.saveEmployee("Venaktest", "HibernateDev", 30000, 30);

        //Save Employee parameters employee name, Job ,  Salary Of the employee, department number
        test.saveEmployee("Dayanandh Sir", "Project Manager", 98000, 10);

        //Selecting Retriving  Employee parameters employee name, Job ,  Salary Of the employee, department number
        test.retriveEmployee();

        //Deleting the Employee where department number 30
        test.deleteEmployee();

        //Updating Employee where "sal", 10000
        test.updateEmployee();

        //Selecting Retriving  Employee parameters employee name, Job ,  Salary Of the employee, department number
        test.retriveEmployee();
    }


    /**
     * This method is used to save the employee detais.
     *
     * @param ename  This is the first paramter to saveEmployee method
     * @param job    This is the second parameter to saveEmployee method
     * @param sal    This is the second parameter to saveEmployee method
     * @param deptno This is the second parameter to saveEmployee method
     */
    public void saveEmployee(String ename, String job, int sal, int deptno) {

        //Session is used to get a physical connection with a database
        //The main function of the Session is to offer
        // create, read and delete operations for instances of mapped entity classes.
        Session session = HibernateUtil.getSessionFactory().openSession();

        //Allows the application to define units of work, while maintaining abstraction
        // from the underlying transaction implementation
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            HexagonEmployeeTable emp = new HexagonEmployeeTable();
            emp.setEname(ename);
            emp.setJob(job);
            emp.setSal(sal);
            emp.setDeptno(deptno);
            session.save(emp);
            transaction.commit();
            System.out.println("Records inserted sucessessfully");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {

            // A single session might span multiple transactions since the notion of a session
            session.close();
        }

    }

    /*
    Retriving the information about the employee
    ex: employeeNumber , employee Name, employee salary, employee DepartmentNumber
     */

    public void retriveEmployee()

    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List employee = session.createQuery("from HexagonEmployeeTable").list();

            for (Iterator iterator = employee.iterator(); iterator.hasNext(); ) {
                HexagonEmployeeTable employee1 = (HexagonEmployeeTable) iterator.next();
                System.out.println(employee1.getEmpno() + "  " + employee1.getEname() + "  " + employee1.getJob()
                        + "   " + employee1.getSal() + "   " + employee1.getDeptno());
            }
            transaction.commit();

        } catch (HibernateException e) {

            transaction.rollback();

            e.printStackTrace();

        } finally {
            // finally we are closing the session
            session.close();

        }
    }

    /*
    Deleteing Employee where deptno", 30
    i.e, "Venaktest", "HibernateDev", 10000, 30
     */
    public void deleteEmployee() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String queryString = "from HexagonEmployeeTable where deptno = :deptno";
            Query query = session.createQuery(queryString);
            query.setInteger("deptno", 30);
            @SuppressWarnings("deprecation")
            HexagonEmployeeTable employee = (HexagonEmployeeTable) query.uniqueResult();
            session.delete(employee);
            System.out.println("One employee is deleted!");

        } catch (HibernateException e) {

            transaction.rollback();

            e.printStackTrace();

        } finally {
            // finally we are closing the session
            session.close();

        }
    }


    /*
 update the  information about the employee
 ex: employeeNumber , employee Name, employee salary, employee DepartmentNumber
 i.e, "sal", 20000 is updating to 11000
 */
    public void updateEmployee() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String queryString = "from HexagonEmployeeTable where sal = :sal";
            Query query = session.createQuery(queryString);
            query.setInteger("sal", 20000);
            HexagonEmployeeTable employee = (HexagonEmployeeTable) query.uniqueResult();
            employee.setSal(11000);
            session.update(employee);
            System.out.println("One employee is updated!");
        } catch (HibernateException e) {

            transaction.rollback();

            e.printStackTrace();

        } finally {
            // finally we are closing the session
            session.close();

        }
    }
}
