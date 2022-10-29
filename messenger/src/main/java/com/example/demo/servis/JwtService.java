package com.example.demo.servis;

import com.example.demo.model.JwtAuth;
import com.example.demo.model.User;
import com.example.demo.repo.JwtAuthRepo;
import com.example.demo.repo.RepoMessenger;
import com.example.demo.repo.UserRepo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    @Autowired
    private JwtAuthRepo jwtData;

//    @PersistenceContext // or even @Autowired
//    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;


    public void rewoveOldDate() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


//        String myDate = "17-04-2011T20:20:20";
//        Date date = formatter.parse(myDate);
//
//        Date nowDate = formatter.parse(String.valueOf(LocalDateTime.now()));
//
//        Date maxDate = new Date(nowDate.getTime() - TimeUnit.DAYS.toMinutes(31));


            Session session = sessionFactory.openSession();

//        List listJwt = session.createCriteria(JwtAuth.class)
//                .add(Restrictions.le("date", maxDate)).list();
//
//        System.out.println(listJwt);
//





//        for (int i = 0; i < jwtK.size(); i++) {
//            jwtData.delete(
//                    jwtK.get(i));
//        }
//
//        System.out.println(jwtK.size());


//        String myDate = "17-04-2011T20:20:20";
//        Date date = formatter.parse(myDate);
//
//        Date nowDate = formatter.parse(String.valueOf(LocalDateTime.now()));
//
//
//
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(nowDate.getYear(),nowDate.getMonth(),nowDate.getDay(),nowDate.getHours(),nowDate.getMinutes()-30,nowDate.getSeconds());
//
//
//        Date toDate = calendar.getTime();



        Date nowDate = formatter.parse(String.valueOf(LocalDateTime.now().minusDays(1)));

        String myDate = "0-00-0000T00:00:00";
        Date fromDate = formatter.parse(myDate);



        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<JwtAuth> cr = cb.createQuery(JwtAuth.class);
        Root<JwtAuth> root = cr.from(JwtAuth.class);



        cr.select(root).where(cb.between(root.get("dataJwt"), fromDate, nowDate));

        Query<JwtAuth> query = session.createQuery(cr);

        System.out.println(fromDate.toString()+"        "+ nowDate.toString());



        System.out.println(query.getResultList());

        List<JwtAuth> jwtList = query.getResultList();
        for (int i = 0; i < jwtList.size(); i++) {
            jwtData.delete(jwtList.get(i));
        }


    }

//    public void setJwtData()  {
//
//        LocalDateTime data = LocalDateTime.now();
//        JwtAuth jwtAuth = new JwtAuth();
//        jwtAuth.setJwt("sdff");
//        jwtAuth.setData();
//        jwtData.save(jwtAuth);
//
//
//    }

}
