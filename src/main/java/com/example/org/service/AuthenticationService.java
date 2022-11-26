package com.example.org.service;

import com.example.org.bean.Users;
import com.example.org.dao.UsersDao;
import com.example.org.util.TokenUtil;
import java.util.List;

public class AuthenticationService {
        private final UsersDao dao = new UsersDao();
        private static final TokenUtil tokenUtil = new TokenUtil();

        static {
                try {
                        tokenUtil.init();
                }catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public boolean authenticate(String username, String password) throws Exception {
                List<Users> users = dao.getUserByCredentials(username, password);
                if (users.size() == 0) {
                        throw new Exception("User with these credentials is not found in database");
                }
                return true;
        }

        public String issueToken(String username, String password) throws Exception {
                List<Users> users = dao.getUserByCredentials(username, password);
                Users user = users.get(0);
                return tokenUtil.encrypt(user.getUser_id() + "");
        }

        public boolean signUp(String username, String password) {
                Users users = Users.factory();
                if((username.length() <= 8) || (password.length() < 8)) {
                        return false;
                }
                users.setUsername(username);
                users.setPassword(password);
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
