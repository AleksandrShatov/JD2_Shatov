package com.noirix;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContextTester {
    public static void main(String[] args) {

//        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
//
//        User user1 = xmlContext.getBean("user1", User.class);
//        User user2 = (User) xmlContext.getBean("user2");
//        System.out.println(user1);
//        System.out.println(user2);

        Logger log = Logger.getLogger(SpringContextTester.class);
        log.info("I am working!");

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.noirix");

        //UserRepository userRepository = annotationConfigApplicationContext.getBean(UserRepository.class);

        //System.out.println(annotationConfigApplicationContext.getBean("getStringUtils", StringUtils.class).concat("First", "second"));

        UserRepository userRepository = annotationConfigApplicationContext.getBean(UserRepository.class);

        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }


//        UserGenerator userGenerator = annotationConfigApplicationContext.getBean(UserGenerator.class);
//
//        List<User> generatedUsers1 = userGenerator.generate(100);
//        List<User> generatedUsers2 = userGenerator.generate(100);

//        LocationRepository locationRepository = annotationConfigApplicationContext.getBean("locationRepositoryImpl", LocationRepositoryImpl.class);
//
//        for (Location location : locationRepository.findAll()) {
//            System.out.println(location);
//        }

//        //TODO: check speed of executing
//        userRepository.batchInsert(generatedUsers1);
//        userRepository.save(generatedUsers2);

    }
}