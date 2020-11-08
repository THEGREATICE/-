package org.scu_db.demo.controller;

import org.scu_db.demo.model.Book;
import org.scu_db.demo.model.Member;
import org.scu_db.demo.model.Title;
import org.scu_db.demo.service.BookService;
import org.scu_db.demo.service.MemberService;
import org.scu_db.demo.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private TitleService titleService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BookService bookService;


    @RequestMapping("/1")
    public List<Integer> homework1(){
        //TODO:请完成相关代码实现下述查询要求：
        //查询所有被借走图书的Book_ID。(对应第2题)
        List<Integer> bookId = new LinkedList<>();

        //------------在此之下写下执行代码--------------
        Connection con;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/book_management",
                    "postgres", "12345678");
            Statement statement = con.createStatement();
            String sql = "select book_id from book where borrowermemno is not null;";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                bookId.add(rs.getInt(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //------------在此之上写下执行代码--------------
        return bookId;
    }

    @RequestMapping("/2")
    public List<Title> homework2(){
        //TODO:请完成相关代码实现下述查询要求：
        //查询书名为”Iliad”或”Odyssey”的书目信息。(对应第4题)
        List<Title> titles= new LinkedList<>();

        //------------在此之下写下执行代码--------------
        Connection con;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/book_management",
                    "postgres", "12345678");
            Statement statement = con.createStatement();
            String sql = "select * from title where name in ('Iliad','Odyssey');";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Title t = new Title();
                String a = new String();
                a = rs.getString(1);
                t.setName(a);
                a = rs.getString(2);
                t.setCallnumber(a);
                a = rs.getString(3);
                t.setIsbn(a);
                Integer b = 0;
                b = rs.getInt(4);
                t.setYear(b);
                a = rs.getString(5);
                t.setPublisher(a);
                titles.add(t);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //-----------在此之上写下执行代码---------------
        return titles;//TODO:修改返回值为titles
    }


    @RequestMapping("/3")
    public Integer homework3(){
        //TODO:请完成相关代码实现下述查询要求：
        //查询callnumber为”Call123”的图书有多少本。(对应第5题)
        Integer count=0;
        //------------在此之下写下执行代码--------------
        Connection con;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/book_management",
                    "postgres", "12345678");
            Statement statement = con.createStatement();
            String sql = "select count(*) from book where callnumber='Call123';";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                count=rs.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //-----------在此之上写下执行代码---------------
        return count;//TODO:修改返回值为books
    }

    @RequestMapping("/4")
    public List<String> homework4(){
        //TODO:请完成相关代码实现下述查询要求：
        //查询本数不超过2本的图书的callnumber号。(对应第7题)
        List<String> callnumbers =new LinkedList<>();
        //------------在此之下写下执行代码--------------
        Connection con;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/book_management",
                    "postgres", "12345678");
            Statement statement = con.createStatement();
            String sql = "select callnumber,count(*) from book group by callnumber having count(*)<3;";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                callnumbers.add(rs.getString(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //-----------在此之上写下执行代码---------------
        return callnumbers;//TODO:修改返回值为books
    }


}
