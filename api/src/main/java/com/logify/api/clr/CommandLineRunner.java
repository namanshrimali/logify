//package in.logify.api.clr;
//
//import in.logify.api.dao.UserDao;
//import in.logify.api.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//@Transactional
//@Component
//public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
//    @Autowired
//    UserDao userDao;
//    @Override
//    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setName("Administrator");
//        user.setPassword("$2a$10$SecGi8Yd4fYu6yE61sq3suqt0PQAXhVcBGocSNYyqUtvkLuNluVI.");
//        user.setRole("ADMIN");
//        user.setUsername("admin");
//        long no = userDao.deleteByUsername(user.getUsername());
//        userDao.save(user);
//    }
//}
