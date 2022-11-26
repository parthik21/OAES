package com.example.org.service;

import com.example.org.bean.Users;
import com.example.org.dao.UsersDao;
import com.example.org.util.TokenUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationService {
        private final UsersDao dao = new UsersDao();
        private static final TokenUtil tokenUtil = new TokenUtil();
        private static final Logger logger = Logger.getLogger(AuthenticationService.class.toString());

        static {
                try {
                        tokenUtil.init();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public Users authenticate(String username, String password) throws Exception {
                logger.log(Level.INFO, "authentication service called for user " + username);
                List<Users> users = dao.getUserByCredentials(username, password);
                if (users.size() == 0) {
                        logger.log(Level.SEVERE, "authentication failed");
                        throw new Exception("User with these credentials is not found in database");
                }
                logger.log(Level.FINEST, "authentication successful");
                return users.get(0);
        }

        public String issueToken(String username, String password) throws Exception {
                List<Users> users = dao.getUserByCredentials(username, password);
                Users user = users.get(0);
                return tokenUtil.encrypt(user.getUser_id() + "");
        }

        public boolean signUp(String username, String password, String role) {
                Users users = Users.factory();
                if((username.length() <= 8) || (password.length() < 8)) {
                        return false;
                }
                users.setUsername(username);
                users.setPassword(password);
                users.setRole(role);
                return dao.addUser(users);
        }

        public boolean authenticate(String Token) throws Exception {
                System.out.print(tokenUtil.decrypt(Token));
                int user_id = Integer.parseInt(tokenUtil.decrypt(Token));
                System.out.print(user_id);
                Users users = dao.getUserById(user_id);
                return users.getUser_id() == user_id;
        }
}
